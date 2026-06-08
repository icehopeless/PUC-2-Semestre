public class No<T>{
	No<T> esq;
       	No<T> dir;
	private T valor;
	private boolean cor;


	public No(){
		this.valor = null;
		this.esq = null;
		this.dir = null;
		this.cor = false;
	}

	public No(T valor){
		this.valor = valor;
		this.esq = null;
		this.dir = null;
		this.cor = false;
	}

	public T getValor(){
		return this.valor;
	}

	public boolean getCor(){
		return this.cor;
	}

	public void setCor(boolean cor){
		this.cor = cor;
	}
	
	public boolean isTipo4(){
		if(this.esq != null && this.dir != null){
			return (this.esq.cor == true && this.dir.cor) == true ? true : false;
		}		
	}

	
}
