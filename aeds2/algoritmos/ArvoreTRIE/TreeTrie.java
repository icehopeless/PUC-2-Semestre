/*
Gabriel Agostinho da Silva
Isaque de Sousa Almeira

Implementacao da arvore TRIE com metodos de inserir, mostrar e pesquisar
*/

public class TreeTrie {
    private NodeTrie raiz;
    
    public TreeTrie(){
        raiz = new NodeTrie();
    }

    public void inserir(String s){
        inserir(raiz,s, 0);
    }

    public boolean pesquisar(String s){
       return pesquisar(raiz, s, 0);
    }

    public void mostrar(){
        mostrar(raiz, "");
    }


    public boolean hasEndWiths(String sufix){
        return hasEndWiths(raiz, sufix, "");
    }

    public int countHasEndsWith(String sufix){
        return countHasEndsWith(raiz, sufix, "");
    }
    /*public void inserir(NodeTrie node, String s, int i){
        if(node.alfabeto[(int)s.charAt(i)] == null){
            node.alfabeto[(int)s.charAt(i)] = new NodeTrie(s.charAt(i));

            if(i == s.length() - 1){
                node.alfabeto[(int)s.charAt(i)].setFolha(true);
                node.alfabeto[(int)s.charAt(i)].setIsFinalString(true);
            }else{
                inserir(node.alfabeto[(int)s.charAt(i)], s, i+1);
            }
        }else{
            if(i == s.length()-1){
                node.alfabeto[(int)s.charAt(i)].setIsFinalString(true);
            }else{
                inserir(node.alfabeto[(int)s.charAt(i)], s, i+1);
            }
        }
    } 
    */
    private void inserir(NodeTrie node, String s, int i) {
        char c = s.charAt(i);

        if (node.alfabeto[c] == null) {
            node.alfabeto[c] = new NodeTrie(c);
        }

        if (i == s.length() - 1) {
            node.alfabeto[c].setIsFinalString(true);
        } else {
            inserir(node.alfabeto[c], s, i + 1);
        }
    }

    private boolean pesquisar(NodeTrie node, String s, int i){
        char c = s.charAt(i);    
        boolean rtn = false;

        if(node.alfabeto[c] == null){
            rtn = false;
        }else if(i == s.length()-1){
            rtn = node.alfabeto[c].isFinalString();
        }else{
            rtn = pesquisar(node.alfabeto[c], s, i+1);
        }

        return rtn;
    }

    private void mostrar(NodeTrie node, String palavra){
        if(node.isFinalString()){
            System.out.println(palavra);
        }
            
        for(int i = 0; i < node.alfabeto.length; i++){
            if(node.alfabeto[i] != null){
                mostrar(node.alfabeto[i], palavra + node.alfabeto[i].getValue());
            }
        }
    }

    private boolean hasEndWiths(NodeTrie node,String sufix, String cacheSufix){
        boolean rtn = false;

        if(node.isFinalString() && cacheSufix.endsWith(sufix)){
            rtn = true;
        }else{
            for(int i =0; i < node.alfabeto.length && !rtn; i++){
                if(node.alfabeto[i] != null){
                    rtn = hasEndWiths(node.alfabeto[i], sufix, cacheSufix + node.alfabeto[i].getValue());
                }
            }
        }
        return rtn;
    }

    private int countHasEndsWith(NodeTrie node,String sufix,String cahcString){
        int result = 0;
        if(node.isFinalString() && cahcString.endsWith(sufix)){
            result = 1;
        }

        for(int i = 0; i < node.alfabeto.length; i++){
            if(node.alfabeto[i] != null){
                result += countHasEndsWith(node.alfabeto[i], sufix, cahcString + node.alfabeto[i].getValue());
            }
        }

        return result;
    }
}
