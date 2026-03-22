import java.util.*;

public class atividade12{

	//funcao recursiva que soma todos os termos de um inteiro
	public static int somaRecursiva(int num){
	int soma = 0;
       	soma+= num%10;

	if(num < 10){
		return num;
	}
	
	return soma+somaRecursiva(num/10);

}

	public static void main(String[]args){
		Scanner scan = new Scanner(System.in);
		int num = 0;
		

		while(scan.hasNextInt()){
			num = scan.nextInt();
			System.out.println(somaRecursiva(num));
			
		}	
	}


}
