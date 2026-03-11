#include <stdio.h>
#include <string.h>


char *generic(char *str1, char *str2){
    static char generic[200];

    int i = 0, j = 0, k = 0;

    while(str1[i] != '\0' && str2[j] != '\0'){
        generic[k++] = str1[i++];
        generic[k++] = str2[j++];
    }

    while(str1[i] != '\0'){
        generic[k++] = str1[i++];
    }

    while(str2[j] != '\0'){
        generic[k++] = str2[j++];
    }

    generic[k] = '\0';

    return generic;
}


int main(){
    char entrada[100][100], saida[100][100], str1[100], str2[100];
    int count = 0;

    while(fgets(entrada[count], 100, stdin) != NULL){

        sscanf(entrada[count], "%s %s", str1, str2);

        strcpy(saida[count], generic(str1, str2));

        count++;
    }

    for(int i = 0; i < count; i++){
        printf("%s\n", saida[i]);
    }


    return 0;
}