#include <stdio.h>
#include <stdlib.h>

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

//funcao que retorna o tamanho da maior substring contida na string recebida por parametro 
/*int subStr(char * str){
	int len = lengthStr(str);
	char aux[len];
	int start = 0;
	int repeat = 0;
	int count = 0;

	for(int i = 0; i < len; i++){
		repeat = 0;
		for(int j = start; j < i; j++){
			if(str[i] == str[j]){
				start = j+1;
				repeat = 1;
				break;
			}
		}
		if(!repeat){
			aux[count] = str[i];	
			count++;
		}
		
	}

	aux[count] = '\0';
	printf("%s\n", aux);
	return lengthStr(aux);

}
*/
//funcao que retorna o tamanho da maior substring contida na string recebida por parametro 
int subStr(char * str){
    int len = lengthStr(str);
    int start = 0;
    int maior = 0;
    int repeat = 0;
    int atual = 0;

    for(int i = 0; i < len; i++){
         repeat = 0;
        for(int j = start; j < i; j++){
            if(str[i] == str[j]){
                start = j + 1;
                repeat = 1;
                break;
            }
        }

        atual = i - start + 1;

        if(atual > maior){
            maior = atual;
        }
    }

    return maior;
}


int main(){
	char str[256];

	while(1){
		scanf(" %[^\n]", str);
		if(strCompare(str, "FIM")){
			break;
		}

		printf("%d\n", subStr(str));
	}

	return 0;
}
