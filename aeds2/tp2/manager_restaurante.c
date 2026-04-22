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

char ** split_string(char * s, char * regex){
	int len = 1;
	for(int i = 0; i < length_str(s); i++){
		if(s[i] == regex[0]){
			len++;
		}	
	}

	char ** string = (char **) malloc((len)* sizeof(char*));
	int aux = 0;

	for (int i = 0; i < len; i++) {
        	string[i] = (char *) malloc(100 * sizeof(char));
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

typedef struct Hora{
	int hora;
	int minuto;

}Hora;

Hora parse_hora(char * s){
	char ** partes = split_string(s, ":");
	Hora hora;
	hora.hora = atoi(partes[0]);
	hora.minuto = atoi(partes[1]);	
}

void formatar_hora(Hora * hora, char * buffer){
	sprintf(buffer, "%d:%d", hora.hora, hora.minuto);
}

typedef struct Data{
	int ano;
	int mes;
	int dia;
}Data;

Data parse_data(char * s){
	char ** partes = split_string(s, "-");
	Data data;
	data.ano = atoi(partes[0]);
	data.mes = atoi(partes[1]);
	data.dia = atoi(partes[2]);
}

void formatar_data(Data * data, char * buffer){
	sprintf(buffer, "%d/%d/%d/", data.dia, data.mes, data.ano);
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

Restaurante * parse_restaurante(char * s){}
void formatar_restaurante(Restaurante * r, char * buffer){}

typedef struct Colecao_Restaurantes{
	int tamanho;
	Restaurante ** Restaurante;
}Colecao_Restaurantes;

void ler_csv_colecao(Colecao_Restaurantes * c, char * path){}
Colecao_Restaurantes * ler_csv(){}

int main(){
	char ** test = split_string("oi,tudo,bem", ",");
	for(int i = 0; i < 3; i++){
		printf("%s\n", test[i]);
	}

	return 0;
}



