import java.util.*;

public class atividade02 {
    public static Random gerador = new Random();

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
    
    public static String randomAlter(String str){
        //gerando os dois caracteres aleatorios
        char[] chars = new char[str.length()]; //criacao de uma string literal para manipulacao de caracteres

        char a = ((char)('a' + (Math.abs(gerador.nextInt()) % 26))),
        b = ((char)('a' + (Math.abs(gerador.nextInt()) % 26)));

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == a){
                chars[i] = b;
            }else{
                chars[i] = str.charAt(i);
            }
        }

        return new String(chars);
    }

    public static void main (String[] args){
        gerador.setSeed(4);
		int count = 0;
		String[] lines = new String [1000];
		Scanner scan = new Scanner(System.in);

		String linha = scan.nextLine();
		while(!compareStrings(linha, "FIM")){
			lines[count] = linha;
			count++;
			linha = scan.nextLine();
		}

		for(int i = 0; i < count; i++){
    		System.out.println(randomAlter(lines[i]));
		}
	}
}
