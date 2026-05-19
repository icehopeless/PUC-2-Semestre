import java.util.*;
import java.util.Scanner;

class Celula {
    private int valor;
    Celula sup, inf, esq, dir;

    public Celula() {
        this.sup = null;
        this.inf = null;
        this.esq = null;
        this.dir = null;
    }

    public Celula(int valor) {
        this();
        this.valor = valor;
    }

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}

public class Matriz {
    int coluna;
    int linha;
    Celula inicio;

    public Matriz(int l, int c) {
        this.coluna = c;
        this.linha = l;
        inicio = new Celula();

        Celula atual = inicio;
        for (int j = 1; j < this.coluna; j++) {
            atual.dir = new Celula();
            atual.dir.esq = atual;
            atual = atual.dir;
        }

        Celula linhaCima = inicio;
        for (int i = 1; i < linha; i++) {
            linhaCima.inf = new Celula();
            linhaCima.inf.sup = linhaCima;
            Celula novaAtual = linhaCima.inf;
            Celula atualCima = linhaCima;

            for (int j = 1; j < coluna; j++) {
                novaAtual.dir = new Celula();
                novaAtual.dir.esq = novaAtual;
                novaAtual = novaAtual.dir;
                atualCima = atualCima.dir;
                atualCima.inf = novaAtual;
                novaAtual.sup = atualCima;
            }

            linhaCima = linhaCima.inf;
        }
    }

    public void preencher(Scanner sc) {
        Celula lin = this.inicio;
        while (lin != null) {
            Celula cel = lin;
            while (cel != null) {
                cel.setValor(sc.nextInt());
                cel = cel.dir;
            }
            lin = lin.inf;
        }
    }

    public Matriz somar(Matriz m) {
        Matriz resp = new Matriz(this.linha, this.coluna);

        if (this.coluna == m.coluna && this.linha == m.linha) {
            Celula linha1 = this.inicio;
            Celula linha2 = m.inicio;
            Celula rlinha = resp.inicio;

            while (linha1 != null) {
                Celula a = linha1;
                Celula b = linha2;
                Celula r = rlinha;

                while (a != null) {
                    r.setValor(a.getValor() + b.getValor());
                    a = a.dir;
                    b = b.dir;
                    r = r.dir;
                }

                linha1 = linha1.inf;
                linha2 = linha2.inf;
                rlinha = rlinha.inf;
            }
        }
        return resp;
    }

    public Matriz multiplicar(Matriz m) {
        Matriz resp = new Matriz(this.linha, m.coluna);

        if (this.coluna == m.linha) {
            Celula linhaA = this.inicio;
            Celula linhaResp = resp.inicio;

            while (linhaA != null) {
                Celula colunaB = m.inicio;
                Celula respAtual = linhaResp;

                while (colunaB != null) {
                    int soma = 0;
                    Celula a = linhaA;
                    Celula b = colunaB;

                    while (a != null && b != null) {
                        soma += a.getValor() * b.getValor();
                        a = a.dir;
                        b = b.inf;
                    }

                    respAtual.setValor(soma);
                    respAtual = respAtual.dir;
                    colunaB = colunaB.dir;
                }

                linhaA = linhaA.inf;
                linhaResp = linhaResp.inf;
            }
        }

        return resp;
    }

    public void mostrarDiagonalPrincipal() {
        if (this.linha != this.coluna) {
            System.out.println("Matriz nao quadrada");
            return;
        }
        System.out.println(getDiagonalPrincipal(this.inicio, true));
    }

    private String getDiagonalPrincipal(Celula celula, boolean primeiro) {
        if (celula == null) {
            return "";
        }
        String resp = (primeiro ? "" : " ") + celula.getValor();
        if (celula.inf != null && celula.inf.dir != null) {
            resp += getDiagonalPrincipal(celula.inf.dir, false);
        }
        return resp;
    }

    public void mostrarDiagonalSecundaria() {
        if (this.linha != this.coluna) {
            System.out.println("Matriz nao quadrada");
            return;
        }
        Celula aux = this.inicio;
        while (aux.dir != null) {
            aux = aux.dir;
        }
        System.out.println(getDiagonalSecundaria(aux, true));
    }

    private String getDiagonalSecundaria(Celula celula, boolean primeiro) {
        if (celula == null) {
            return "";
        }
        String resp = (primeiro ? "" : " ") + celula.getValor();
        if (celula.inf != null && celula.inf.esq != null) {
            resp += getDiagonalSecundaria(celula.inf.esq, false);
        }
        return resp;
    }

    public void mostrar() {
        Celula lin = this.inicio;
        while (lin != null) {
            Celula cel = lin;
            boolean primeiro = true;
            while (cel != null) {
                if (primeiro) {
                    System.out.print(cel.getValor());
                    primeiro = false;
                } else {
                    System.out.print(" " + cel.getValor());
                }
                cel = cel.dir;
            }
            System.out.println();
            lin = lin.inf;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int casos = sc.nextInt();

        for (int t = 0; t < casos; t++) {
            int l1 = sc.nextInt();
            int c1 = sc.nextInt();
            Matriz m1 = new Matriz(l1, c1);
            m1.preencher(sc);

            Matriz m2 = new Matriz(l1, c1);
            m2.preencher(sc);

            m1.mostrarDiagonalPrincipal();
            m2.mostrarDiagonalSecundaria();

            Matriz soma = m1.somar(m2);
            soma.mostrar();

            Matriz produto = m1.multiplicar(m2);
            produto.mostrar();
        }

        sc.close();
    }
}