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
			 add(no.getEsq(), valor);
		}else{
			 add(no.getDir(), valor);
		}

	}

	public void printEmOrdem(No no){
		if(no == null){
			return;
		}
		printEmOrdem(no.getEsq());
		System.out.print(no.getValor() + " ");
		printEmOrdem(no.getDir());
	}

        public void printPre(No no){
                if(no == null){
                        return;
                }
                System.out.print(no.getValor() + " ");
                printPre(no.getEsq());
                printPre(no.getDir());
        }

        public void printPos(No no){
                if(no == null){
                        return;
                }
                printPos(no.getEsq());
                printPos(no.getDir());
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

		out += no.getValor + "->";
		out += printPaths(no.getEsq(), no.getEsq().getValor()) + "->";
		out += printPaths(no.getDir(), no.getDir().getValor() + "->");
		
		return out;

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
