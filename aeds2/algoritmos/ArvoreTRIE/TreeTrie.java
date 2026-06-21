/*
Gabriel Agostinho da Silva
Isaque de Sousa Almeira

Implementacao da arvore TRIE com metodos de inserir, mostrar e pesquisar
*/

public class TreeTrie {
    private NodeTrie raiz;
    private final int sizeAlfa = 256;
    
    public TreeTrie(){
        raiz = new NodeTrie();
    }

    public void inserir(String s){
        inserir(raiz,s, 0);
    }

    public void pesquisar(String s){
        pesquisar(raiz, s, 0);
    }

    public void mostrar(){
        mostrar(raiz, "");
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
    public void inserir(NodeTrie node, String s, int i) {
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

    public boolean pesquisar(NodeTrie node, String s, int i){
        char c = s.charAt(i);    
        boolean rtn = false;

        if(node.alfabeto[c] == null){
            rtn = false;
        }

        else if(i == s.length()-1){
            rtn = node.alfabeto[c].isFinalString();
        }

        else{
            rtn = pesquisar(node.alfabeto[c], s, i+1);
        }

        return rtn;
    }

    public void mostrar(NodeTrie node, String palavra){
        if(node.isFinalString()){
            System.out.println(palavra);
        }

        for(int i = 0; i < node.alfabeto.length; i++){
            if(node.alfabeto[i] != null){
                mostrar(node.alfabeto[i], palavra + node.alfabeto[i].getValue());
            }
        }
    }
}
