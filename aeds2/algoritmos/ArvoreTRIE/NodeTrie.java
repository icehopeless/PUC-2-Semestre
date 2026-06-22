/*
Gabriel Agostinho da Silva
Isaque de Sousa Almeira

No da arvore TRIE com tabela hash
*/

public class NodeTrie{
    NodeTrie[] alfabeto;
    private final int sizeAlfa = 256;
    private char value;
    private boolean finalString;


    public NodeTrie(char value) {
        this.value = value;
        this.alfabeto = new NodeTrie[sizeAlfa];
        this.finalString = false;
        initAlfabeto();
    }

    public NodeTrie(){
        this.value = ' ';
        this.alfabeto = new NodeTrie[sizeAlfa];
        this.finalString = false;
        initAlfabeto();
    }

    private void initAlfabeto(){
        for(int i = 0; i < sizeAlfa; i++){
            alfabeto[i] = null;
        }
    }


    public boolean isFinalString(){
        return this.finalString;
    }
    
    public void setIsFinalString(boolean isFinal){
        this.finalString = isFinal;
    }

    public char getValue(){
        return this.value;
    }

    public void setValue(char value){
        this.value = value;
    }

}