package trabalhoPratico1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ListaEncadeada lista = new ListaEncadeada();
        Arquivo arq = new Arquivo("contas");
        Scanner e = new Scanner(System.in);

        int esc = 0;

        while (esc != 5) {
            System.out.println("---------------------------------");
            System.out.println("--- Controle Mensal de Contas ---");
            System.out.println("---------------------------------");
            System.out.println("1 - Cadastrar Conta");
            System.out.println("2 - Remover Conta");
            System.out.println("3 - Listar Conta");
            System.out.println("4 - Alterar Conta");
            System.out.println("5 - Sair");

            System.out.println("");
            System.out.print("Escolha: ");
            esc = e.nextInt();

            switch (esc) {
                case 1:
                    lista = arq.ler();
                    System.out.println("Digite o nome, o valor e a situação da conta, sendo paga ou pendente");
                    System.out.println("Digite separadamente.");
                    Conta c = new Conta(e.next(), e.next(), e.next());
                    lista.adicionar(c);
                    arq.gravar(lista);
                    break;
                case 2:
                    lista = arq.ler();
                    
                    lista.remover(e.next());
                    
                    arq.gravar(lista);
                    break;
                case 3:
                    lista = arq.ler();
                    lista.listar();
                    arq.gravar(lista);
                    break;
                case 4:
                    e.nextLine();
                    String nome = e.next();
                    String sit = e.next();

                    if (lista.alterar(nome,sit)) {
                        System.out.println("Conta alterada com sucesso!");
                    }else{
                        System.out.println("Não foi possivel achar esta conta!");
                    }
                    arq.gravar(lista);
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalido");
            }
        }
    }
}
