#include <stdio.h>
#include <stdlib.h>

typedef struct No{
	Restaurante r;
	struct No *prox;
}No;

typedef struct Lista{
	No *inicio;
	int tamanho;
}Lista;

void inserir_inicio(Lista *l, Restaurante *r){
	No *novo = (No *) malloc(sizeof(No));
	novo->r = *r;

	novo->prox = l->inicio->prox;
	l->inicio->prox = novo;

	l->tamanho++;
	
}


void inserir(Lista *l, Restaurante *r, int pos){
	if(pos < 0 || pos == l->tamanho){
		return;
	}

	No *atual = l->inicio;
	No *novo = (No *) malloc(sizeof(No));
	novo->r = *r;

	for(int i = 0; i < pos; i++){
		atual=atual->prox;
	}

	novo->prox = atual->prox;
	atual->prox = novo;

	l->tamanho++;
}

void inserir_fim(Lista *l,Restaurante *r){
	No *atual = l->inicio;
        No *novo = (No *) malloc(sizeof(No));
        novo->r = *r;
	novo->prox = NULL;

	for(int i = 0; i < l->tamanho; i++){
		atual = atual->prox;
	}

	atual->prox = novo;
	l->tamanho++;
}

Restaurante remover_inicio(){}
Restaurante remover(int pos){}
Restaurante remover_fim(){}
