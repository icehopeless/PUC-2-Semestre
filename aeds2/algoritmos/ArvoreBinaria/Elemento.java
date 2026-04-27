public class Elemento<T>{
	private Elemento<T> esq;
       	private Elemento<T> dir;
	private T valor;

	public Elemento(T valor){
		this.valor = valor;
		this.esq = null;
		this.direita = null;
	}

	public T getValor(){
		return this.valor;
	}

	public Elemento<T> getEsq(){
		return this.esq;
	}

	public Elemento<T> getDir(){
		return this.dir;
	}

	public setDir(Elemento<T> dir){
		this.dir = dir;
	}

	public setEsq(Elemento<T> esq){
                this.esq = esq;
        }

}
