#include <stdlib.h>
#include <stdio.h>

//funcao recursiva que soma todos os termos de um inteiro
int somaRecursiva(int num){
	int soma = 0;
       soma+= num%10;

	if(num < 10){
		return num;
	}
	
	return soma+somaRecursiva(num/10);

}


int main(){
	int num = 0;
	
	//while que quebra quando a o scanf le uma string
	while(1){
		if(scanf("%d", &num) != 1){
			break;
		}

		printf("%d\n", somaRecursiva(num));	
	}

	return 0;
}
