#include <stdio.h>
#include <stdlib.h>

#define TAM 1000
#define KEY 3

//funcao que retorna o tamanho de uma string passada por parametro 
int lengthStr(char *str){
	int count = 0;

	while(str[count] != '\0'){
		count++;
	}

	return count;
}


//funcao que que recebe duas string e retorna true caso sejam iguais
int strCompare(char * str, char * str2){
	int aux = 1;
	if(lengthStr(str) != lengthStr(str2)){
		return 0;
	}
	for(int i = 0; i < lengthStr(str); i++){
		if(str[i] != str2[i]){
			return 0;
		}
	}
	return 1;
}

//funcao recrusiva que trata o ciframento de cesar... Recebe uma String e retorna outra cifrada.
//Para retornar uma string, vamos modificar a string retorno.
// A funcao recebe os parametros (a string a ser utilizada como base, uma string retorno, e o indice que vai iniciar)
char * ciframentoCesar(char * str, char * retorno, int i){
	if(str[i] == '\0'){
		retorno[i] = '\0';
		return retorno;
	}
	
	retorno[i] = str[i] + KEY;
	return ciframentoCesar(str, retorno, i+1);
}
/*
int main(){
	char entrada[TAM], retorno[TAM];

	while(1){
		scanf(" %[^\n]", entrada);
		getchar(); //consome o \n
		if(strCompare(entrada, "FIM")){
			break;
		}
		printf("%s\n", ciframentoCesar(entrada, retorno, 0));
	}


	return 0;
}
*/

int main(){
    char entrada[TAM], retorno[TAM];

    while(fgets(entrada, TAM, stdin) != NULL){

        int len = lengthStr(entrada);
        if(len > 0 && entrada[len-1] == '\n'){
            entrada[len-1] = '\0';
        }

        if(strCompare(entrada, "FIM")){
            break;
        }

        ciframentoCesar(entrada, retorno, 0);
        printf("%s\n", retorno);
    }

    return 0;
}
