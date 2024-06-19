package Aula08_03;

import java.util.Scanner;

public class Exercicio3_1 {
    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        Exercicio3_2 c = new Exercicio3_2();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("    Jogo do Palpite!");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("");
        
        System.out.println("Digite um numero até acertar!");
        c.setNum();
        while (c.getPalpite()) {
            int num = e.nextInt();
            c.getAcerto(num);
        }
        System.out.println(c.getNum());
    }
}
