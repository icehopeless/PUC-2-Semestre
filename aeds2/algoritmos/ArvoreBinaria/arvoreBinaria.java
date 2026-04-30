import java.util.*;
import java.io.*;

public class arvoreBinaria<T extends Comparable<T>>{
	Elemento<T> raiz;

	public arvoreBinaria(){
		raiz = null;
	}
	public boolean search(T valor){
		Elemento <T>elemento = this.raiz;
		while(elemento != null){
			if(valor.compareTo(elemento.getValor()) == 0){
				return true;
			}
			else if(valor.compareTo(elemento.getValor()) < 0){
				elemento = elemento.getEsq();
			}
			else if (valor.compareTo(elemento.getValor()) > 0){
				elemento = elemento.getDir();
			}
		}
		return false;
	}

	public boolean add(T valor){
		Elemento <T>elemento = new Elemento<T>(valor);

		if(this.raiz == null){
			this.raiz = elemento;
			return true;
		}

		Elemento<T> atual = this.raiz;
		Elemento<T> pai = null;
		while(atual != null){
			pai = atual;

			if(valor.compareTo(atual.getValor()) < 0){
				atual = elemento.getEsq();
			}
			else if (valor.compareTo(atual.getValor()) > 0){
				atual = elemento.getDir();
			}
		}

		if(valor.compareTo(pai.getValor()) < 0){
			pai.setEsq(elemento);
		}else{
			pai.setDir(elemento);
		}

		return true;
	}

	public boolean remove(T valor){
		Elemento<T> elemento  = new Elemento<T>(valor);
		Elemento<T> atual = this.raiz;
		Elemento<T> pai = null;

		if(atual.getValor() == valor){
			pai = atual;
			atual = atual.getDir();
			while(atual.getEsq()!= null){
				atual = atual.getEsq();
			}
			pai = atual;	
		}
	}

	void print(){
	
	}

}


