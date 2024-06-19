package Trabalho;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        ListaCDEncadeada lista = new ListaCDEncadeada();
        

        int esc = 0;
        while (esc != 5) {
            System.out.println("");
            System.out.println("--------------------------------------------");
            System.out.println("O que voce deseja fazer?");
            System.out.println("1 - Inserir ponto;");
            System.out.println("2 - Listar pontos;");
            System.out.println("3 - Listar item especifico;");
            System.out.println("4 - Listar vizinhos de um ponto especifico;");
            System.out.println("5 - sair.");
            System.out.println("--------------------------------------------");
            esc = e.nextInt();

            switch (esc) {
                case 1:
                    Ponto p = new Ponto();
                    System.out.println("Inserir ponto: ");
                    System.out.println("X: ");
                    p.setX(e.nextInt());
                    System.out.println("Y: ");
                    p.setY(e.nextInt());
                    System.out.println("Z: ");
                    p.setZ(e.nextInt());
                    lista.adicionar(p);
                    break;
                case 2:
                    System.out.println("Imprimindo pontos... ");
                    lista.listar();
                    break;
                case 3:
                    System.out.println("Qual indice do ponto que deseja verificar?");
                    int index = e.nextInt();
                    if (lista.listarIndice(index-1) == null) {
                        System.out.println("Numero muito grande!");
                    }else{
                        System.out.println(lista.listarIndice(index-1));
                    }
                    break;
                case 4:
                    System.out.println("Vizinhos de qual ponto quer verificar?");
                    System.out.println("X: ");
                    int x = e.nextInt();
                    System.out.println("Y: ");
                    int y = e.nextInt();
                    System.out.println("Z: ");
                    int z = e.nextInt();
                    lista.listarVizinhos(x,y,z);
                    break;
                case 5:
                    System.out.println("Saiu!");
                    break;
                default:
                    System.out.println("Invalido");
                    ;
            }
        }
    }
}
