public class No{
	private No prox;
	private Restaurante r;

	public No(){
		prox = null;
		r = new Restaurante();
	}
	public No(Restaurante r){
		this.r = r;
		this.prox = null;
	}

	public No getProx(){
		return this.prox;
	}
	public void setProx(No no){
		this.prox = no;	
	}
	public Restaurante getRestaurante(){
		return this.r;
	}
	public void setRestaurante(Restaurante r){
		this.r = r;
	}

}

public class pilhaFlex{
	private No topo;
	private int tamanho;
	
	public pilhaFlex(){
		this.topo = new No();
		this.tamanho = 0;
	}

	public pilhaFlex(No no, int tam){
		this.topo = no;
		this.tamanho = tam;
	}

	public void setTopo(No no){
		this.topo = no;
	}

	public No getTopo(){
		return this.topo;
	}

	public int getTamanho(){
		return this.tamanho;
	}

	public void setTamanho(int tam){
		this.tamanho = tam;
	}

	public void push(Restaurante r){
		No novo = new No(r);
		No aux = this.topo.getProx();

		novo.setProx(aux);
		this.topo.setProx(novo);

		this.tamanho++;
		
	}

	public Restaurante pop(){
		if(this.topo.getProx() != null){
			Restaurante r = this.topo.getProx().getRestaurante();
	                No aux = this.topo.getProx().getProx();
        	        this.topo.setProx(aux);

                	this.tamanho--;
	                return r;

		}else{
			return null;
		}
	}

	public void print(){
		for(No i = this.topo.getProx(); i!=null; i = i.getProx()){
			System.out.println(i.getRestaurante().getNome());
		}
	}

}



