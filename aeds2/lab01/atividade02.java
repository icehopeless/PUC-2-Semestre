import java.util.Scanner;
import java.util.Vector;

public class atividade02{
	public static int countMaiusculas(String str, int i){
		int count = 0;
		if(str.charAt(i) >= 65 && str.charAt(i) <=90){
			count = 1;
		}
		if(i <= 1){
			count = 0;
			return 0;	
		}
		else{
			return count + countMaiusculas(str, i-1);
		}


	}

	public static void main (String[] args){
		Vector<String> lines = new Vector<String>();
		Scanner scan = new Scanner (System.in);

		do{
			lines.add(scan.nextLine());
		}while(!lines.lastElement().equals("FIM"));

		for(int i =0; i < lines.size(); i++){			
			System.out.println(countMaiusculas(lines.elementAt(i), lines.elementAt(i).length()-1 ));
		}
	}

	
}
