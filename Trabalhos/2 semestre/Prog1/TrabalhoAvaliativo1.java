package prova;

import java.util.Scanner;

public class Exercicio1 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        int[] aleatorio = new int[10];
        int cont = 0;

        System.out.println("Digite um valor de 1 a 10: ");
        int escolha = entrada.nextInt();
        if (escolha < 10) {
            for (int i = 0; i < 10; i++) {
                aleatorio[i] = (int) (Math.random() * 10);
                if (aleatorio[i] == 0) {
                    aleatorio[i] = (int) (Math.random() * 10);
                }
                System.out.println(aleatorio[i] + " " + i);
                if (escolha == aleatorio[i]) {
                    cont++;
                }
            }
            System.out.println("Seu numero aparece: " + cont);
        }else{
            System.out.println("Digite um valor valido!");
        }
    }
}
