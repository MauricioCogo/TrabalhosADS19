import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        Tabuleiro tab = new Tabuleiro();
        Player player_01 = new Player();
        Player player_02 = new Player();
        System.out.println();
        System.out.println("Nome do Player 1: ");
        player_01.setName(e.nextLine());
        System.out.println("Nome do Player 2(digite 'IA' para jogar com computador): ");
        player_02.setName(e.nextLine());

        System.out.println("Simbolo do Player 1 (digite 'null' para ser player 1 = X e player 2 = O):");
        player_01.setSymbol(e.next());
        if (player_01.getSymbol().equals("null")) {
            player_01.setSymbol("X");
            player_02.setSymbol("O");
        } else {
            if (player_02.getName().equals("null")) {

            }
            System.out.println("Simbolo do Player 2:");
            player_02.setSymbol(e.next());
        }

        System.out.println(player_01.toString());
        System.out.println(player_02.toString());

        tab.create();
        boolean ve = true;
        while (ve) {
            tab.getBoard();

            System.out.println("Jogada do player 1");
            System.out.println("X (1, 2 ou 3): ");
            int x = e.nextInt();
            System.out.println("Y: ");
            int y = e.nextInt();

            if (tab.shot(player_01, verifyPosition(y), verifyPosition(x))) {
                tab.getBoard();

                if (!tab.verify(player_01)) {
                    System.out.println("player 1 ganhou!");
                    ve = false;
                } else {

                    if (player_02.getName().equals("IA")) {
                        while (tab.shot(player_02, player_02.getRandomNum(), player_02.getRandomNum())) {
                            if (!tab.verify(player_02)) {
                                System.out.println("Player 2 ganhou!");
                                ve = false;
                            }
                        }

                    } else {
                        System.out.println("Jogada do player 2");
                        System.out.println("X (1, 2 ou 3): ");
                        int x2 = e.nextInt();
                        System.out.println("Y: ");
                        int y2 = e.nextInt();

                        if (tab.shot(player_02, verifyPosition(x2), verifyPosition(y2))) {

                            tab.getBoard();
                            if (!tab.verify(player_02)) {
                                System.out.println("Player 2 ganhou!");
                                ve = false;
                            }

                        } else {
                            System.out.println("Jogada invalida!");
                        }
                    }
                }
            } else {
                System.out.println("Jogada invalida!");
                System.out.println("");
                System.out.println("");
            }
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
                default:
                    System.out.println("Numero invalido!");
                    break;
            }
        }
        return 0;
    }

}