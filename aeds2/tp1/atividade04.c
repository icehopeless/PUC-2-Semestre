#include <stdio.h>
#include <stdlib.h>

//funcao que conta o tamanho da string recebida
int lengthStr(char *str){
	int count = 0;

	while(str[count] != '\0'){
		count++;
	}

	return count; 
}


//funcao que compara uma string com outra passadas por parametro
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


//funcao que recebe uma string e retorna ela inversa
char * reverseStr(char * str){
	int size = lengthStr(str);
	char *out = (char *) malloc((size+1) * sizeof(char));

	for(int i = 0; i < size; i++){
		out[i] = str[size-i-1];		
	}
	out[size] = '\0';

	return out;

}


void main(){
	char str[256];
	//loop que quebra quando a entrada for igual a FIM
	while(1){
		scanf(" %[^\n]", str);
		if(strCompare(str, "FIM")){
			break;
		}

		printf("%s\n", reverseStr(str)); //printa a string inversa
	}

}
