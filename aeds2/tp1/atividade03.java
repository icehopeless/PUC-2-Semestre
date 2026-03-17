import java.util.*;

public class atividade03 {
    static char[] vogais = {a, e, i, o, u};

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
    
    public boolean fullVogal(String str){
        for(int i = 0; i < str.length(); i++){
            if(!compareCases(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
