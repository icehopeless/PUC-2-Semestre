#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int length_str(char *str) {
    int count = 0;
    while (str && str[count] != '\0') {
        count++;
    }
    return count;
}

char **split_string(char *s, char *regex, int *tam) {
    int len = 1;
    for (int i = 0; i < length_str(s); i++) {
        if (s[i] == regex[0]) {
            len++;
        }
    }
    if (tam != NULL) {
        *tam = len;
    }

    char **string = (char **)malloc((len) * sizeof(char *));
    int aux = 0;

    for (int i = 0; i < len; i++) {
        string[i] = (char *)malloc(256 * sizeof(char));
    }

    for (int i = 0; i < len; i++) {
        int j = 0;
        while (s[aux] != '\0' && s[aux] != regex[0]) {
            string[i][j] = s[aux];
            j++;
            aux++;
        }
        string[i][j] = '\0';

        if (s[aux] == regex[0]) {
            aux++;
        }
    }

    return string;
}

char *remove_char(char *s, char c) {
    int j = 0;
    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] != c) {
            s[j++] = s[i];
        }
    }
    s[j] = '\0';
    return s;
}

char *str_modify(char *base, char *str) {
    int len_base = length_str(base);
    int len_str = length_str(str);

    base = realloc(base, len_base + len_str + 2);
    int aux = len_base;
    if (len_base > 0) {
        base[aux] = ',';
        aux++;
    }

    for (int i = 0; i < len_str; i++) {
        base[aux + i] = str[i];
    }

    base[aux + len_str] = '\0';

    return base;
}

char *str_copy(char *str, char *str2) {
    str = realloc(str, length_str(str2) + 1);
    for (int i = 0; i < length_str(str2); i++) {
        str[i] = str2[i];
    }
    str[length_str(str2)] = '\0';
    return str;
}

char *limpar_quebra_linha(char *s) {
    s = remove_char(s, '\n');
    s = remove_char(s, '\r');
    return s;
}

typedef struct Hora {
    int hora;
    int minuto;
} Hora;

Hora parse_hora(char *s) {
    char **partes = split_string(s, ":", NULL);
    Hora hora;
    hora.hora = atoi(partes[0]);
    hora.minuto = atoi(partes[1]);
    for (int i = 0; i < 2; i++) free(partes[i]);
    free(partes);
    return hora;
}

void formatar_hora(Hora *hora, char *buffer) {
    sprintf(buffer, "%02d:%02d", hora->hora, hora->minuto);
}

typedef struct Data {
    int ano;
    int mes;
    int dia;
} Data;

Data parse_data(char *s) {
    char **partes = split_string(s, "-", NULL);
    Data data;
    data.ano = atoi(partes[0]);
    data.mes = atoi(partes[1]);
    data.dia = atoi(partes[2]);
    for (int i = 0; i < 3; i++) free(partes[i]);
    free(partes);
    return data;
}

void formatar_data(Data *data, char *buffer) {
    sprintf(buffer, "%02d/%02d/%d", data->dia, data->mes, data->ano);
}

typedef struct Restaurante {
    int id;
    char *nome;
    char *cidade;
    int capacidade;
    double avaliacao;
    int n_tipos_cozinha;
    char **tipos_cozinha;
    int faixa_preco;
    Hora horario_abertura;
    Hora horario_fechamento;
    Data data_abertura;
    int aberto;
} Restaurante;

Restaurante *parse_restaurante(char *s) {
    char **partes = split_string(s, ",", NULL);
    char **horas = split_string(partes[7], "-", NULL);
    int tam = 0;

    Restaurante *restaurante = (Restaurante *)malloc(sizeof(Restaurante));

    restaurante->id = atoi(partes[0]);
    restaurante->nome = strdup(partes[1]);
    restaurante->cidade = strdup(partes[2]);
    restaurante->capacidade = atoi(partes[3]);
    restaurante->avaliacao = atof(partes[4]);
    restaurante->tipos_cozinha = split_string(partes[5], ";", &tam);
    restaurante->n_tipos_cozinha = tam;
    restaurante->faixa_preco = length_str(partes[6]);
    restaurante->horario_abertura = parse_hora(horas[0]);
    restaurante->horario_fechamento = parse_hora(horas[1]);
    restaurante->data_abertura = parse_data(partes[8]);

    if (strcmp("true", partes[9]) == 0) {
        restaurante->aberto = 1;
    } else {
        restaurante->aberto = 0;
    }

    return restaurante;
}

void formatar_restaurante(Restaurante *r, char *buffer) {
    char hora_abertura[10];
    formatar_hora(&r->horario_abertura, hora_abertura);

    char hora_fechamento[10];
    formatar_hora(&r->horario_fechamento, hora_fechamento);

    char data[15];
    formatar_data(&r->data_abertura, data);

    char *boolean = r->aberto ? "true" : "false";

    char faixa_preco[10];
    int i;
    for (i = 0; i < r->faixa_preco; i++) {
        faixa_preco[i] = '$';
    }
    faixa_preco[i] = '\0';

    char *tipos_str = NULL;
    tipos_str = str_copy(tipos_str, r->tipos_cozinha[0]);
    if (r->n_tipos_cozinha > 1) {
        for (int i = 1; i < r->n_tipos_cozinha; i++) {
            tipos_str = str_modify(tipos_str, r->tipos_cozinha[i]);
        }
    }

    sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## [%s] ## %s ## %s-%s ## %s ## %s]",
            r->id, r->nome, r->cidade, r->capacidade, r->avaliacao,
            tipos_str, faixa_preco, hora_abertura, hora_fechamento, data, boolean);

    free(tipos_str);
}

typedef struct Colecao_Restaurantes {
    int tamanho;
    int capacidade;
    Restaurante **restaurantes;
} Colecao_Restaurantes;

Restaurante **aumentarCapacidade(Restaurante **rs, int *capacidade) {
    *capacidade = (*capacidade) * 2;
    Restaurante **novoR = realloc(rs, (*capacidade) * sizeof(Restaurante *));
    return (novoR == NULL) ? rs : novoR;
}

void ler_csv_colecao(Colecao_Restaurantes *c, char *path) {
    c->tamanho = 0;
    c->capacidade = 10;
    c->restaurantes = malloc((c->capacidade) * sizeof(Restaurante *));

    FILE *file = fopen(path, "r");
    if (file == NULL) {
        printf("erro ao ler o arquivo\n");
        return;
    }

    char line[1024];
    fgets(line, sizeof(line), file); // skip header
    while (fgets(line, sizeof(line), file) != NULL) {
        if (c->tamanho == c->capacidade) {
            c->restaurantes = aumentarCapacidade(c->restaurantes, &c->capacidade);
        }
        c->restaurantes[c->tamanho++] = parse_restaurante(limpar_quebra_linha(line));
    }
    fclose(file);
}

Colecao_Restaurantes *ler_csv() {
    Colecao_Restaurantes *colecao = malloc(sizeof(Colecao_Restaurantes));
    ler_csv_colecao(colecao, "/tmp/restaurantes.csv");
    return colecao;
}


struct NoTrie;

typedef struct ListaTrie {
    char c;
    struct NoTrie *ponteiroTrie;
    struct ListaTrie *prox;
} ListaTrie;

typedef struct NoTrie {
    ListaTrie *filhos;
    int finalString;
    Restaurante *r;
} NoTrie;

NoTrie *criarNoTrie() {
    NoTrie *novo = (NoTrie *)malloc(sizeof(NoTrie));
    novo->filhos = NULL;
    novo->finalString = 0;
    novo->r = NULL;
    return novo;
}

NoTrie *buscarFilho(NoTrie *node, char c) {
    ListaTrie *atual = node->filhos;
    while (atual != NULL) {
        if (atual->c == c) {
            return atual->ponteiroTrie;
        }
        atual = atual->prox;
    }
    return NULL;
}

void inserirFilho(NoTrie *node, char c, NoTrie *filho) {
    ListaTrie *novo = (ListaTrie *)malloc(sizeof(ListaTrie));
    novo->c = c;
    novo->ponteiroTrie = filho;
    novo->prox = node->filhos;
    node->filhos = novo;
}

void inserirTrie(NoTrie *node, char *s, int i, Restaurante *r) {
    char c = s[i];
    NoTrie *proximo = buscarFilho(node, c);
    if (proximo == NULL) {
        proximo = criarNoTrie();
        inserirFilho(node, c, proximo);
    }

    if (i == length_str(s) - 1) {
        proximo->finalString = 1;
        proximo->r = r;
    } else {
        inserirTrie(proximo, s, i + 1, r);
    }
}

void pesquisarTrie(NoTrie *node, char *s, int i) {
    if (i >= length_str(s)) return;

    char c = s[i];
    NoTrie *proximo = buscarFilho(node, c);

    if (proximo == NULL) {
        printf("NAO\n");
    } else {
        printf("%c ", c);
        if (i == length_str(s) - 1) {
            if (proximo->finalString) {
                char buffer[1500];
                formatar_restaurante(proximo->r, buffer);
                printf("SIM %s\n", buffer);
            } else {
                printf("NAO\n");
            }
        } else {
            pesquisarTrie(proximo, s, i + 1);
        }
    }
}

int main() {
    Colecao_Restaurantes *colecao = ler_csv();
    NoTrie *raiz = criarNoTrie();

    char entrada[256];

    while (fgets(entrada, sizeof(entrada), stdin) != NULL) {
        limpar_quebra_linha(entrada);
        if (strcmp(entrada, "-1") == 0) break;

        if (length_str(entrada) > 0) {
            int id = atoi(entrada);
            for (int i = 0; i < colecao->tamanho; i++) {
                if (colecao->restaurantes[i]->id == id) {
                    inserirTrie(raiz, colecao->restaurantes[i]->nome, 0, colecao->restaurantes[i]);
                    break;
                }
            }
        }
    }

    while (fgets(entrada, sizeof(entrada), stdin) != NULL) {
        limpar_quebra_linha(entrada);
        if (strcmp(entrada, "FIM") == 0) break;

        if (length_str(entrada) > 0) {
            pesquisarTrie(raiz, entrada, 0);
        }
    }

    return 0;
}
