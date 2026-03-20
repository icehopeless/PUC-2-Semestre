import java.util.*;


public class atividade01{
	public static int key = 3;

	//funcao para verificar se uma string e igual a outra
	public static boolean compareStrings(String str1, String str2){
		if(str1.length() != str2.length()){
			return false;
		}
		else{
			for(int i = 0; i < str1.length(); i++){
				if(str1.charAt(i) != str2.charAt(i)){
					return false;
				}
			}
		}
		return true;
	}

	//funcao principal para fazer o ciframento
	public static String cifraCesar(String str){
		//e necessario utilizar um array de caracter literal, pois a String em java e imutavel, e nao podemos alterar um caracter especifico que desejamos.
		char[] chars = new char[str.length()];
		for(int i = 0; i < str.length(); i++){
			chars[i] = (char)(str.charAt(i) + key);
		}
		
		return new String(chars);
	}

	public static void main (String[] args){
		int count = 0;
		String[] lines = new String [1000];
		Scanner scan = new Scanner(System.in);


		//para o laco quando a entrada for FIM
		String linha = scan.nextLine();
		while(!compareStrings(linha, "FIM")){
			lines[count] = linha;
			count++;
			linha = scan.nextLine();
		}
		//printa os resultados
		for(int i = 0; i < count; i++){
    		System.out.println(cifraCesar(lines[i]));
		}
	}
	
}
