public class No{
	No esq;
	No dir;
	private int valor;
	private boolean cor;


	public No(){
		this.valor = 0;
		this.esq = null;
		this.dir = null;
		this.cor = false;
	}

	public No(int valor){
		this.valor = valor;
		this.esq = null;
		this.dir = null;
		this.cor = false;
	}

	public int getValor(){
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
		return false;
	}

	
}
