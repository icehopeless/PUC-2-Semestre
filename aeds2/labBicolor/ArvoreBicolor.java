public class arvoreBinariaRec<T extends Comparable <T>>{
	private No<T> raiz;

	public arvoreBinariaRec(){
		raiz = null;
	}

	public void add(No no, T valor){
		if(no == null){
			return new No(valor);
		}

		if(valor < no.getValor()){
			 add(no.esq, valor);
		}else{
			 add(no.dir, valor);
		}

	}

	public void printEmOrdem(No no){
		if(no == null){
			return;
		}
		printEmOrdem(no.esq);
		System.out.print(no.getValor() + " ");
		printEmOrdem(no.dir);
	}

        public void printPre(No no){
                if(no == null){
                        return;
                }
                System.out.print(no.getValor() + " ");
                printPre(no.esq);
                printPre(no.dir);
        }

        public void printPos(No no){
                if(no == null){
                        return;
                }
                printPos(no.esq);
                printPos(no.dir);
		System.out.print(no.getValor() + " ");

        }

	public void printPaths(No no){
		  printPaths(no, no.getValor());
	}
	public String printPaths(No no, T valor){
		String out;

		if(no == null){
			return "";
		}

		out += no.getValor() + "->";
		out += printPaths(no.esq, no.esq.getValor()) + "->";
		out += printPaths(no.dir, no.dir.getValor() + "->");
		
		return out;

	}

	//inversao de cor
	private void fragmentar(No no){ 
		no.setCor(!no.getCor());
		no.esq.setCor(!no.esq.getCor());
		no.dir.setCor(!no.dir.getCor());
	}
	
	private No rotacionarEsq(No no){
    		No noDir = no.dir;
		No noDirEsq = noDir.esq;


		noDir.esq = no;
		no.dir = noDirEsq;
	
		return no.dir;
    	}

    	private No rotacionarDir(No no){
   		No noEsq = no.esq;
       		No noEsqDir = noEsq.dir;
		
		noEsq.dir = no;
		no.esq = noEsqDir;
		
		return no.esq;	
    	}
   
       	//o no.dir = pai... pois o atual = avo.
   	private No rotacionarDirEsq(No no){
    		no.dir = rotacionarDir(no.dir);
		return rotacionarEsq(no);
    	}

    	private No rotacionarEsqDir(No no){
    		no.esq = rotacionarEsq(no.esq);
		return rotacionarDir(no);
    	}
	
	
public class Main {

    public static void main(String[] args) {

        /*
                 8
               /   \
              3     10
             / \      \
            1   6      14
               / \     /
              4   7   13
        */

        No raiz = new No(8);

        raiz.setEsq(new No(3));
        raiz.setDir(new No(10));

        raiz.getEsq().setEsq(new No(1));
        raiz.getEsq().setDir(new No(6));

        raiz.getEsq().getDir().setEsq(new No(4));
        raiz.getEsq().getDir().setDir(new No(7));

        raiz.getDir().setDir(new No(14));
        raiz.getDir().getDir().setEsq(new No(13));

        Arvore arvore = new Arvore();

        System.out.println("Percurso em ordem:");
        arvore.printEmOrdem(raiz);
    }
}
