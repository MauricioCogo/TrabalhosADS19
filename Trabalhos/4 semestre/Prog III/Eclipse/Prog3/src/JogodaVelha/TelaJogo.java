package JogodaVelha;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Stack;

public class TelaJogo extends JPanel {

    private Player p1;
    private Player p2;
    private Player currentP;
    private Stack<String[][]> pilha = new Stack<>();
    private Random r = new Random();
    private Tabuleiro tab = new Tabuleiro();

    private JLabel txt_vez;
    private JLabel txt_gerandoMovimento;
    private JButton btn_refazer;
    private JButton btn_limpar;
    private JLabel[] boardLabels;
    private boolean gameOver = false;

    public TelaJogo(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.currentP = p1;

        setLayout(new MigLayout("wrap 3", "[grow][grow][grow]", "[]10[]"));

        boardLabels = new JLabel[9];
        for (int i = 0; i < 9; i++) {
            boardLabels[i] = createBoardLabel(i);
            add(boardLabels[i], "grow");
        }

        txt_vez = new JLabel("Vez de: " + currentP.getName());
        txt_gerandoMovimento = new JLabel("");
        btn_refazer = new JButton("Refazer");
        btn_refazer.addActionListener(e -> redoLastMove());

        btn_limpar = new JButton("Reiniciar");
        btn_limpar.setVisible(false);
        btn_limpar.addActionListener(e -> resetGame());

        add(txt_vez, "span 3");
        add(txt_gerandoMovimento, "span 3");
        add(btn_refazer, "span 3");
        add(btn_limpar, "span 3");

        main();
    }

    public void main() {
        tab.create();
    }

    private JLabel createBoardLabel(int index) {
        JLabel jLabel = new JLabel("", SwingConstants.CENTER);
        jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel.setOpaque(true);
        jLabel.setName((index / 3) + "," + (index % 3));
        jLabel.setIcon(new ImageIcon(getClass().getResource("./fundo.png")));

        int row = index / 3;
        int col = index % 3;

        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleBoardClick(row, col, jLabel);
            }
        });

        return jLabel;
    }

    private void handleBoardClick(int row, int col, JLabel jLabel) {
        if (gameOver) return; // Impede cliques quando o jogo termina

        pilha.push(tab.getCopy());

        if (tab.shot(currentP, verifyPosition(row), verifyPosition(col))) {
            updateBoardDisplay(row, col, jLabel);
            System.out.println("Jogada de " + currentP.getName() + " na posição: " + row + ", " + col);

            if (!tab.verify(currentP)) {
                JOptionPane.showMessageDialog(this, currentP.getName() + " ganhou!");
                gameOver = true; // Define o jogo como terminado
                btn_refazer.setEnabled(false);
                btn_limpar.setVisible(true);
                return;
            }
            if (tab.draw()) {
                JOptionPane.showMessageDialog(this, "Empate!");
                gameOver = true; // Define o jogo como terminado
                btn_limpar.setVisible(true);
                return;
            }

            currentP = (currentP == p1) ? p2 : p1;
            txt_vez.setText("Vez de: " + currentP.getName());
            tab.getBoard();

            if (currentP.getIA()) {
                txt_gerandoMovimento.setText("Gerando movimento de IA...");
                Timer timer = new Timer(2000, e -> {
                    performIATurn();
                    txt_gerandoMovimento.setText("");
                });
                timer.setRepeats(false);
                timer.start();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Posição inválida!");
        }
    }


    private void updateBoardDisplay(int row, int col, JLabel jLabel) {
        ImageIcon icon = new ImageIcon(getClass().getResource(currentP == p1 ? "./p1.png" : "./p2.png"));
        jLabel.setIcon(icon);
    }

    private void performIATurn() {
        boolean validMove = false;
        while (!validMove) {
            int x = r.nextInt(3);
            int y = r.nextInt(3);
            validMove = tab.shot(currentP, verifyPosition(y), verifyPosition(x));
            if (validMove) {
                updateBoardDisplay(y, x, boardLabels[y * 3 + x]);
                System.out.println("Jogada da IA na posição: " + y + ", " + x);
                if (!tab.verify(currentP)) {
                    JOptionPane.showMessageDialog(this, currentP.getName() + " ganhou!");
                    btn_refazer.setEnabled(false);
                    btn_limpar.setVisible(true);
                    return;
                }
                if (tab.draw()) {
                    JOptionPane.showMessageDialog(this, "Empate!");
                    btn_limpar.setVisible(true);
                    return;
                }
                tab.getBoard();
                currentP = p1;
                txt_vez.setText("Vez de: " + currentP.getName());
            }
        }
    }

    private void redoLastMove() {
        if (!pilha.isEmpty()) {
            tab.setCount(tab.getCount() + 1);
            tab.setTabuleiro(pilha.pop());
            updateBoardLabels();
            txt_vez.setText("Vez de: " + currentP.getName());
            System.out.println("Última jogada desfeita.");
        } else {
            JOptionPane.showMessageDialog(this, "Não há jogadas para desfazer!");
        }
    }

    private void updateBoardLabels() {
        String[][] board = tab.getBoard();
        for (int i = 0; i < 5; i += 2) {
            for (int j = 0; j < 5; j += 2) {
                int index = (i / 2) * 3 + (j / 2);
                JLabel jLabel = boardLabels[index];
                String symbol = board[i][j];
                if (symbol.equals(" ")) {
                    jLabel.setIcon(new ImageIcon(getClass().getResource("./fundo.png")));
                } else {
                    jLabel.setIcon(new ImageIcon(getClass().getResource(symbol.equals(p1.getSymbol()) ? "./p1.png" : "./p2.png")));
                }
            }
        }
    }

    private void resetGame() {
        tab.create();
        tab.setCount(0);
        pilha.clear();
        currentP = p1;
        txt_vez.setText("Vez de: " + currentP.getName());
        btn_refazer.setEnabled(true);
        btn_limpar.setVisible(false);
        gameOver = false; // Reseta o estado do jogo
        updateBoardLabels();
    }


    public static int verifyPosition(int num) {
        switch (num) {
            case 0: return 0;
            case 1: return 2;
            case 2: return 4;
        }
        return 0;
    }
}
