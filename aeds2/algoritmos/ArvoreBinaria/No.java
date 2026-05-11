public class No<T>{
	private No<T> esq;
       	private No<T> dir;
	private T valor;

	public No(T valor){
		this.valor = valor;
		this.esq = null;
		this.dir = null;
	}

	public T getValor(){
		return this.valor;
	}

	public No<T> getEsq(){
		return this.esq;
	}

	public No<T> getDir(){
		return this.dir;
	}

	public void setDir(No<T> dir){
		this.dir = dir;
	}

	public void setEsq(No<T> esq){
            this.esq = esq;
        }

}
