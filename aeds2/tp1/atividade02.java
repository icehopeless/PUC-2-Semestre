import java.util.*;

public class atividade02 {
    public static Random gerador = new Random();
    
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

		while(!(lines[count] = scan.nextLine()).equals("FIM")){		
			count++;
		}

		for(int i = 0; i < count; i++){
    		System.out.println(randomAlter(lines[i]));
		}
	}
}
