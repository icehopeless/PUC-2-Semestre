#include <stdio.h>
#include <string.h>


int countMaiusculas(char *str){
        int count = 0;
        for(int i = 0; i < strlen(str); i++){
                if(str[i] >= 65 && str[i]<= 90){
                        count++;
                }
        }
        return count;
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
                printf("%d\n", countMaiusculas(vetor[i]));
		}

        return 0;
}