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
