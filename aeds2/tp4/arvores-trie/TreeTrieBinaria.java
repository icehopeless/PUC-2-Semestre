import java.io.*;
import java.util.*;

class stringUtils {
    protected static boolean equalsS(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        if (a.length() != b.length()) {
            return false;
        }
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    protected static String[] splitS(String s, char regex) {
        int parts = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == regex) {
                parts++;
            }
        }
        String[] slices = new String[parts];
        String atual = "";
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char caracter = s.charAt(i);
            if (caracter == regex) {
                slices[count++] = atual;
                atual = "";
            } else {
                atual += caracter;
            }
        }
        slices[count] = atual;
        return slices;
    }

    protected static int countChar(String s, char regex) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == regex) {
                count++;
            }
        }
        return count;
    }

    protected String arrayToString(String[] array) {
        String s = "[";
        for (int i = 0; i < array.length; i++) {
            s += array[i];
            if (i < array.length - 1) {
                s += ",";
            }
        }
        s += "]";
        return s;
    }
}

class Hora extends stringUtils {
    private int hora;
    private int minuto;

    public Hora() {
        this.hora = 0;
        this.minuto = 0;
    }

    public Hora(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public static Hora parseHora(String s) {
        String[] rtn = splitS(s, ':');
        int hora = Integer.parseInt(rtn[0]);
        int minuto = Integer.parseInt(rtn[1]);
        return new Hora(hora, minuto);
    }

    public String formatar() {
        return String.format("%02d:%02d", this.hora, this.minuto);
    }
}

class Data extends stringUtils {
    private int dia;
    private int mes;
    private int ano;

    public Data() {
        dia = 0;
        mes = 0;
        ano = 0;
    }

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public static Data parseData(String s) {
        String[] rtn = splitS(s, '-');
        int dia = Integer.parseInt(rtn[2]);
        int mes = Integer.parseInt(rtn[1]);
        int ano = Integer.parseInt(rtn[0]);
        return new Data(dia, mes, ano);
    }

    public String formatar() {
        return String.format("%02d/%02d/%d", this.dia, this.mes, this.ano);
    }
}

class Restaurante extends stringUtils {
    private int id;
    private String nome;
    private String cidade;
    private int capacidade;
    private double avaliacao;
    private String[] tipos_cozinha;
    private int faixa_preco;
    private Hora horario_abertura;
    private Hora horario_fechamento;
    private Data data_abertura;
    private boolean aberto;

    public Restaurante(int id, String nome, String cidade, int capacidade, double avaliacao, String[] tipos_cozinha, int faixa_preco, Hora horario_abertura, Hora horario_fechamento, Data data_abertura, boolean aberto) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.capacidade = capacidade;
        this.avaliacao = avaliacao;
        this.tipos_cozinha = new String[tipos_cozinha.length];
        for (int i = 0; i < tipos_cozinha.length; i++) {
            this.tipos_cozinha[i] = tipos_cozinha[i];
        }
        this.faixa_preco = faixa_preco;
        this.horario_abertura = horario_abertura;
        this.horario_fechamento = horario_fechamento;
        this.data_abertura = data_abertura;
        this.aberto = aberto;
    }

    public static Restaurante parseRestaurante(String s) {
        String[] slices = splitS(s, ',');
        String[] horas = splitS(slices[7], '-');
        return new Restaurante(
            Integer.parseInt(slices[0]),
            slices[1],
            slices[2],
            Integer.parseInt(slices[3]),
            Double.parseDouble(slices[4]),
            splitS(slices[5], ';'),
            countChar(slices[6], '$'),
            Hora.parseHora(horas[0]),
            Hora.parseHora(horas[1]),
            Data.parseData(slices[8]),
            Boolean.parseBoolean(slices[9]));
    }

    public int getId() { return this.id; }
    public String getNome() { return this.nome; }

    public String formatar() {
        String s = "";
        for (int i = 0; i < this.faixa_preco; i++) {
            s += "$";
        }
        return "[" + this.id + " ## " 
            + this.nome + " ## " 
            + this.cidade + " ## "
            + this.capacidade + " ## " 
            + this.avaliacao + " ## "
            + arrayToString(this.tipos_cozinha) + " ## "
            + s + " ## "
            + this.horario_abertura.formatar() + "-"
            + this.horario_fechamento.formatar() + " ## "
            + this.data_abertura.formatar() + " ## "
            + this.aberto + "]";
    }
}

class ColecaoRestaurantes extends stringUtils {
    private int tamanho;
    private Restaurante[] restaurantes;

    public ColecaoRestaurantes() {
        this.tamanho = 0;
        this.restaurantes = new Restaurante[10];
    }

    public Restaurante[] getRestaurantes() { return this.restaurantes; }

    private void aumentarCapacidade() {
        Restaurante[] novo = new Restaurante[this.restaurantes.length * 2];
        for (int i = 0; i < this.restaurantes.length; i++) {
            novo[i] = this.restaurantes[i];
        }
        this.restaurantes = novo;
    }

    public void readCsv(String path) {
        try {
            Scanner scan = new Scanner(new File(path));
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.length() == 0) {
                    break;
                }
                if (tamanho == restaurantes.length) {
                    aumentarCapacidade();
                }
                this.restaurantes[tamanho++] = Restaurante.parseRestaurante(line);
            }
            scan.close();
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

    public static ColecaoRestaurantes readCsv() {
        ColecaoRestaurantes colecao = new ColecaoRestaurantes();
        colecao.readCsv("/tmp/restaurantes.csv");
        return colecao;
    }
}

class NodeBinario {
    public char caractere;
    public NodeTrie ponteiroTrie;
    public NodeBinario esq, dir;

    public NodeBinario(char caractere) {
        this.caractere = caractere;
        this.ponteiroTrie = new NodeTrie(caractere);
        this.esq = this.dir = null;
    }
}

class NodeTrie {
    private char value;
    private boolean finalString;
    private Restaurante restaurante;
    public NodeBinario raizBinaria;

    public NodeTrie(char value) {
        this.value = value;
        this.finalString = false;
        this.raizBinaria = null;
    }

    public NodeTrie() {
        this.value = ' ';
        this.finalString = false;
        this.raizBinaria = null;
    }

    public boolean isFinalString() { 
        return this.finalString; 
    }

    public void setIsFinalString(boolean isFinal) { 
        this.finalString = isFinal; 
    }

    public char getValue() { 
        return this.value; 
    }

    public void setRestaurante(Restaurante r) { 
        this.restaurante = r; 
    }

    public Restaurante getRestaurante() { 
        return this.restaurante; 
    }
}


public class TreeTrieBinaria {
    private NodeTrie raiz;

    public TreeTrieBinaria() {
        raiz = new NodeTrie();
    }

    public void inserir(Restaurante r) {
        inserir(raiz, r.getNome(), 0, r);
    }

    private void inserir(NodeTrie node, String s, int i, Restaurante r) {
        char c = s.charAt(i);
        node.raizBinaria = inserirNoBinario(node.raizBinaria, c);
        NodeTrie proximoNode = buscarNoBinario(node.raizBinaria, c);

        if (i == s.length() - 1) {
            proximoNode.setIsFinalString(true);
            proximoNode.setRestaurante(r);
        } else {
            inserir(proximoNode, s, i + 1, r);
        }
    }

    private NodeBinario inserirNoBinario(NodeBinario node, char c) {
        if (node == null) {
            return new NodeBinario(c);
        }
        if (c < node.caractere) {
            node.esq = inserirNoBinario(node.esq, c);
        } else if (c > node.caractere) {
            node.dir = inserirNoBinario(node.dir, c);
        }
        return node;
    }

    private NodeTrie buscarNoBinario(NodeBinario node, char c) {
        if (node == null) {
            return null;
        }
        if (c == node.caractere) {
            return node.ponteiroTrie;
        } else if (c < node.caractere) {
            return buscarNoBinario(node.esq, c);
        } else {
            return buscarNoBinario(node.dir, c);
        }
    }

    public void pesquisar(String s) {
        pesquisar(raiz, s, 0);
    }

    private void pesquisar(NodeTrie node, String s, int i) {
        if (i >= s.length()) {
            return;
        }
        
        char c = s.charAt(i);
        NodeTrie proximo = buscarNoBinario(node.raizBinaria, c);

        if (proximo == null) {
            System.out.println("NAO");
        } else {
            System.out.print(c + " ");
            if (i == s.length() - 1) {
                if (proximo.isFinalString()) {
                    System.out.println("SIM " + proximo.getRestaurante().formatar());
                } else {
                    System.out.println("NAO");
                }
            } else {
                pesquisar(proximo, s, i + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ColecaoRestaurantes colecao = ColecaoRestaurantes.readCsv();
        TreeTrieBinaria trie = new TreeTrieBinaria();

        while (sc.hasNextLine()) {
            String linha = sc.nextLine().trim();
            if (stringUtils.equalsS(linha, "-1")) {
                break;
            }

            if (linha.length() > 0) {
                int id = Integer.parseInt(linha);
                for (Restaurante r : colecao.getRestaurantes()) {
                    if (r != null) {
                        if (r.getId() == id) {
                            trie.inserir(r);
                            break;
                        }
                    }
                }
            }
        }

        while (sc.hasNextLine()) {
            String nomePesquisa = sc.nextLine().trim();
            if (stringUtils.equalsS(nomePesquisa, "FIM")) {
                break;
            }

            if (nomePesquisa.length() > 0) {
                trie.pesquisar(nomePesquisa);
            }
        }

        sc.close();
    }
}
