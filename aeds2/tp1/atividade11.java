import java.util.*;


public class atividade11{
	//funcao que compara uma string com uma outra, e retorna true caso sejam
	public static boolean compareStr(String str1, String str2){
		if(str1.length() != str2.length()){
			return false;
		}
		for(int i = 0; i < str1.length(); i++){
			if(str1.charAt(i) != str2.charAt(i)){
				return false;
			}
		}
		return true;
	}


	//funcao recursiva que recebe uma string, e retorna a inversa desta
	public static String reverseString(String str, char[] strout, int i){
		
		if(i == str.length()-1){
			strout[i] = str.charAt(str.length()-1-i);
			return new String(strout);
		}

		strout[i] = str.charAt(str.length()-1-i);
		return reverseString(str, strout, i+1);
	}

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in, "ISO-8859-1");
		String line;

		line = scan.nextLine();
		while(!compareStr(line, "FIM")){
			System.out.println(reverseString(line, new char[line.length()], 0));
			line = scan.nextLine();
		}
	}
}


