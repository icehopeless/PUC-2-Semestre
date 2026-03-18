import java.util.*;

public class atividade03 {
    static char[] vogais = {'a', 'e', 'i', 'o', 'u'};

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

    //recebe um caracter e retorna true caso o caracter seja uma vogal
    public static boolean compareCasesVogal(char chr){
        boolean aux = false;
        for(char v : vogais){
                if(Character.toLowerCase(chr) == v){
                    aux = true;
                    break;
                }
        }

        return aux;
    }
    
    //metodo para verificar se a string e composta apenas por vogais
    public static boolean fullVogal(String str){
        for(int i = 0; i < str.length(); i++){
            if(!compareCasesVogal(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    //metodo para verificar se a string e composta somente por consoantes
    public static boolean fullConsoante(String str){
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);

            if(!Character.isLetter(c) || compareCasesVogal(c)){
                return false;
            }
        }
    return true;
}

    //metodo para verificar se a string e um inteiro
    public static boolean isInteiro(String str){
        for(int i = 0; i < str.length(); i++){
            if(!(str.charAt(i) >= '0' && str.charAt(i) <= '9')){
                return false;
            }
            if(str.charAt(i) == '.' || str.charAt(i) == ','){
                return false;
            }
        }
        return true;
    }

//metodo para verificar se a string e um numero real
    public static boolean isReal(String str){
        boolean aux = false;
        int points = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '.' || str.charAt(i) == ','){
                points++;
                aux = true;
                if(points>1){
                    return false;
                }
            }
            else if(!(str.charAt(i) >= '0' && str.charAt(i) <= '9')){
                return false;
            }
        }
        return aux;
    }

    public static void main(String[] args) {
        int count = 0;
		String[] lines = new String [1000];
		Scanner scan = new Scanner(System.in);
        String out = new String();

		String linha = scan.nextLine();
		while(!compareStrings(linha, "FIM")){
			lines[count] = linha;
			count++;
			linha = scan.nextLine();
		}

        
		for(int i = 0; i < count; i++){
    		if(fullVogal(lines[i])){
                out += "SIM";
            }else{
                out += "NAO";
            }

            if(fullConsoante(lines[i])){
                out += " SIM";
            }else{
                out += " NAO";
            }

            if(isInteiro(lines[i])){
                out += " SIM";
            }else{
                out += " NAO";
            }

            if(isReal(lines[i])){
                out += " SIM";
            }else{
                out += " NAO";
            }

            System.out.println(out);
            out = "";
		}
	} 

}
