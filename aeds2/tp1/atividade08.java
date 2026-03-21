import java.util.*;

//apenas charAt e lenght da String
public class atividade08{
//funcao que recebe um caracter e retorna true caso este seja uma letra maiuscula conforme a tabela ASCII
	public static boolean isMaiuscula(char chr){
		if(chr >= 'A' && chr <= 'Z'){
			return true;
		}else{
			return false;
		}
	}
//funcao que recebe um caracter e retorna true caso este seja uma letra minuscula conforme a tabela ASCII
	public static boolean isMinuscula(char chr){
                if(chr >= 'a' && chr <= 'z'){
                        return true;
                }else{
                        return false;
                }
	
	}
//funcao que recebe um caracter e retorna true casa este seja um numero de acordo com a tabela ASCII
	public static boolean isNum(char chr){
                if(chr >= '0' && chr <= '9'){
                        return true;
                }else{
                        return false;
                }

	}
//funcao que recebe um caracter e retorna true casa este seja um caracter especial de acordo com a tabela ASCII
	public static boolean isEspecial(char chr){
                if(!isMaiuscula(chr) && !isMinuscula(chr) && !isNum(chr) && chr != ' ' && chr != '\n' && chr != '\t'){
                        return true;
                }else{
                        return false;
                }

	}

	//metodo para validar a senha. Recebe uma str e retorna true caso seja valida ou false caso invalida
	public static boolean isValid(String str){
		boolean mai = false, min = false, num = false, esp = false;
		for(int i = 0; i < str.length(); i++){
			char istr = str.charAt(i);
			if(str.length() < 8){
				return false;
			}
			
			if(isMaiuscula(istr)){
				mai = true;
			}
			if(isMinuscula(istr)){
				min = true;
			}
			if(isNum(istr)){
				num = true;
			}
			if(isEspecial(istr)){
				esp = true;
			}

		}

		if(mai && min && num && esp){
			return true;
		}else{
			return false;
		}	
	}
//funcao que compara uma string com uma outra, e retorna true caso sejam
	public static boolean compareStr(String str1, String str2){
		if(str1.length() != str2.length()){
			return false;
		}
		for(int i = 0; i < str1.length(); i++){
			if(str1.charAt(i) != str2.charAt(i)){
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String line, in;

		//loop principal que e executado enquanto a entrada for diferente de FIM
		line = scan.nextLine();
		while(!compareStr(line,"FIM")){
			in = line;
			if(isValid(in)){
				System.out.println("SIM");
			}else{
				System.out.println("NAO");
			}
			line = scan.nextLine();
		}
	}


}
