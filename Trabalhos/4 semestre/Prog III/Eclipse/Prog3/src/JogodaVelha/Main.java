package JogodaVelha;

import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner e = new Scanner(System.in);
        Tabuleiro tab = new Tabuleiro();
        Player player_01 = new Player();
        Player player_02 = new Player();
        Random r = new Random();
        Stack<String[][]> pilha = new Stack<>();

        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Bem vindo ao jogo da velha!");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println();

        System.out.println("Nome do Player 1: ");
        player_01.setName(e.nextLine());

        System.out.println("Jogar contra computador? (sim ou não)");
        player_02.setIA(verifyString(e.next()));

        if (!player_02.getIA()) {
            System.out.println("Nome do Player 2: ");
            player_02.setName(e.next());
        }

        System.out.println("Simbolo do Player 1 (digite 'X' para ser player 1 = X e player 2 = O):");
        player_01.setSymbol(e.next().toUpperCase());

        if (player_01.getSymbol().equals("X")) {
            player_01.setSymbol("X");
            player_02.setSymbol("O");
        } else {
            System.out.println("Simbolo do Player 2:");
            player_02.setSymbol(e.next());
            while (player_01.getSymbol().equals(player_02.getSymbol())) {
                System.out.println("O simbolo do player 1 não pode ser igual ao do player 2!");
                player_02.setSymbol(e.next());
            }
        }

        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("    jogadores   ");
        System.out.println(player_01.getName() + " : " + player_01.getSymbol());
        System.out.println(player_02.getName() + " : " + player_02.getSymbol());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println();

        tab.create();
        boolean ve = true;
        while (ve) {
            pilha.push(tab.getCopy());

            tab.getBoard();

            System.out.println("Jogada de " + player_01.getName());
            System.out.println("Coordenada X (1, 2 ou 3): ");
            int x = e.nextInt();
            System.out.println("Coordenada Y (1, 2 ou 3): ");
            int y = e.nextInt();

            if (tab.shot(player_01, verifyPosition(y), verifyPosition(x))) {
                tab.getBoard();

                if (!tab.verify(player_01)) {
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println("    " + player_01.getName() + " ganhou!");
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    break;
                }

                if (player_02.getIA()) {
                    boolean ia = true;
                    System.out.println(" ");
                    System.out.println("Jogada da IA");
                    System.out.println(" ");
                    Thread.sleep(2000);

                    while (ia) {
                        x = r.nextInt(3) + 1;
                        y = r.nextInt(3) + 1;
                        if (tab.shot(player_02, verifyPosition(x), verifyPosition(x))) {
                            System.out.println("");
                            ia = false;
                        } else {
                            x = r.nextInt(3) + 1;
                            y = r.nextInt(3) + 1;
                        }
                    }

                    tab.getBoard();
                    System.out.println("Deseja voltar uma jogada?");
                    if (verifyString(e.next())) {
                        tab.setTabuleiro(pilha.pop());
                    }

                } else {
                    System.out.println("Jogada de " + player_02.getName());
                    System.out.println("X (1, 2 ou 3): ");
                    int x2 = e.nextInt();
                    System.out.println("Y: ");
                    int y2 = e.nextInt();

                    while (!tab.shot(player_02, verifyPosition(y2), verifyPosition(x2))) {
                        tab.getBoard();
                        System.out.println("Jogada de " + player_02.getName());
                        System.out.println("X (1, 2 ou 3): ");
                        x2 = e.nextInt();
                        System.out.println("Y: ");
                        y2 = e.nextInt();
                    }
                }

                if (!tab.verify(player_02)) {
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println("    " + player_02.getName() + " ganhou!");
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    break;
                }

            }
            if (tab.draw()) {
                System.out.println("Empate!");
                break;

            }
        }
    }

    public static boolean verifyString(String bool) {
        bool = bool.toLowerCase();
        switch (bool) {
            case "sim":
                return true;
            case "s":
                return true;
            case "positivo":
                return true;
            case "sum":
                return true;
            case "som":
                return true;
            default:
                return false;
        }
    }

    public static int verifyPosition(int num) {
        if ((num >= 0) && (num <= 3)) {
            switch (num) {
                case 1:
                    return 0;
                case 2:
                    return 2;
                case 3:
                    return 4;
            }
        } else {
            System.out.println("Numero invalido!");
        }
        return 0;
    }
}
