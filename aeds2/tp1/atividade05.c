#include <stdlib.h>
#include <stdio.h>

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

	while(1){
		if(scanf("%d", &num) != 1){
			break;
		}

		printf("%d\n", somaRecursiva(num));	
	}

	return 0;
}
