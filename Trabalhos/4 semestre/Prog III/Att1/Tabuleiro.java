public class Tabuleiro {

    private String[][] tabuleiro = new String[5][5];

    public void create() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tabuleiro[i][j] = " ";
            }
        }

        for (int i = 0; i < 5; i++) {
            tabuleiro[i][1] = "|";
            tabuleiro[i][3] = "|";
        }

        for (int i = 0; i < 5; i++) {
            tabuleiro[1][i] = "-";
            tabuleiro[3][i] = "-";

        }
    }

    public void getBoard() {

        if (this.tabuleiro[1][1] != null) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    System.out.print(tabuleiro[i][j]);
                }
                System.out.println();
            }
        } else {
            System.out.println("O tabuleiro está vazio!");
        }
    }

    public Boolean shot(Player j, int x, int y) {
        if (tabuleiro[x][y].equals(" ")) {
            tabuleiro[x][y] = j.getSymbol();
            return true;
        } else {
            return false;
        }
    }

    public Boolean verify(Player j) {

        if (verifyLine(j)) {
            return false;
        }
        if (verifyColum(j)) {
            return false;
        }
        if (verifyDiagona(j)) {
            return false;
        }

        return true;
    }

    public boolean verifyLine(Player j1) {
        for (int j = 0; j < tabuleiro.length; j += 2) {
            if ((tabuleiro[j][0].equals(j1.getSymbol())) && (tabuleiro[j][2].equals(j1.getSymbol())) && (tabuleiro[j][4].equals(j1.getSymbol()))) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyColum(Player j1) {
        for (int j = 0; j < tabuleiro.length; j += 2) {
            if ((tabuleiro[0][j].equals(j1.getSymbol())) && (tabuleiro[2][j].equals(j1.getSymbol())) && (tabuleiro[4][j].equals(j1.getSymbol()))) {
                return true;
            }
        }
        return false;
    }

    public Boolean verifyDiagona(Player j1) {
        if ((tabuleiro[0][0].equals(j1.getSymbol())) && (tabuleiro[2][2].equals(j1.getSymbol()))
                && (tabuleiro[4][4].equals(j1.getSymbol()))) {
            return true;
        }
        if ((tabuleiro[0][4].equals(j1.getSymbol())) && (tabuleiro[2][2].equals(j1.getSymbol()))
                && (tabuleiro[4][0].equals(j1.getSymbol()))) {
            return true;
        }
        return false;
    }
}
