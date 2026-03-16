import java.util.*;

public class atividade02 {

    public static String randomAlter(String str){
        //gerando os dois caracteres aleatorios
        Random gerador = new Random();
        gerador.setSeed(4);
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
		int count = 0;
		String[] lines = new String [100];
		Scanner scan = new Scanner(System.in);

		while((scan.hasNextLine())){		
			lines[count] = scan.nextLine();
			count++;
		}

		String[] out = new String[count];


		for(int i = 0; i < count; i++){
			out[i] = randomAlter(lines[i]); 
		}
		
		for (String s : out) {
			System.out.println(s);
		}
	}
}
