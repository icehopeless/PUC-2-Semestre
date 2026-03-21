#include <stdio.h> 
#include <stdlib.h>

//funcao que recebe um caracter e converte ele para lower.
//pega a diferenca entre A e Z, e soma. Dessa forma, retorna ao requivalente lower
char lowerCase(char chr){
	if(chr >= 'A' && chr <= 'Z'){
		return chr+32;
	}else{
		return chr;
	}

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

//recebe um caracter e verifica se e uma letra ou nao
int isLetter(char c){
        if((c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z')){
                return 1;
        }
        return 0;
}


//recebe um caracter e verifica se e uma vogal ou nao
int compareCaseVogal(char c){
	if(!isLetter(c)){
		return 0;
	}
        if((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')){
                return 1;
        }
	return 0;

}

//funcao recursiva que verifica todos os caracteres da string. Caso sejam todas vogais, retorna true;
//A primeira condicao verifica se e vogal, se nao for, ja retorna.
//a segunda verifica se chegou no fim da string, se tiver chegado sem quebrar na priemira condicao, sao todas vogais
//caso a segunda nao seja satisfeita, chama recursiva para o proximo caracter
int isVogal(char * str, int i){
	char c = lowerCase(str[i]);
	
	//caso mais fundamental.
	if(!compareCaseVogal(c)){
		return 0;	
	}

	if(i == lengthStr(str)-1){
		return 1;
	}

	return isVogal(str, i+1);
}


//mesma ideia da funcao isVogal, porem, para consoantes. Retorna true se a string for composta somente por consoantes
int isConsoante(char * str, int i){
        char c = lowerCase(str[i]);

        //caso mais fundamental.
        if(!(isLetter(c) && !compareCaseVogal(c))){
                return 0;
        }

        if(i == lengthStr(str)-1){
                return 1;
        }

        return isConsoante(str, i+1);
}

//funcao recursiva para verificar se a string e um inteiro (composta por numeros e sem . ou ,)
int isInteiro(char * str, int i){
	char c = str[i];
	if(!(c >= '0' && c <= '9')){
		return 0;
	}

	if(i == lengthStr(str)-1){
                return 1;
        }

        return isInteiro(str, i+1);
}
//funcao recursiva  para verificar se a string e um numero real
int isReal(char * str, int i, int point){
        char c = str[i];

        if(!((c >= '0' && c <= '9') || (c == ',' || c == '.'))){
		return 0;
        }

        if(c == ',' || c == '.'){
		if(point){
			return 0;
		}
                point = 1;
        }

	
        if(i == lengthStr(str)-1){
                return 1;
        }

        return isReal(str, i+1, point);
}


int main(){
	char entrada[1000], saida[16];

	while(1){
		scanf(" %[^\n]", entrada);
		getchar(); 
		if(strCompare(entrada, "FIM")){
			break;
		}

		if(isVogal(entrada,0)){
			saida[0] = 'S';
			saida[1] = 'I';
			saida[2] = 'M';
			saida[3] = ' ';
		}else{
                        saida[0] = 'N';
                        saida[1] = 'A';
                        saida[2] = 'O';
                        saida[3] = ' ';

		}

                if(isConsoante(entrada,0)){
                        saida[4] = 'S';
                        saida[5] = 'I';
                        saida[6] = 'M';
                        saida[7] = ' ';
                }else{
                        saida[4] = 'N';
                        saida[5] = 'A';
                        saida[6] = 'O';
                        saida[7] = ' ';

                }

                if(isInteiro(entrada,0)){
                        saida[8] = 'S';
                        saida[9] = 'I';
                        saida[10] = 'M';
                        saida[11] = ' ';
                }else{
                        saida[8] = 'N';
                        saida[9] = 'A';
                        saida[10] = 'O';
                        saida[11] = ' ';

                }

                if(isReal(entrada,0, 0)){
                        saida[12] = 'S';
                        saida[13] = 'I';
                        saida[14] = 'M';

                }else{
                        saida[12] = 'N';
                        saida[13] = 'A';
                        saida[14] = 'O';
                }


		printf("%s\n", saida);
	}

	return 0;
}
