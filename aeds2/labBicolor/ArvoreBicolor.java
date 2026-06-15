public class ArvoreBicolor {
	private No raiz;

	public ArvoreBicolor() {
		raiz = null;
	}

	public No getRaiz(){
		return this.raiz;
	}

	/*public No add(No no, int valor) {
		if (no == null) {
			return new No(valor);
		}

		if (valor < no.getValor()) {
			no.esq = add(no.esq, valor);
		} else {
			no.dir = add(no.dir, valor);
		}

		return no;
	}
	*/

	public void printEmOrdem(No no) {
		if (no == null) {
			return;
		}
		printEmOrdem(no.esq);
		System.out.print(no.getValor() + " ");
		printEmOrdem(no.dir);
	}

	public void printPre(No no) {
		if (no == null) {
			return;
		}
		System.out.print(no.getValor() + " ");
		printPre(no.esq);
		printPre(no.dir);
	}

	public void printPos(No no) {
		if (no == null) {
			return;
		}
		printPos(no.esq);
		printPos(no.dir);
		System.out.print(no.getValor() + " ");

	}

	public void printPaths(No no) {
		printPaths(no, no.getValor());
	}

	public String printPaths(No no, int valor) {
		String out = "";

		if (no == null) {
			return "";
		}

		out += no.getValor() + "->";
		out += printPaths(no.esq, no.esq.getValor()) + "->";
		out += printPaths(no.dir, no.dir.getValor()) + "->";

		return out;

	}

	// inversao de cor
	private void fragmentar(No no) {
		no.setCor(!no.getCor());
		no.esq.setCor(!no.esq.getCor());
		no.dir.setCor(!no.dir.getCor());
	}

	private No rotacionarEsq(No no) {
		No noDir = no.dir;
		No noDirEsq = noDir.esq;

		noDir.esq = no;
		no.dir = noDirEsq;

		return noDir;
	}

	private No rotacionarDir(No no) {
		No noEsq = no.esq;
		No noEsqDir = noEsq.dir;

		noEsq.dir = no;
		no.esq = noEsqDir;

		return noEsq;
	}

	// o no.dir = pai... pois o atual = avo.
	private No rotacionarDirEsq(No no) {
		no.dir = rotacionarDir(no.dir);
		return rotacionarEsq(no);
	}

	private No rotacionarEsqDir(No no) {
		no.esq = rotacionarEsq(no.esq);
		return rotacionarDir(no);
	}

	private No balancear(No bisavo, No avo, No pai, No atual) {
		No novaRaiz = new No();

		if (avo.getValor() > pai.getValor() && pai.getValor() > atual.getValor()) {
			novaRaiz = rotacionarDir(avo);
		}

		else if (avo.getValor() > pai.getValor() && pai.getValor() < atual.getValor()) {
			avo.esq = rotacionarEsq(pai);
			novaRaiz = rotacionarDir(avo);
		}

		else if (avo.getValor() < pai.getValor() && pai.getValor() < atual.getValor()) {
			novaRaiz = rotacionarEsq(avo);
		}

		else if (avo.getValor() < pai.getValor() && pai.getValor() > atual.getValor()) {
			avo.dir = rotacionarDir(pai);
			novaRaiz = rotacionarEsq(avo);
		}

		novaRaiz.dir.setCor(true);
		novaRaiz.esq.setCor(true);
		if (bisavo == null) {
			return novaRaiz;
		}

		if (bisavo.esq == avo) {
			bisavo.esq = novaRaiz;
		} else {
			bisavo.dir = novaRaiz;
		}

		return novaRaiz;
	}

	public void add(int x){
		if(raiz == null){
			raiz = new No(x);
		}else{
			add(null, null, null, raiz, x);
		}
	}

	private No add(No bisavo, No avo, No pai, No no, int valor){
        if (no == null) {
            No novo = new No(valor);
            novo.setCor(true);

            if (pai.getValor() > valor) {
                pai.esq = novo;
            } else {
                pai.dir = novo;
            }
            if (pai.getCor()) {
                balancear(bisavo, avo, pai, novo);
            }
            return;
        }

		if(no.isTipo4()){
			fragmentar(no);

			if(no == raiz) no.setCor(false);
			else if(pai != null && pai.getCor()){
				balancear(bisavo, avo, pai, no);
			}
		}

		if(valor < no.getValor()){
			add(avo, pai, no, no.esq, valor);
		}else{
			add(avo, pai, no, no.dir, valor);
		}

	}
}

class Main {
    public static void main(String[] args) {
        ArvoreBicolor arvore = new ArvoreBicolor();
 
        int[] valores = {4, 35, 10, 13, 3, 30, 15, 12, 7, 40, 20};
        for (int v : valores) {
            arvore.add(v);
        }
 
        System.out.println("Caminhamento em ordem:");
        arvore.printEmOrdem(arvore.getRaiz());
        System.out.println();
    }
}
