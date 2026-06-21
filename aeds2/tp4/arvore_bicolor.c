#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int length_str(char *str){
        int count = 0;

        while(str[count] != '\0'){
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
	for (int i = 0; s[i] != '\0'; i++) {
		if (s[i] == c) {
                        s[i] = '\0';
                        break;
               }
        }
	return s;
}

char * str_modify(char * base, char * str){
	int len_base = length_str(base);
        int len_str = length_str(str);

	base = realloc(base, len_base + len_str + 2);
	int aux = len_base;
	base[aux] = ',';
	aux++;

	for(int i = 0; i < len_str; i++){
		base[aux+i] = str[i];
	}

	base[len_base+len_str+1] = '\0';

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
	restaurante->nome = partes[1];
	restaurante->cidade = partes[2];
	restaurante->capacidade = atoi(partes[3]);
	restaurante->avaliacao = atof(partes[4]);
	restaurante->tipos_cozinha = split_string(partes[5], ";", &tam);
	restaurante->n_tipos_cozinha = tam; 
	restaurante->faixa_preco = length_str(partes[6]);
	restaurante->horario_abertura = parse_hora(horas[0]);
	restaurante->horario_fechamento = parse_hora(horas[1]);
	restaurante->data_abertura = parse_data(partes[8]);
	limpar_quebra_linha(partes[9]);

	if(strcmp("true", partes[9]) == 0){
		restaurante->aberto = 1;
	}else{
		restaurante->aberto = 0;
	}

	return restaurante;
}

void formatar_restaurante(Restaurante * r, char * buffer){
	char * hora_abertura = malloc(10 * sizeof(char));
	formatar_hora(&r->horario_abertura, hora_abertura);
	
	char * hora_fechamento = malloc(10 * sizeof(char));
	formatar_hora(&r->horario_fechamento, hora_fechamento);
	
	char * data = malloc(10 * sizeof(char));
	formatar_data(&r->data_abertura, data);

	char * boolean;
	if(r->aberto == 1){
		boolean = "true";
	}else{
		boolean = "false";
	}

	char faixa_preco[5];
	for(int i = 0; i < r->faixa_preco;i++){
		faixa_preco[i] = '$';
	}

	faixa_preco[r->faixa_preco] = '\0';	

	char * tipos_str = NULL;
	tipos_str = str_copy(tipos_str, r->tipos_cozinha[0]);
	if(r->n_tipos_cozinha > 1){
		for(int i = 1; i < r->n_tipos_cozinha; i++){
			tipos_str = str_modify(tipos_str, r->tipos_cozinha[i]);
			                        

		}
	}

	sprintf(buffer, "[%d ## %s ## %s ## %d ## %.1f ## [%s] ## %s ## %s-%s ## %s ## %s]\n",
			r->id,
			r->nome,
			r->cidade,
			r->capacidade,
			r->avaliacao,
			tipos_str,
			faixa_preco,
			hora_abertura,
			hora_fechamento,
			data,
			boolean
			);

	free(hora_abertura);
	free(hora_fechamento);
	free(data);
}

typedef struct Colecao_Restaurantes{
	int tamanho;
	int capacidade;
	Restaurante ** restaurantes;
}Colecao_Restaurantes;

Restaurante **aumentarCapacidade(Restaurante ** rs, int *capacidade){
	*capacidade = (*capacidade) * 2;
	Restaurante **novoR = realloc(rs, (*capacidade) * sizeof(Restaurante*));
	if(novoR==NULL){
		return rs;
	}

	return  novoR;
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

	char line[256];
	fgets(line, sizeof(line), file);
	while(fgets(line, sizeof(line), file) != NULL){
		if(c->tamanho == c->capacidade){
			c->restaurantes = aumentarCapacidade(c->restaurantes, &c->capacidade);
		}
		c->restaurantes[c->tamanho] = parse_restaurante(remove_char(line, '\n'));	
		c->tamanho++;
	}

	fclose(file);
}

Colecao_Restaurantes * ler_csv(){
	Colecao_Restaurantes *colecao = malloc(sizeof(Colecao_Restaurantes));
	ler_csv_colecao(colecao, "/tmp/restaurantes.csv");
	return colecao;
}

typedef struct No{
    struct No* esq;
    struct No* dir;
    int color;
    Restaurante *r;
} No;

typedef struct arvoreBicolor{
    No* raiz;
} arvoreBicolor;

No* novoNo(Restaurante *r){
    No* novo = (No*) malloc(sizeof(No));
    novo->r = r;
    novo->esq = NULL;
    novo->dir = NULL;
    novo->color = 0;
    return novo;
}


int isTipo4(No *no){
	if(no->esq != NULL && no->dir != NULL){
		if(no->esq->color == 1 && no->dir->color == 1){
			return 1;
		}
	}

	return 0;
}

void fragmentar(No *no){
	no->color = !no->color;
	no->esq->color = !no->esq->color;
	no->dir->color = !no->dir->color;
}

No* rotacionarEsq(No *no){
	No *noDir = no->dir;
	No *noDirEsq = noDir->esq;

	noDir->esq = no;
	no->dir = noDirEsq;
	
	return noDir;
}

No* rotacionarDir(No *no){
	No *noEsq = no->esq;
	No *noEsqDir = noEsq->dir;

	noEsq->dir = no;
	no->esq = noEsqDir;
	
	return noEsq;
}

No * rotacionarDirEsq(No *no){
	no->dir = rotacionarDir(no->dir);
	return rotacionarEsq(no);
}

No * rotacionarEsqDir(No *no){
	no->esq = rotacionarEsq(no->esq);
	return rotacionarDir(no);
}


void balancear(No *bisavo, No *avo, No *pai, No *atual, arvoreBicolor *a){
	No *novaraiz = NULL;
	
	if(strcmp(avo->r->nome, pai->r->nome) > 0 && strcmp(pai->r->nome, atual->r->nome) > 0){
		novaraiz = rotacionarDir(avo);

	}
	else if(strcmp(avo->r->nome, pai->r->nome) > 0 && strcmp(pai->r->nome, atual->r->nome) < 0){
		novaraiz = rotacionarEsqDir(avo);
	}

	else if(strcmp(avo->r->nome, pai->r->nome) < 0 && strcmp(pai->r->nome, atual->r->nome) < 0){
		novaraiz = rotacionarEsq(avo);
	}

	else if(strcmp(avo->r->nome, pai->r->nome) < 0 && strcmp(pai->r->nome, atual->r->nome) > 0){
		novaraiz = rotacionarDirEsq(avo);
	}
	novaraiz->color = 0;
	novaraiz->dir->color = 1;
	novaraiz->esq->color = 1;

	if(bisavo == NULL){
		a->raiz = novaraiz;
	}
	else if(bisavo->esq == avo){
		bisavo->esq = novaraiz;
	}
	else{
		bisavo->dir = novaraiz;
	}
}


No* addRec(No* bisavo, No* avo, No* pai, No* no, Restaurante *r, arvoreBicolor *a){
    if(no == NULL){
        No* novo = novoNo(r);
	novo->color = 1;

	if(pai == NULL){return novo;}

	if(strcmp(r->nome, pai->r->nome) < 0){
		pai->esq = novo;
	}
	else{
		pai->dir = novo;
	}

	if(pai->color && avo != NULL){
		balancear(bisavo, avo, pai, novo, a);
	}
	return novo;
    }

    if(isTipo4(no)){
    	fragmentar(no);
	
	if(no == a->raiz){
		no->color = 0;
	}
	else if(pai != NULL && pai->color == 1 && avo != NULL){
		balancear(bisavo, avo, pai, no, a);
	}
    }

    if(strcmp(r->nome, no->r->nome) < 0){
        addRec(avo, pai, no, no->esq, r, a); 
    }

    else{
        addRec(avo, pai, no, no->dir, r, a);
    }
    return no;
}

void add(arvoreBicolor* arvore, Restaurante *r){
	if(arvore->raiz != NULL){
		arvore->raiz = addRec(NULL,NULL,NULL,arvore->raiz, r, arvore);
		arvore->raiz->color = 0;
	}else{
		arvore->raiz = novoNo(r);
		arvore->raiz->color = 0;
	}
}

int pesquisarRec(char nome[], No* no){
    if(no == NULL){
        return 0;
    }

    if(strcmp(nome, no->r->nome) == 0){
        return 1;
    }

    if(strcmp(nome, no->r->nome) < 0){
        printf("esq ");
        return pesquisarRec(nome, no->esq);
    }else{
        printf("dir ");
        return pesquisarRec(nome, no->dir);
    }
}

int pesquisar(arvoreBicolor* arvore, char nome[]){
    printf("raiz ");
    return pesquisarRec(nome, arvore->raiz);
}

void caminhar_em_ordem_rec(No* no, char *buffer) {
    if (no != NULL) {
        caminhar_em_ordem_rec(no->esq, buffer);
        formatar_restaurante(no->r, buffer);
        printf("%s", buffer);
        caminhar_em_ordem_rec(no->dir, buffer);
    }
}

void caminhar_em_ordem(arvoreBicolor* arvore, char *buffer) {
    caminhar_em_ordem_rec(arvore->raiz, buffer);
}

int buscar_restaurante(Colecao_Restaurantes *c, int id){
    for(int i = 0; i < c->tamanho; i++){
        if(c->restaurantes[i]->id == id){
            return i;
        }
    }
    return -1;
}



int main(){
    Colecao_Restaurantes *colecao = ler_csv();

    arvoreBicolor arvore;
    arvore.raiz = NULL;

    char entrada[256];

    
    while(fgets(entrada, sizeof(entrada), stdin) != NULL){
        limpar_quebra_linha(entrada);
        if(strcmp(entrada, "-1") == 0) {
            break;
        }

        int id = atoi(entrada);
        int pos = buscar_restaurante(colecao, id);
        if(pos != -1){
            add(&arvore, colecao->restaurantes[pos]);
        }
    }
    while(fgets(entrada, sizeof(entrada), stdin) != NULL){
        limpar_quebra_linha(entrada);
        if(strcmp(entrada, "FIM") == 0) {
            break;
        }

        int encontrado = pesquisar(&arvore, entrada);
        if(encontrado) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

 
    char buffer[1500];
    caminhar_em_ordem(&arvore, buffer);

    return 0;
}
