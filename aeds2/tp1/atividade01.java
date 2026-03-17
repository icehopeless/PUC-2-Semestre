import java.util.*;


public class atividade01{
	public static int key = 3;

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

		while(!(lines[count] = scan.nextLine()).equals("FIM")){		
			count++;
		}
		
		for(int i = 0; i < count; i++){
    		System.out.println(cifraCesar(lines[i]));
		}
	}
	
}
