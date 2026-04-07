class stringUtils{
	public static String[] splitS(String s, char regex){
		int[] intervalos;
		int count = 0;
		for(int i = 0; i < s.length(); i++ ){
			if(s.charAt(i) == regex || s.charAt(i) == '\n'){
				intervalos[count] = i;
				count++;
			}
		}

	}
}

class Hora{
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
		
	}

	public String formatar(){
		return this.hora + ":" +this.minuto;
	}


}

class Data{
	private int dia;
	private int mes;
	private int ano;
}

class Restaurante{
	private int id;
	private String nome;
	private String cidade;
	private int capacidade;
	private double avaliacao; //nota media
	private String[] tipos_cozinha; //tipos de culinaria
	private int faixa_preco;
	private Hora horario_abertura;
	private Hora horario_fechamento;
	private Data data_abertura;
	private boolean aberto;

}

public class managerRestaurante{
	public static void main(String[] args){}
} 
