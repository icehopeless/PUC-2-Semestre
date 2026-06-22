import java.io.*;
import java.util.*;

class stringUtils{
/*
	protected static String[] splitS(String s, char regex){
		String atual = "";
		int count = 0;
		String[] slices = new String[s.length()];

		for(int i = 0; i < s.length(); i++){
			char caracter = s.charAt(i);
			if(caracter == regex){
				slices[count] = atual;
				count++;
				atual = "";
			}else{
				atual += caracter;
			}
		}
		return slices;
	}
*/
    protected static boolean equalsS(String a, String b){

    if(a == null || b == null) return false;
    if(a.length() != b.length()) return false;

    for(int i = 0; i < a.length(); i++){
        if(a.charAt(i) != b.charAt(i)){
            return false;
        }
    }
    return true;
}
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

	protected static int countChar(String s, char regex){
		int count = 0;
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == regex){
				count++;
			}
		}
		return count;
	}

	protected String arrayToString(String[] array) {
	    String s = "[";

	    for (int i = 0; i < array.length; i++) {
	        s += array[i];

	        if (i < array.length - 1) {
        	    s += ",";
	        }
	    }

	    s += "]";
	    return s;
	}
}

class Hora extends stringUtils{
	private int hora;
	private int minuto;

	public Hora(){
		this.hora = 0;
		this.minuto = 0;
	}

	public Hora(int hora, int minuto){
		this.hora = hora;
		this.minuto = minuto;
	}

	public void setHora(int hora){
		if(hora >= 0 && hora <= 23){
			this.hora = hora;
		}
	}

	public void setMinuto(int minuto){
		if(minuto >= 0 && minuto <= 59){
			this.minuto = minuto;
		}
	}

	public int getHora(){
		return this.hora;
	}
	
	public int getMinuto(){
		return this.minuto;
	}

	public static Hora parseHora(String s){
		String[] rtn = splitS(s, ':');
		int hora = Integer.parseInt(rtn[0]);
		int minuto = Integer.parseInt(rtn[1]);

		return new Hora(hora, minuto);
	}

	public String formatar(){
		return String.format("%02d:%02d", this.hora, this.minuto);
	}


}

class Data extends stringUtils{
	private int dia;
	private int mes;
	private int ano;

	public Data(){
		dia = 0;
		mes = 0;
		ano = 0;
	}

	public Data(int dia, int mes, int ano){
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}

	public void setDia(int dia){
		this.dia = dia;
	}

	public void setMes(int mes){
                this.mes = mes;
        }

	public void setAno(int ano){
                this.ano = ano;
        }

	public int getDia(){
		return this.dia;
	}

	public int getMes(){
		return this.mes;
	}

	public int getAno(){
		return this.ano;
	}

	public static Data parseData(String s){
                String[] rtn = splitS(s, '-');
                int dia = Integer.parseInt(rtn[2]);
                int mes = Integer.parseInt(rtn[1]);
                int ano = Integer.parseInt(rtn[0]);

                return new Data(dia, mes, ano);

	}

	public String formatar(){
		return String.format("%02d/%02d/%d", this.dia, this.mes, this.ano);
	}



}

class Restaurante extends stringUtils{
	private int id;
	private String nome;
	private String cidade;
	private int capacidade;
	private double avaliacao; //nota media
	private String[] tipos_cozinha; //tipos de culinaria
	private int faixa_preco; //expresso por $
	private Hora horario_abertura;
	private Hora horario_fechamento;
	private Data data_abertura;
	private boolean aberto;


	public Restaurante(int id, String nome, String cidade, int capacidade, double avaliacao, String[] tipos_cozinha, int faixa_preco, Hora horario_abertura, Hora horario_fechamento, Data data_abertura, boolean aberto){
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.capacidade = capacidade;
		this.avaliacao = avaliacao;
		this.tipos_cozinha = new String[tipos_cozinha.length];
		for(int i = 0; i < tipos_cozinha.length; i++){
			this.tipos_cozinha[i] = tipos_cozinha[i];
		}
		this.faixa_preco = faixa_preco;
		this.horario_abertura = horario_abertura;
		this.horario_fechamento = horario_fechamento;
		this.data_abertura = data_abertura;
		this.aberto = aberto;

        }

/*	public static Restaurante parseRestaurante(String s){
		String[] slices = splitS(line, ',');
                return new Restaurante(integer.ParseInt(slices[0]), slices[1], slices[2], integer.ParseInt(slices[3]), Double.parseDouble(slices[4]), 
			splitS(slices[5], ';'), countChar(slices[6]), '$'), Hora.parseHora(slices[7], Hora.parseHora(8), Data.parseData(slices[9]), Boolean.parseBoolean(slices[10]));
		
	}
*/
	public static Restaurante parseRestaurante(String s){
	    String[] slices = splitS(s, ',');
	    String[] horas = splitS(slices[7], '-');

	    return new Restaurante(
	        Integer.parseInt(slices[0]),
	        slices[1],
	        slices[2],
	        Integer.parseInt(slices[3]),
	        Double.parseDouble(slices[4]),
	        splitS(slices[5], ';'),
	        countChar(slices[6], '$'),
	        Hora.parseHora(horas[0]),
		Hora.parseHora(horas[1]),
	        Data.parseData(slices[8]),
        	Boolean.parseBoolean(slices[9]));
	}

	public String[] gettipos_cozinha(){
		return this.tipos_cozinha;
	}

	public int getId(){
		return this.id;
	}

	public String getNome(){
		return this.nome;
	}

	public String getCidade(){
		return this.cidade;
	}

	public int getCapacidade(){
		return this.capacidade;
	}

	public Double getAvalicao(){
		return this.avaliacao;
	}

	public String getFaixaPreco(){
		String s = "";
		for(int i = 0; i < faixa_preco; i++){
			s+="$";
		}

		return s;
	}

	public Hora getHorarioAbertura(){
		return this.horario_abertura;
	}
	
	public Hora getHorarioFechamento(){
		return this.horario_fechamento;
	}

	public Data getDataAbertura(){
		return this.data_abertura;
	}

	public boolean getAberto(){
		return this.aberto;
	}

	public void setId(int id){
		this.id = id;
	}
	
	public void setNome(String s){
		this.nome = s;
	}

	public void setCidade(String s){
		this.cidade = s;
	}

	public void setCapacidade(int capacidade){
		this.capacidade = capacidade;
	}

	public void setAvaliacao(double nota){
		this.avaliacao = nota;
	}

	public void setTiposCozinha(String[] s){
		for(int i = 0; i < tipos_cozinha.length; i++){
                        this.tipos_cozinha[i] = s[i];
                }

	}
	
	public void setFaixaPreco(String s){
		faixa_preco = countChar(s, '$');
	}

	public void setHorarioAbertura(Hora hora){
		this.horario_abertura = hora;
	}

	public void setHorarioFechamento(Hora hora){
		this.horario_fechamento = hora;
	}

	public void setDataAbertura(Data data){
		this.data_abertura = data;
	}

	public void setAberto(boolean aberto){
		this.aberto = aberto;
	}

	

	public String formatar(){
		String s = "";
		for (int i = 0; i < this.faixa_preco; i++) {
    			s += "$";
		}
		

		return "[" + this.id + " ## " 
	   		+ this.nome + " ## " 
			+ this.cidade + " ## "
		    	+ this.capacidade + " ## " 
			+ this.avaliacao + " ## "
			+ arrayToString(this.tipos_cozinha) + " ## "
		    	+ s + " ## "
		    	+ this.horario_abertura.formatar() + "-"
		    	+ this.horario_fechamento.formatar() + " ## "
		    	+ this.data_abertura.formatar() + " ## "
		    	+ this.aberto + "]";
	}
}


class ColecaoRestaurantes extends stringUtils{
	private int tamanho;
	private Restaurante[] restaurantes;

	public ColecaoRestaurantes(){
		this.tamanho = 0;
		this.restaurantes = new Restaurante[10];
	}

	public int getTamanho(){
		return this.tamanho;
	}
	public Restaurante[] getRestaurantes(){
		return this.restaurantes;
	}

	private void aumentarCapacidade(){
		Restaurante[] novo = new Restaurante[this.restaurantes.length * 2];

		for(int i = 0; i < this.restaurantes.length; i++){
			novo[i] = this.restaurantes[i];
		}

		this.restaurantes = novo;
	}

	public void readCsv(String path){
		try{
			Scanner scan = new Scanner(new File(path));
			String line;
			line = scan.nextLine();
			while(scan.hasNextLine()){
				line = scan.nextLine();
				if(line.length() == 0){
					break;
				}
				
				if(tamanho == restaurantes.length){
					aumentarCapacidade();
				}

				this.restaurantes[tamanho] = Restaurante.parseRestaurante(line);
				tamanho++;

			}

			scan.close();

		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao ler o arquivo");
		}
	}

	public static ColecaoRestaurantes readCsv(){
		ColecaoRestaurantes colecao = new ColecaoRestaurantes();
		colecao.readCsv("/tmp/restaurantes.csv");
		return colecao;

	}
}

public class tabelaHash extends stringUtils{
    private Restaurante[] table;
    private int tamTab = 83;

    public tabelaHash(){
        table = new Restaurante[tamTab];
    }

    public Restaurante getAt(int pos) {
        return table[pos];
    }

    private int ascNome(String nome){
        int count = 0;
        for(int i = 0; i < nome.length(); i++){
            count += nome.charAt(i);
        }
        return count;
    }

    private int hash(String nome){
        return ascNome(nome) % tamTab;
    }

    private int reHash(String nome){
        return (ascNome(nome) + 1) % tamTab;
    }

    public void inserir(Restaurante r){
        int pos = hash(r.getNome());
        if(this.table[pos] == null){
            this.table[pos] = r;
        }else{
            int rehash = reHash(r.getNome());
            if(this.table[rehash] == null){
                this.table[rehash] = r;
            }else{
                System.out.println(r.getNome());
                return;
            }
        }
    }

    public int pesquisar(String nome){
        int pos = hash(nome);
        if(this.table[pos] != null && equalsS(this.table[pos].getNome(), nome)){
            return pos;
        }
        
        pos = reHash(nome);
        if(this.table[pos] != null && equalsS(this.table[pos].getNome(), nome)){
            return pos;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColecaoRestaurantes colecao = ColecaoRestaurantes.readCsv();
        tabelaHash hash = new tabelaHash();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (stringUtils.equalsS(line, "-1")) break;
            if (line.isEmpty()) continue;

            int id = Integer.parseInt(line);
            for (int i = 0; i < colecao.getTamanho(); i++) {
                Restaurante r = colecao.getRestaurantes()[i];
                if (r != null && r.getId() == id) {
                    hash.inserir(r);
                    break;
                }
            }
        }

        
        while (sc.hasNextLine()) {
            String nome = sc.nextLine().trim();
            if (stringUtils.equalsS(nome, "FIM")) break;
            if (nome.isEmpty()) continue;

            int pos = hash.pesquisar(nome);
            if (pos != -1) {
                System.out.println(pos + " " + hash.getAt(pos).formatar());
            } else {
                System.out.println("-1");
            }
        }

        sc.close();
    }

}