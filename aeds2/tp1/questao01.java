import java.util.*;


public class atividade01{
	public int key = 3;

	//funcao principal para fazer o ciframento
	public static String cifraCesar(String str){
		for(int i = 0; i < str.length(); i++){
			str[i] = str[i]+3;
		}
		
		return str;
	}

	public static void main (String[] args){
		String in;
		int count = 0;
		String[][] lines = new String [100][100];
		Scanner scan = new Scanner(System.in);

		while((scan.hasNextLine())){		
			lines[count] = scan.nextLine();
			count++;
		}

		String[][] out = new String [100][count];


		for(int i = 0; i <= count; i++){
			out[i] = cifraCesar(lines[i]); 
		}

		for (String[] linha : out) {
   			for (String str : linha) {
      				MyIO.println(linha);
    			}
		}
		
		
	}
	
}
