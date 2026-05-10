#include <stdio.h>
#include <stdlib.h>

typedef struct No{
	Restaurante *r;
	struct No *prox;
}No;

typedef struct Lista{
	No *inicio;
	int tamanho;
}Lista;

void inserir_inicio(Lista *l, Restaurante *r){
	No *novo = (No *) malloc(sizeof(No));
	novo->r = r;

	novo->prox = l->inicio->prox;
	l->inicio->prox = novo;

	l->tamanho++;
	free(novo);
}


void inserir(Lista *l, Restaurante *r, int pos){
	if(pos < 0 || pos == l->tamanho){
		return;
	}

	No *atual = l->inicio;
	No *novo = (No *) malloc(sizeof(No));
	novo->r = r;

	for(int i = 0; i < pos; i++){
		atual=atual->prox;
	}

	novo->prox = atual->prox;
	atual->prox = novo;

	l->tamanho++;
	free(novo);
}

void inserir_fim(Lista *l,Restaurante *r){
	No *atual = l->inicio;
        No *novo = (No *) malloc(sizeof(No));
        novo->r = r;
	novo->prox = NULL;

	for(int i = 0; i < l->tamanho; i++){
		atual = atual->prox;
	}

	atual->prox = novo;
	l->tamanho++;
	free(novo);
}

Restaurante remover_inicio(Lista *l){
	Restaurante *r;
	No *aux = l->inicio->prox;
	r = aux->r;
	l->inicio->prox = aux->prox;


	l->tamanho--;
	free(aux);
	return r;
}

Restaurante remover(Lista *l, int pos){
	Restaurante *r;
	No *aux;
	No *atual = l->inicio;

	for(int i = 0; i < pos; i++){
		atual = atual->prox;
	}
	
	aux = atual->prox;
	atual->prox = aux->prox;
	free(aux);
	l->tamanho--;
	return r;
}

Restaurante remover_fim(Lista *l){
        Restaurante r;
        No *atual = l->inicio;
	No *aux;
        for(int i = 0; i < l->tamanho-1; i++){
                atual = atual->prox;
        }

        aux = atual->prox;
	r = aux->r;

	atual->prox = NULL;
	free(aux);
        l->tamanho--;
        return r;

}

void printNome(Lista *l){
	No *atual = l->inicio;
	for(int i = 0; i < l->tamanho; i++){
		printf("%s\n",atual->r->nome);
	}
}

void print(Lista *l, char * buffer){
        No *atual = l->inicio;
        for(int i = 0; i < l->tamanho; i++){
                formatar_restaurante(atual->r, buffer);
		printf("%s", buffer);
        }
}

void select_sort(Lista *l){
	for(No *i = l->inicio->prox; i!=NULL; i = i->prox){
		No *menor = i;
		for(No *j = i->prox; j != NULL; j = j->prox){
			comparacoes++;
			if(strcmp(menor->r->nome, j->r->nome) > 0){
				menor = j;
			}
		}
	Restaurante *tmp = i->r;
        i->r = menor->r;
        menor->r = tmp;
	movimentacoes+=3;

	}
}


