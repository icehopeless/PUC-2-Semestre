import java.util.*;

public class atividade02{

    public static ArrayList<Character> sequenciaEspelho(int num1, int num2){
        ArrayList<Character> saida = new ArrayList<>();
        for(int i = num1; i <= num2; i++){
            String s = String.valueOf(i);

            for (int j = 0; j < s.length(); j++){ //percorre a string adicionando cada caracter presente nela
                saida.add(s.charAt(j));
            }
            
        }

        for(int i = saida.size()-1; i >= 0; i--){
            saida.add(saida.get(i));
        }

        return saida;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        ArrayList<Character> saida = new ArrayList<>();
        String entrada;
        int num1, num2;

        entrada = scan.nextLine();
        num1 = Integer.parseInt(entrada.split(" ")[0]);
        num2 = Integer.parseInt(entrada.split(" ")[1]);
        saida = sequenciaEspelho(num1,num2);

        for (char c : saida) {
            System.out.print(c);
        }
        
    }
}