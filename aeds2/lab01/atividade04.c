#include <stdio.h>
#include <string.h>


int countMaiusculas(char *str, int i){
		int count = 0;
		if(str[i] >= 65 && str[i] <=90){
			count = 1;
		}
		if(i <= 1){
			count = 0;
			return 0;	
		}
		else{
			return count + countMaiusculas(str, i-1);
		}


	}


int main(){
        char vetor[100][100], entrada[100];
        int count = 0;


        while(1){
            scanf(" %[^\n]", entrada);
            if(strcmp(entrada, "FIM") == 0){
                break;
            }else{
                strcpy(vetor[count], entrada);
            }
            count++;
            getchar();
        }

        for(int i = 0; i < count; i++){
                printf("%d\n", countMaiusculas(vetor[i], strlen(vetor[i]) - 1));
		}

        return 0;
}