public class testmyio {

    public static void main(String[] args) {

        MyIO.println("=== Teste da MyIO ===");

        MyIO.print("Digite seu nome: ");
        String nome = MyIO.readString();

        MyIO.print("Digite sua idade: ");
        int idade = MyIO.readInt();

        MyIO.println(" ");
        MyIO.println("=== Resultado ===");
        MyIO.println("Nome: " + nome);
        MyIO.println("Idade: " + idade);

        if (idade >= 18) {
            MyIO.println("Você é maior de idade.");
        } else {
            MyIO.println("Você é menor de idade.");
        }
    }
}
