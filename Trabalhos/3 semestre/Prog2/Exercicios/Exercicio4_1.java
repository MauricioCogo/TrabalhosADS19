package Aula08_03;

import java.util.Scanner;

public class Exercicio4_1 {

    public static void main(String[] args) {

        boolean teste = true;

        Scanner e = new Scanner(System.in);
        Exercicio4_2 p1 = new Exercicio4_2();

        System.out.println("Digite seu nome: ");
        p1.setNome(e.nextLine());
        System.out.println("Digite sua idade: ");
        p1.setIdade(e.nextInt());

        while (teste) {
            System.out.println("");
            System.out.println("Digite 1 para cadastrar uma consulta");
            System.out.println("Digite 2 para ver consultas");
            System.out.println("Digite 3 para sair");
            String esc = e.next();
            
            
            switch (esc) {
                case "1":
                    System.out.println("Digite a consulta: ");
                    p1.setCons();
                    break;
                case "2":
                    p1.getCons();
                    break;
                case "3":
                    teste = false;
                    break;
                default:
                    System.out.println("Caracter invalido");
            }

        }
    }
}
