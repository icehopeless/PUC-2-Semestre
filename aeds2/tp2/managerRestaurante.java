import java.io.*;
import java.util.*;

class stringUtils{
	protected static String[] splitS(String s, char regex){
		String atual = "";
		int count = 0;
		String[] slices = new String[s.length()];

		for(int i = 0; i < s.length(); i++){
			char caracter = s.chatAt(i);
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
		String rtn = splitS(s, ':');
		int hora = Integer.parseInt(rtn[0]);
		int minuto = Integer.parseInt(rtn[1]);

		return new Hora(hora, minuto);
	}

	public String formatar(){
		return this.hora + ":" +this.minuto;
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
                String[] rtn = splitS(s, '/');
                int dia = Integer.parseInt(rtn[2]);
                int mes = Integer.parseInt(rtn[1]);
                int ano = Integer.parseInt(rtn[0]);

                return new Data(dia, mes, ano);

	}

	public String formatar(){
		return this.dia + "/" + this.mes + "/" + this.ano;
	}



}

class Restaurante{
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
		this.tipos_cozinha = new String[tipos_cozinha.length()];
		for(int i = 0; i < tipos_cozinha.length(); i++){
			this.tipos_cozinha[i] = tipos_cozinha[i];
		}
		this.faixa_preco = faixa_preco;
		this.horario_abertura = horario_abertura;
		this.aberto = aberto;

        }

	public static Restaurante parseRestaurante(String s){
		
	}

	public String formatar(){
		return "[ => id" + this.id + 
			" nome " + this.nome + 
			" cidade " + this.cidade + 
			" capacidade " + this.capacidade + 
			" avaliacao " + this.avaliacao + 
			gettipos_cozinha() + 
			" faixa_preco " + this.faixa_preco + 
			" horario_abertura  " + this.horario_abertura +
			" horario_fechamento " + this.horario_fechamento +
			" data_abertura " + this.data_abertura + 
			" aberto " + this.aberto + "\n";
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

	public void addRestaurante(int id, String nome, String cidade, int capacidade, double avaliacao, String[] tipos_cozinha, int faixa_preco, Hora horario_abertura, Hora horario_fechamento, Data data_abertura, boolean aberto){
		restaurantes[tamanho] = new Restaurante(id, nome, cidade, capacidade, avaliacao, tipos_cozinha, faixa_preco, horario_abertura, horario_fechamento, data_abertura, aberto);	
	}

	public void readCsv(String path){
		try{
			Scanner scan = new Scanner(new File(path));
			String line;
			while(scan.hasNextLine()){
				line = scan.nextLine();
				if(line.length() < 0){
					break;
				}
				
				String[] slices = splitS(line, ',');
				Restaurante r = new Restaurante(integer.ParseInt(slices[0]), slices[1], slices[2], integer.ParseInt(slice[3]), Double.parseDouble(slices[4]), 
						splitS(slices[5], ';'), );

			}

			scan.close();

		}catch(Exception e){
			System.out.println("Erro ao ler o arquivo");
		}
	}

	public static ColecaoRestaurantes readCsv(){
		
	}
}

public class managerRestaurante{
	public static void main(String[] args){}
} 
