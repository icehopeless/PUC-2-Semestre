class No{
	private No right;
	private No left;
	private Restaurante r;

	public No(){
		right = null;
		left = null;
		r = new Restaurante();
	}
	public No(Restaurante r){
		this.r = r;
		this.right = null;
		this.left = null;
	}

	public No getRight(){
		return this.right;
	}
	public void setRight(No no){
		this.right = no;	
	}
	public No getLeft(){
		return this.left;
	}
	public void setLeft(No no){
		this.left = no;
	}
	public Restaurante getRestaurante(){
		return this.r;
	}
	public void setRestaurante(Restaurante r){
		this.r = r;
	}

}

class listaDupla{
	private int tamanho;
	private No inicio;
	private No fim;
	
	public listaDupla(){
		this.inicio = new No();
		this.fim = new No();
		this.inicio.setRight(fim);
		this.fim.setLeft(this.inicio);
		this.tamanho = 0;
	}

	public listaDupla(No inicio, No fim, int tam){
		this.inicio = inicio;
		this.fim = fim;
		this.tamanho = tam;
	}

	public No getInicio(){
		return this.topo;
	}

	public No setInicio(No no){
		this.inicio = no;
	}

	public No getFim(){
		return this.fim;
	}

	public void setFim(No no){
		this.fim = no;
	}

	public int getTamanho(){
		return this.tamanho;
	}

	public void setTamanho(int tam){
		this.tamanho = tam;
	}
	
	public void inserir(Restaurante r, int pos){
		if(pos > this.tamanho){
			return;
		}

		No novo = new No(r);
		No atual = this.inicio;
		for(int i = 0; i < pos; i++){
			atual = atual.getRight();
		}

		No aux = atual.getRight();
		novo.setLeft(atual);
		atual.setRight(novo);
		novo.setRight(aux);

		this.tamanho++;
		
	}

	public void inserirInicio(Restaurante r){
		No novo = new No(r);
		
		No aux = this.inicio.getRight();
		novo.setLeft(this.inicio);
		this.inicio.setRight(novo);
		novo.setRight(aux);
		aux.setLeft(novo);	


		this.tamanho++;
	}

	public void inserir_fim(Restaurante r){
		No novo = new No(r);
		
		No aux = this.fim.getLeft();
		novo.setRight(this.fim);
		novo.setLeft(aux);
		aux.setRight(novo);
		this.fim.setLeft(novo);

		this.tamanho++;
		
	}

	public Restaurante remover(int pos){
		if(pos >= this.tamanho || pos < 0){
			return null;
		}
		
		No atual;

		if(pos < this.tamanho/2){
			atual = this.inicio.getRight();
			for(int i = 0; i < pos; i++){
				atual = atual.getRight();
			}
		}else{
			atual = this.fim;
			for(int i = this.tamanho; i > pos; i--){
                                atual = atual.getLeft();
                        }
		}
		Restaurante r = atual.getRestaurante();
		No aux = atual.getRight();
		No aux2 = atual.getLeft();
		aux2.setRight(aux);
		aux.setLeft(aux2);
		

		this.tamanho--;
		return r;

	}

	public Restaurante remover_inicio(){
		if(this.inicio.getRight() == this.fim){
 			return null;
		}
		
		Restaurante r = inicio.getRight().getRestaurante();
		No aux = this.inicio.getRight().getRight();
		this.inicio.setRight(aux);
		aux.setLeft(this.inicio);
		
		this.tamanho--;
		return r;

	}

	public Restaurante remover_fim(){
                if(this.inicio.getRight() == this.fim){
                        return null;
                }

                Restaurante r = fim.getLeft().getRestaurante();
                No aux = this.fim.getLeft().getLeft();
                this.fim.setLeft(aux);
                aux.setRight(this.fim);

                this.tamanho--;
                return r;

	}

	public void printNome(){
		for(No i = this.topo.getProx(); i!=null; i = i.getProx()){
			System.out.println(i.getRestaurante().getNome());
		}
	
	}

	public void print(){
                for(No i = this.topo.getProx(); i!=null; i = i.getProx()){
                        System.out.println(i.getRestaurante().formatar());
                }

	}
}

public class quickFlex{
	 public void quickSort(listaDupla l){
	 	if(l->tamanho > 0){
			quickSort(l.getInicio(), l.getFim());
		}
	 }
	
	 public void quickSort(No esq, No dir){
		No i = esq;
		No j = dir;
		No pivo = dir;

		while(i != j && i.getLeft() != j){
			while(i.getRestaurante().getAvaliacao() < pivo.getRestaurante().getAvaliacao || j.getRestaurante().getAvaliacao() == pivo.getRestaurante().getAvaliacao() && j.getRestaurante().compareTo(pivo.getRestaurante().getNome()) < 0) {
				i = i.getRight(); //para quando i for maior que o pivo	
			}

			while(i.getRestaurante().getAvaliacao() > pivo.getRestaurante().getAvaliacao || j.getRestaurante().getAvaliacao() == pivo.getRestaurante().getAvaliacao() && j.getRestaurante().compareTo(pivo.getRestaurante().getNome()) > 0){
				j = j.getLeft(); //para qunado j for menor que o pivo
			}
			
			if(i != j && i.getLeft() != j){
				Restaurante tmp = i.getRestaurant();

				i.setRestaurante(j.getRestaurante());
				j.setRestaurante(tmp);

				i = i.getRight();
				j = j.getLeft();
			}	

		}

		if(esq != j){
			quickSort(esq, j);
		}

		if(i != dir){
			quickSort(i, dir);
		}
	 }

}
