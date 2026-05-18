class Celula{
	private int valor;
	Celula sup, inf, esq, dir;
	
	public Celula(){
		this.sup = null;
		this.inf = null;
		this.esq = null;
		this.dir = null;
	}

	public Celula(int valor){
		this();
		this.valor = valor;
	}

	public int getValor(){
		return this.valor;
	}

	public void setValor(int valor){
		this.valor = valor;
	}
}

public class matrizDinamica{
	private int coluna;
	private int linha;
	Celula inicio;
	
	public matrizDinamica(int l, int c){
		this.coluna = c;
		this.linha = l;
		inicio = new Celula();

		Celula atual = inicio;
		for(int j = 1; j < this.coluna; j++){
			atual.dir = new Celula();
			atual.dir.esq = atual;
			atual = atual.dir;
		}

		Celula linhaCima = inicio;
		for(int i = 1; i < linha; i++){
			linhaCima.inf = new Celula();
			linhaCima.inf.sup = linhaCima;
			Celula novaAtual = linhaCima.inf;
			Celula atualCima = linhaCima;
			
			for(int j = 1; j < coluna; j++){
				novaAtual.dir = new Celula();
				novaAtual.dir.esq = novaAtual;
				novaAtual = novaAtual.dir
				atualCima = atualCima.dir;
				atualCima.inf = novaAtual;
				novaAtual.sup = atualCima;
			}

			linhaCima = linhaCima.inf;

		}		
	}

	public int getColuna(){
		return this.coluna;
	}
	public int getLinha(){
		return this.linha;
	}

	public matrizDinamica somar(Matriz m){
		matrizDinamica resp = new matrizDinamica(this.l, this.c);

		if(this.getColuna() == m.getColuna() && this.getLinha() == m.getLinha()){
			Celula linha1 = this.inicio;
			Celula linha2 = m.inicio;
			Celula rlinha = resp.inicio;

			while(1linha != null){
				Celula a = linha1;
				Celula b = linha2;
				Celula r = rlinha;

				while(a != null){
					r.setValor(a.getValor() + b.getValor());
					a = a.dir;
					b = b.dir;
					r = r.dir;
				}
					linha1 = linha1.inf;
				linha2 = linha2.inf;
				rlinha = rlinha.inf;
			}
		}
		return resp;
	}

	public matrizDinamica multiplicar(Matriz m){
		matrizDinamica resp = new matrizDinamica(this.l, this.c);
		
	}


}
