public class Main{
    public static void main(String[] args) {
        TreeTrie tree = new TreeTrie();
        tree.inserir("Gabriel");
        tree.inserir("Gabrieliel");
        tree.inserir("Isaque");
        tree.inserir("Batman");
        tree.inserir("Robin");
        tree.inserir("Macgyver");
        tree.inserir("Bozer");
        tree.inserir("Reverb");
        tree.inserir("Ice");
        tree.inserir("Ws");
        tree.inserir("Sonar");
        tree.inserir("Sonare");

        tree.mostrar();


        System.out.println("Has bozer: " + tree.pesquisar("Bozer"));
        System.out.println("Has Ends With iel: " + tree.hasEndWiths("iel"));
        System.out.println("Ends With iel: " + tree.countHasEndsWith("iel"));
        
    }
}