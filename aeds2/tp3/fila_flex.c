#include <stdio.h>
#include <stdlib.h>

typedef struct No{
	Restaurante r;
	struct No *prox;
}No;

typedef struct Fila{
	No *inicio;
	int tamanho;
}Fila;


void inserir(Fila *f, Restaurante *r){
	No *novo = (No *) malloc(sizeof(No));
	novo->r = *r;
	novo->prox = NULL;
	No *atual = f->inicio;
	for(int i = 0; i < f->tamanho; i++){
		atual = atual->prox;
	}
	
	atual->prox = novo;
	f->tamanho++;
}

Restaurante remover(Fila *f){
	Restaurante r;
	No *aux = f->inicio->prox;
	r = aux->r;
	f->inicio->prox = aux->prox;

	f->tamanho--;
	free(aux);
	
	return r;
}

void printNome(Fila *f){
	No *atual = f->inicio;
	for(int i = 0; i < f->tamanho; i++){
		printf("%s\n",atual->r->nome);
	}
}

void print(Fila *f, char * buffer){
        No *atual = f->inicio;
        for(int i = 0; i < f->tamanho; i++){
                formatar_restaurante(atual->r, buffer);
		printf("%s", buffer);
        }
}




