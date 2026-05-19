import java.io.*;
import java.util.*;

class stringUtils {
	protected static String[] splitS(String s, char regex) {
		int parts = 1;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == regex) parts++;
		}
		String[] slices = new String[parts];
		String atual = "";
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			char caracter = s.charAt(i);
			if (caracter == regex) {
				slices[count++] = atual;
				atual = "";
			} else {
				atual += caracter;
			}
		}
		slices[count] = atual;
		return slices;
	}

	protected static int countChar(String s, char regex) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == regex) count++;
		}
		return count;
	}

	protected String arrayToString(String[] array) {
		String s = "[";
		for (int i = 0; i < array.length; i++) {
			s += array[i];
			if (i < array.length - 1) s += ",";
		}
		s += "]";
		return s;
	}
}

class Hora extends stringUtils {
	private int hora;
	private int minuto;

	public Hora() {
		this.hora = 0;
		this.minuto = 0;
	}

	public Hora(int hora, int minuto) {
		this.hora = hora;
		this.minuto = minuto;
	}

	public static Hora parseHora(String s) {
		String[] rtn = splitS(s, ':');
		return new Hora(Integer.parseInt(rtn[0]), Integer.parseInt(rtn[1]));
	}

	public String formatar() {
		return String.format("%02d:%02d", this.hora, this.minuto);
	}
}

class Data extends stringUtils {
	private int dia;
	private int mes;
	private int ano;

	public Data() {
		dia = 0;
		mes = 0;
		ano = 0;
	}

	public Data(int dia, int mes, int ano) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public static Data parseData(String s) {
		String[] rtn = splitS(s, '-');
		return new Data(Integer.parseInt(rtn[2]), Integer.parseInt(rtn[1]), Integer.parseInt(rtn[0]));
	}

	public String formatar() {
		return String.format("%02d/%02d/%d", this.dia, this.mes, this.ano);
	}
}

class Restaurante extends stringUtils {
	private int id;
	private String nome;
	private String cidade;
	private int capacidade;
	private double avaliacao;
	private String[] tipos_cozinha;
	private int faixa_preco;
	private Hora horario_abertura;
	private Hora horario_fechamento;
	private Data data_abertura;
	private boolean aberto;

	public Restaurante(int id, String nome, String cidade, int capacidade, double avaliacao, String[] tipos_cozinha, int faixa_preco, Hora horario_abertura, Hora horario_fechamento, Data data_abertura, boolean aberto) {
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.capacidade = capacidade;
		this.avaliacao = avaliacao;
		this.tipos_cozinha = new String[tipos_cozinha.length];
		for (int i = 0; i < tipos_cozinha.length; i++) {
			this.tipos_cozinha[i] = tipos_cozinha[i];
		}
		this.faixa_preco = faixa_preco;
		this.horario_abertura = horario_abertura;
		this.horario_fechamento = horario_fechamento;
		this.data_abertura = data_abertura;
		this.aberto = aberto;
	}

	public static Restaurante parseRestaurante(String s) {
		String[] slices = splitS(s, ',');
		String[] horas = splitS(slices[7], '-');
		return new Restaurante(Integer.parseInt(slices[0]), slices[1], slices[2], Integer.parseInt(slices[3]), Double.parseDouble(slices[4]), splitS(slices[5], ';'), countChar(slices[6], '$'), Hora.parseHora(horas[0]), Hora.parseHora(horas[1]), Data.parseData(slices[8]), Boolean.parseBoolean(slices[9]));
	}

	public int getId() { return this.id; }
	public String getNome() { return this.nome; }
	public String getCidade() { return this.cidade; }
	public int getCapacidade() { return this.capacidade; }
	public double getAvaliacao() { return this.avaliacao; }
	public String[] getTiposCozinha() { return this.tipos_cozinha; }
	public Hora getHorarioAbertura() { return this.horario_abertura; }
	public Hora getHorarioFechamento() { return this.horario_fechamento; }
	public Data getDataAbertura() { return this.data_abertura; }
	public boolean getAberto() { return this.aberto; }

	public String formatar() {
		String s = "";
		for (int i = 0; i < this.faixa_preco; i++) s += "$";
		return "[" + this.id + " ## " + this.nome + " ## " + this.cidade + " ## " + this.capacidade + " ## " + this.avaliacao + " ## " + arrayToString(this.tipos_cozinha) + " ## " + s + " ## " + this.horario_abertura.formatar() + "-" + this.horario_fechamento.formatar() + " ## " + this.data_abertura.formatar() + " ## " + this.aberto + "]";
	}
}

class ColecaoRestaurantes extends stringUtils {
	private int tamanho;
	private Restaurante[] restaurantes;

	public ColecaoRestaurantes() {
		this.tamanho = 0;
		this.restaurantes = new Restaurante[10];
	}

	public int getTamanho() { return this.tamanho; }
	public Restaurante[] getRestaurantes() { return this.restaurantes; }

	private void aumentarCapacidade() {
		Restaurante[] novo = new Restaurante[this.restaurantes.length * 2];
		for (int i = 0; i < this.restaurantes.length; i++) {
			novo[i] = this.restaurantes[i];
		}
		this.restaurantes = novo;
	}

	public void readCsv(String path) {
		try {
			Scanner scan = new Scanner(new File(path));
			scan.nextLine();
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				if (line.length() == 0) break;
				if (tamanho == restaurantes.length) aumentarCapacidade();
				this.restaurantes[tamanho] = Restaurante.parseRestaurante(line);
				tamanho++;
			}
			scan.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao ler o arquivo");
		}
	}

	public static ColecaoRestaurantes readCsv() {
		ColecaoRestaurantes colecao = new ColecaoRestaurantes();
		colecao.readCsv("/tmp/restaurantes.csv");
		return colecao;
	}
}

class No {
	private No right;
	private No left;
	private Restaurante r;

	public No() {
		right = null;
		left = null;
		r = null;
	}

	public No(Restaurante r) {
		this.r = r;
		this.right = null;
		this.left = null;
	}

	public No getRight() { return this.right; }
	public void setRight(No no) { this.right = no; }
	public No getLeft() { return this.left; }
	public void setLeft(No no) { this.left = no; }
	public Restaurante getRestaurante() { return this.r; }
	public void setRestaurante(Restaurante r) { this.r = r; }
}

class listaDupla {
	private int tamanho;
	private No inicio;
	private No fim;

	public listaDupla() {
		this.inicio = new No();
		this.fim = new No();
		this.inicio.setRight(fim);
		this.fim.setLeft(this.inicio);
		this.tamanho = 0;
	}

	public No getInicio() { return this.inicio; }
	public void setInicio(No no) { this.inicio = no; }
	public No getFim() { return this.fim; }
	public void setFim(No no) { this.fim = no; }
	public int getTamanho() { return this.tamanho; }
	public void setTamanho(int tam) { this.tamanho = tam; }

	public void inserir(Restaurante r, int pos) {
		if (pos > this.tamanho || pos < 0) return;
		No novo = new No(r);
		No atual = this.inicio;
		for (int i = 0; i < pos; i++) {
			atual = atual.getRight();
		}
		No aux = atual.getRight();
		novo.setLeft(atual);
		atual.setRight(novo);
		novo.setRight(aux);
		aux.setLeft(novo);
		this.tamanho++;
	}

	public void inserirInicio(Restaurante r) {
		No novo = new No(r);
		No aux = this.inicio.getRight();
		novo.setLeft(this.inicio);
		this.inicio.setRight(novo);
		novo.setRight(aux);
		aux.setLeft(novo);
		this.tamanho++;
	}

	public void inserirFim(Restaurante r) {
		No novo = new No(r);
		No aux = this.fim.getLeft();
		novo.setRight(this.fim);
		novo.setLeft(aux);
		aux.setRight(novo);
		this.fim.setLeft(novo);
		this.tamanho++;
	}

	public Restaurante remover(int pos) {
		if (pos >= this.tamanho || pos < 0) return null;
		No atual;
		if (pos < this.tamanho / 2) {
			atual = this.inicio.getRight();
			for (int i = 0; i < pos; i++) atual = atual.getRight();
		} else {
			atual = this.fim;
			for (int i = this.tamanho; i > pos; i--) atual = atual.getLeft();
		}
		Restaurante r = atual.getRestaurante();
		No esq = atual.getLeft();
		No dir = atual.getRight();
		esq.setRight(dir);
		dir.setLeft(esq);
		this.tamanho--;
		return r;
	}

	public Restaurante removerInicio() {
		if (this.inicio.getRight() == this.fim) return null;
		Restaurante r = this.inicio.getRight().getRestaurante();
		No aux = this.inicio.getRight().getRight();
		this.inicio.setRight(aux);
		aux.setLeft(this.inicio);
		this.tamanho--;
		return r;
	}

	public Restaurante removerFim() {
		if (this.inicio.getRight() == this.fim) return null;
		Restaurante r = this.fim.getLeft().getRestaurante();
		No aux = this.fim.getLeft().getLeft();
		this.fim.setLeft(aux);
		aux.setRight(this.fim);
		this.tamanho--;
		return r;
	}

	public void printNome() {
		for (No i = this.inicio.getRight(); i != this.fim; i = i.getRight()) {
			System.out.println(i.getRestaurante().getNome());
		}
	}

	public void print() {
		for (No i = this.inicio.getRight(); i != this.fim; i = i.getRight()) {
			System.out.println(i.getRestaurante().formatar());
		}
	}
}

/*public class quickFlex {

	private static int comparar(Restaurante a, Restaurante b) {
		if (a.getAvaliacao() != b.getAvaliacao()) {
			return Double.compare(a.getAvaliacao(), b.getAvaliacao());
		}
		return a.getNome().compareTo(b.getNome());
	}

	public static void quickSort(listaDupla l) {
		if (l.getTamanho() > 1) {
			quickSort(l.getInicio().getRight(), l.getFim().getLeft());
		}
	}

	private static void quickSort(No esq, No dir) {
		if (esq == dir || dir.getRight() == esq) return;
		No i = esq;
		No j = dir;
		Restaurante pivo = dir.getRestaurante();
		while (true) {
			while (i != dir && comparar(i.getRestaurante(), pivo) < 0) {
				i = i.getRight();
			}
			while (j != esq && comparar(j.getRestaurante(), pivo) > 0) {
				j = j.getLeft();
			}
			if (i == j || i.getLeft() == j) break;
			Restaurante tmp = i.getRestaurante();
			i.setRestaurante(j.getRestaurante());
			j.setRestaurante(tmp);
			i = i.getRight();
			j = j.getLeft();
		}
		Restaurante tmp = i.getRestaurante();
		i.setRestaurante(dir.getRestaurante());
		dir.setRestaurante(tmp);
		if (i != esq && esq != i.getLeft()) {
			quickSort(esq, i.getLeft());
		}
		if (i != dir) {
			quickSort(i.getRight(), dir);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ColecaoRestaurantes colecao = ColecaoRestaurantes.readCsv();
		listaDupla lista = new listaDupla();
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			if (n <= 0) break;
			if (n <= colecao.getTamanho()) {
				lista.inserirFim(colecao.getRestaurantes()[n - 1]);
			}
		}
		long inicio = System.nanoTime();
		quickSort(lista);
		long fim = System.nanoTime();
		double tempo = (fim - inicio) / 1e9;
		lista.print();
		try {
			FileWriter fw = new FileWriter("quick_sort_log.txt", true);
			fw.write("\ttempo de execucao: " + tempo + "\n");
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}*/

public class quickFlex {
    private static int comparar(Restaurante a, Restaurante b) {
        if (a.getAvaliacao() != b.getAvaliacao()) {
            return Double.compare(a.getAvaliacao(), b.getAvaliacao());
        }
        return a.getNome().compareTo(b.getNome());
    }

  
    public static void quickSort(listaDupla l) {
        if (l.getTamanho() > 1) {
            quickSort(l.getInicio().getRight(), l.getFim().getLeft());
        }
    }


    private static void quickSort(No esq, No dir) {
        if (esq == null || dir == null || esq == dir || dir.getRight() == esq) {
            return;
        }

      
        No i = esq;
        No j = dir;
        Restaurante pivo = dir.getRestaurante(); 

        while (i != j && i.getLeft() != j) {
            
          
            while (i != j && comparar(i.getRestaurante(), pivo) < 0) {
                i = i.getRight();
            }

            
            while (j != i && comparar(j.getRestaurante(), pivo) > 0) {
                j = j.getLeft();
            }


            if (i != j && i.getLeft() != j) {
                Restaurante tmp = i.getRestaurante();
                i.setRestaurante(j.getRestaurante());
                j.setRestaurante(tmp);

          
                if (comparar(i.getRestaurante(), j.getRestaurante()) == 0 && i != j) {
                    i = i.getRight();
                }
            }
        }


        if (esq != i && esq != i.getLeft()) {
            quickSort(esq, i.getLeft());
        }
    
        if (i != dir && i.getRight() != dir.getRight()) {
            quickSort(i.getRight(), dir);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColecaoRestaurantes colecao = ColecaoRestaurantes.readCsv();
        listaDupla lista = new listaDupla();

   
        while (sc.hasNextLine()) {
            String linha = sc.nextLine().trim();
            if (linha.length() == 0) continue;
            
            if (linha.equals("-1")) {
                break;
            }

            int id = Integer.parseInt(linha);

         
            Restaurante encontrado = null;
            for (Restaurante r : colecao.getRestaurantes()) {
                if (r != null && r.getId() == id) {
                    encontrado = r;
                    break;
                }
            }

            if (encontrado != null) {
                lista.inserirFim(encontrado);
            }
        }

   
        long inicio = System.nanoTime();
        quickSort(lista);
        long fim = System.nanoTime();
        double tempo = (fim - inicio) / 1e9;

     
        lista.print();

        try {
            FileWriter fw = new FileWriter("quick_sort_log.txt", true);
            fw.write("Matrícula: XXXXXX\t| Tempo de execução: " + tempo + "s\t| Comparações: [Sua Métrica]\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        sc.close();
    }
}