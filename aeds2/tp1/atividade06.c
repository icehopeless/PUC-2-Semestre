#include <stdio.h>
#include <stdlib.h>
#define TAM 256;

int lengthStr(char *str){
	int count = 0;

	while(str[count] != '\0'){
		count++;
	}

	return count;
}

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

char * anagramaStr(char *str1, char *str2){


}


int main(){
	char str1[TAM], str2[TAM];

	while(1){
		if(strCompare(str1,"FIM") != 1 || strCompare(str2,"FIM")!= 1){
			
		}
	}	
}


