#include <stdio.h>
#include <stdlib.h>
#define TAM 256

//funcao que recebe um caracter e retorna ele em lower 
char lowerChar(char c) {
	if (c >= 'A' && c <= 'Z') {
        	return c + 32;
    	}

   	 return c;
}

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

//funcao que retorna true caso as strings sejam anagramas uma da outra
char * anagramaStr(char *str1, char *str2){
	int usadas[lengthStr(str1)], aux = 0;

	
       if(lengthStr(str1) != lengthStr(str2)){
                return "NAO";
        }

	for(int i = 0; i < lengthStr(str1); i++){
		usadas[i] = 0;
	}	

	for(int i = 0; i < lengthStr(str1); i++){
		aux = 0;
		for(int j = 0; j < lengthStr(str2); j++){
			if(usadas[j] == 0  && lowerChar(str1[i]) == lowerChar(str2[j])){
				aux = 1;
				usadas[j] = 1;
				break;
			}
		}
		if(!aux){
			return "NAO";
		}
	}

	return "SIM";
}


int main(){
    char linha[TAM], str1[TAM], str2[TAM];
    
    while(1){

        scanf(" %[^\n]", linha);
  
        if(strCompare(linha, "FIM")){
            break;
        }

        sscanf(linha, "%s %s", str1, str2);

        printf("%s\n", anagramaStr(str1, str2));
    }    
}