#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

long comparacoes = 0;
long movimentacoes = 0;

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

int get_maior_capacidade(Colecao_Restaurantes * colecao){
	int maior = colecao->restaurantes[0]->capacidade;
	for(int i = 1; i < colecao->tamanho; i++){
		if (colecao->restaurantes[i]->capacidade > maior){
			maior = colecao->restaurantes[i]->capacidade;
		}
	}

	return maior;
}

void counting_sort(Colecao_Restaurantes * colecao){
	if(colecao->tamanho <= 1){
		return;
	}
	int maior = get_maior_capacidade(colecao);

	int * count = calloc(maior+1, sizeof(int));
	
	Restaurante **ordenado = (Restaurante **) malloc(colecao->tamanho * sizeof(Restaurante *));

	for(int i = 0; i < colecao->tamanho; i++){
		count[colecao->restaurantes[i]->capacidade]++;
	}

	for(int i = 1; i <= maior; i++){
		count[i] +=count[i-1];
	}

	for(int i = colecao->tamanho -1; i>=0; i--){
		int capacidade = colecao->restaurantes[i]->capacidade;
		ordenado[count[capacidade]-1] = colecao->restaurantes[i];
		count[capacidade]--;
		movimentacoes++;
	}
	for(int i = 0; i < colecao->tamanho; i++){
		colecao->restaurantes[i] = ordenado[i];
		movimentacoes++;
	}
	free(count); 
	free(ordenado);
}



void generate_log(double tempo){
	FILE *log = fopen("903104_countingsort.txt", "w");

	if(log != NULL){
		fprintf(log, "903104\t%ld\t%ld\t%f",
			comparacoes,
			movimentacoes,
			tempo);
	fclose(log);
	}
}

int main(){
	int n = 1;
	char * buffer = malloc(1024 * sizeof(char));

	Colecao_Restaurantes *colecao = ler_csv();

   
	Colecao_Restaurantes *filtrados = malloc(sizeof(Colecao_Restaurantes));
	filtrados->tamanho = 0;
	filtrados->capacidade = colecao->tamanho;
	filtrados->restaurantes = malloc(filtrados->capacidade * sizeof(Restaurante*));

	while (1) {
		if (scanf("%d", &n) != 1) break;
		if (n <= 0) break;
	
		if (n <= colecao->tamanho) {
			filtrados->restaurantes[filtrados->tamanho++] = colecao->restaurantes[n-1];
		}
	}


	clock_t inicio = clock();
	counting_sort(filtrados);
	clock_t fim = clock();
	double tempo = (double)(fim - inicio)/CLOCKS_PER_SEC;
	
	for(int i = 0; i < filtrados->tamanho; i++){
		formatar_restaurante(filtrados->restaurantes[i], buffer);
		printf("%s", buffer);
	}
	
	generate_log(tempo);
	free(filtrados->restaurantes);
	free(filtrados);
	free(colecao);
	free(buffer);
	return 0;
}

