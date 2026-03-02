import java.util.Scanner;
import java.util.Vector;

public class atividade01{
	public static int countMaiusculas(String str){
		int count = 0;
		for(int i = 0; i<str.length(); i++){
			if(str.charAt(i) >= 65 && str.charAt(i) <= 90){
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args){
		Vector<String> lines = new Vector<String>();
		Scanner scan = new Scanner(System.in);

		do{
			lines.add(scan.nextLine());
		}while(!lines.lastElement().equals("FIM"));

		for(int i = 0; i < lines.size(); i++){
			System.out.println(countMaiusculas(lines.elementAt(i)));
		}
	
	}
}
