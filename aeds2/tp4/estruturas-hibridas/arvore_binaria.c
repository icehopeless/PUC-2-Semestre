#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int length_str(char *str){
    int count = 0;
    while(str && str[count] != '\0'){
        count++;
    }
    return count;
}

char ** split_string(char * s, char * regex, int * tam){
    int len = 1;
    for(int i = 0; i < length_str(s); i++){
        if(s[i] == regex[0]){
            len++;
        }	
    }
    if(tam != NULL){
        *tam = len;	
    }

    char ** string = (char **) malloc((len)* sizeof(char*));
    int aux = 0;

    for (int i = 0; i < len; i++) {
        string[i] = (char *) malloc(256 * sizeof(char));
    }

    for(int i = 0; i < len; i++){
        int j = 0;
        while(s[aux] != '\0' && s[aux] != regex[0]){			
            string[i][j] = s[aux];
            j++;
            aux++;
        }
        string[i][j] = '\0';

        if(s[aux] == regex[0]){
            aux++;
        }
    }

    return string;	
}

char * remove_char(char * s, char c){
    int j = 0;
    for (int i = 0; s[i] != '\0'; i++) {
        if (s[i] != c) {
            s[j++] = s[i];
        }
    }
    s[j] = '\0';
    return s;
}

char * str_modify(char * base, char * str){
    int len_base = length_str(base);
    int len_str = length_str(str);

    base = realloc(base, len_base + len_str + 2);
    int aux = len_base;
    if(len_base > 0){
        base[aux] = ',';
        aux++;
    }

    for(int i = 0; i < len_str; i++){
        base[aux+i] = str[i];
    }

    base[aux+len_str] = '\0';

    return base;
}

char * str_copy(char * str, char * str2){
    str = realloc(str, length_str(str2) + 1);
    for(int i = 0; i < length_str(str2); i++){
        str[i] = str2[i];
    }
    str[length_str(str2)] = '\0';
    return str;
}

char * limpar_quebra_linha(char * s){
    s = remove_char(s, '\n');
    s = remove_char(s, '\r');
    return s;
}

typedef struct Hora{
    int hora;
    int minuto;
}Hora;

Hora parse_hora(char * s){
    char ** partes = split_string(s, ":", NULL);
    Hora hora;
    hora.hora = atoi(partes[0]);
    hora.minuto = atoi(partes[1]);	
    for(int i=0; i<2; i++) free(partes[i]);
    free(partes);
    return hora;
}

void formatar_hora(Hora * hora, char * buffer){
    sprintf(buffer, "%02d:%02d", hora->hora, hora->minuto);
}

typedef struct Data{
    int ano;
    int mes;
    int dia;
}Data;

Data parse_data(char * s){
    char ** partes = split_string(s, "-", NULL);
    Data data;
    data.ano = atoi(partes[0]);
    data.mes = atoi(partes[1]);
    data.dia = atoi(partes[2]);
    for(int i=0; i<3; i++) free(partes[i]);
    free(partes);
    return data;
}

void formatar_data(Data * data, char * buffer){
    sprintf(buffer, "%02d/%02d/%02d", data->dia, data->mes, data->ano);
}

typedef struct Restaurante{
    int id;
    char * nome;
    char * cidade;
    int capacidade;
    double avaliacao;
    int n_tipos_cozinha;
    char ** tipos_cozinha;
    int faixa_preco;
    Hora horario_abertura;
    Hora horario_fechamento;
    Data data_abertura;
    int aberto;
}Restaurante;

Restaurante * parse_restaurante(char * s){
    char ** partes = split_string(s, ",", NULL);
    char ** horas = split_string(partes[7], "-", NULL);
    int tam = 0;

    Restaurante *restaurante = (Restaurante *) malloc(sizeof(Restaurante));

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

    if(strcmp("true", partes[9]) == 0){
        restaurante->aberto = 1;
    }else{
        restaurante->aberto = 0;
    }

    return restaurante;
}

void formatar_restaurante(Restaurante * r, char * buffer){
    char hora_abertura[10];
    formatar_hora(&r->horario_abertura, hora_abertura);
    
    char hora_fechamento[10];
    formatar_hora(&r->horario_fechamento, hora_fechamento);
    
    char data[15];
    formatar_data(&r->data_abertura, data);

    char * boolean = r->aberto ? "true" : "false";

    char faixa_preco[10];
    int i;
    for(i = 0; i < r->faixa_preco; i++){
        faixa_preco[i] = '$';
    }
    faixa_preco[i] = '\0';	

    char * tipos_str = NULL;
    tipos_str = str_copy(tipos_str, r->tipos_cozinha[0]);
    if(r->n_tipos_cozinha > 1){
        for(int i = 1; i < r->n_tipos_cozinha; i++){
            tipos_str = str_modify(tipos_str, r->tipos_cozinha[i]);
        }
    }

    sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## [%s] ## %s ## %s-%s ## %s ## %s]",
            r->id, r->nome, r->cidade, r->capacidade, r->avaliacao,
            tipos_str, faixa_preco, hora_abertura, hora_fechamento, data, boolean);
    
    free(tipos_str);
}

typedef struct Colecao_Restaurantes{
    int tamanho;
    int capacidade;
    Restaurante ** restaurantes;
}Colecao_Restaurantes;

Restaurante **aumentarCapacidade(Restaurante ** rs, int *capacidade){
    *capacidade = (*capacidade) * 2;
    Restaurante **novoR = realloc(rs, (*capacidade) * sizeof(Restaurante*));
    return (novoR == NULL) ? rs : novoR;
}

void ler_csv_colecao(Colecao_Restaurantes * c, char * path){
    c->tamanho = 0;
    c->capacidade = 10;
    c->restaurantes = malloc((c->capacidade) * sizeof(Restaurante*));
    
    FILE *file = fopen(path, "r");
    if(file == NULL){
        printf("erro ao ler o arquivo\n");
        return;
    }

    char line[1024];
    fgets(line, sizeof(line), file); // skip header
    while(fgets(line, sizeof(line), file) != NULL){
        if(c->tamanho == c->capacidade){
            c->restaurantes = aumentarCapacidade(c->restaurantes, &c->capacidade);
        }
        c->restaurantes[c->tamanho++] = parse_restaurante(limpar_quebra_linha(line));	
    }
    fclose(file);
}

Colecao_Restaurantes * ler_csv(){
    Colecao_Restaurantes *colecao = malloc(sizeof(Colecao_Restaurantes));
    ler_csv_colecao(colecao, "/tmp/restaurantes.csv");
    return colecao;
}

//lista
typedef struct NoLista{
    Restaurante *r;
    struct NoLista *prox;
}NoLista;

typedef struct Lista{
    NoLista *inicio;
    int tamanho;
}Lista;

Lista* criarLista() {
    Lista *l = (Lista*) malloc(sizeof(Lista));
    l->inicio = NULL;
    l->tamanho = 0;
    return l;
}

void inserir_fim(Lista *l, Restaurante *r){
    NoLista *novo = (NoLista *) malloc(sizeof(NoLista));
    novo->r = r;
    novo->prox = NULL;

    if(l->inicio == NULL){
        l->inicio = novo;
    } else {
        NoLista *atual = l->inicio;
        while(atual->prox != NULL){
            atual = atual->prox;
        }
        atual->prox = novo;
    }
    l->tamanho++;
}

void select_sort(Lista *l){
    if(l == NULL || l->inicio == NULL) return;
    for(NoLista *i = l->inicio; i != NULL; i = i->prox){
        NoLista *menor = i;
        for(NoLista *j = i->prox; j != NULL; j = j->prox){
            if(strcmp(menor->r->nome, j->r->nome) > 0){
                menor = j;
            }
        }
        Restaurante *tmp = i->r;
        i->r = menor->r;
        menor->r = tmp;
    }
}

Restaurante* buscar_restaurante_lista(Lista *l, char *nome){
    select_sort(l);
    NoLista *atual = l->inicio;

    while(atual != NULL){
        int cmp = strcmp(atual->r->nome, nome);

        if(cmp == 0){
            return atual->r;  
        }

        if(cmp > 0){
            return NULL;
        }
		printf("%s ", atual->r->nome);
        atual = atual->prox;
    }
    return NULL;
}

//arvore
typedef struct No{
    struct No* esq;
    struct No* dir;
    Lista *lista;
    char key;
} No;

typedef struct arvoreBinaria{
    No* raiz;
} arvoreBinaria;

No* novoNo(Restaurante *r){
    No* novo = (No*) malloc(sizeof(No));
    novo->key = r->nome[0];
    novo->lista = criarLista();
    inserir_fim(novo->lista, r);
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}

No* addRec(No* no, Restaurante *r){
    if(no == NULL){
        return novoNo(r);
    }

    if(r->nome[0] == no->key){
        inserir_fim(no->lista, r);
        return no; 
    }

    if(r->nome[0] < no->key){
        no->esq = addRec(no->esq, r);
    }else{
        no->dir = addRec(no->dir, r);
    }
    return no;
}

void add(arvoreBinaria* arvore, Restaurante *r){
    arvore->raiz = addRec(arvore->raiz, r);
}

Restaurante* pesquisarRec(char nome[], No* no){
    if(no == NULL){
        return NULL;
    }

    if(nome[0] == no->key){
        return buscar_restaurante_lista(no->lista, nome);
    }
	
    if(nome[0] < no->key){
        printf("ESQ ");
        return pesquisarRec(nome, no->esq);
    }else{
        printf("DIR ");
        return pesquisarRec(nome, no->dir);
    }
}

Restaurante* pesquisar(arvoreBinaria* arvore, char nome[]){
    printf("RAIZ ");
    return pesquisarRec(nome, arvore->raiz);
}

void caminhar_em_ordem_rec(No* no) {
    if (no != NULL) {
        caminhar_em_ordem_rec(no->esq);

        select_sort(no->lista);
        
        NoLista *atual = no->lista->inicio;
        char buffer[1500];
        while(atual != NULL){
            formatar_restaurante(atual->r, buffer);
            printf("%s\n", buffer);
            atual = atual->prox;
        }
        
        caminhar_em_ordem_rec(no->dir);
    }
}

void caminhar_em_ordem(arvoreBinaria* arvore) {
    caminhar_em_ordem_rec(arvore->raiz);
}

int buscar_restaurante_colecao(Colecao_Restaurantes *c, int id){
    for(int i = 0; i < c->tamanho; i++){
        if(c->restaurantes[i]->id == id){
            return i;
        }
    }
    return -1;
}

int main(){
    Colecao_Restaurantes *colecao = ler_csv();
    arvoreBinaria arvore;
    arvore.raiz = NULL;

    char entrada[256];

    while(fgets(entrada, sizeof(entrada), stdin) != NULL){
        limpar_quebra_linha(entrada);

        if(strcmp(entrada, "-1") == 0) break;

        int id = atoi(entrada);
        int pos = buscar_restaurante_colecao(colecao, id);

        if(pos != -1){
            add(&arvore, colecao->restaurantes[pos]);
        }
    }

    while(fgets(entrada, sizeof(entrada), stdin) != NULL){
        limpar_quebra_linha(entrada);

        if(strcmp(entrada, "FIM") == 0) break;

        Restaurante *r = pesquisar(&arvore, entrada);

        if(r != NULL){
            printf("SIM ");
            char buffer[1500];
            formatar_restaurante(r, buffer);
            printf("%s\n", buffer);
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}