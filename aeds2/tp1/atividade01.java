import java.util.*;


public class atividade01{
	public static int key = 3;

	//funcao principal para fazer o ciframento
	public static String cifraCesar(String str){
		char[] chars = new char[str.length()];
		for(int i = 0; i < str.length(); i++){
			chars[i] = (char)(str.charAt(i) + key);
		}
		
		return new String(chars);
	}

	public static void main (String[] args){
		String in;
		int count = 0;
		String[] lines = new String [100];
		Scanner scan = new Scanner(System.in);

		while((scan.hasNextLine())){		
			lines[count] = scan.nextLine();
			count++;
		}

		String[] out = new String[count];


		for(int i = 0; i < count; i++){
			out[i] = cifraCesar(lines[i]); 
		}
		
		for (String s : out) {
			System.out.println(s);
		}
	}
	
}
