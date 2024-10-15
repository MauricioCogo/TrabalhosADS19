package JogodaVelha;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class TelaJogo extends JPanel {

    private Player p1;
    private Player p2;
    private Player currentP;
    private Stack<String[][]> pilha = new Stack<>();
    private Random r = new Random();
    private Tabuleiro tab = new Tabuleiro();
    
    private JLabel txt_vez;
    private JButton btn_refazer;

    public TelaJogo(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.currentP = p1; // Inicializa currentP aqui

        setLayout(new MigLayout("wrap 3", "[grow][grow][grow]", "[]10[]"));

        for (int i = 0; i < 9; i++) {
            JLabel label = createBoardLabel(i);
            add(label, "grow");
        }

        // Initialize txt_vez and btn_refazer
        txt_vez = new JLabel("Current turn: ");
        btn_refazer = new JButton("Refazer");
        add(txt_vez, "span 3"); // Span across 3 columns
        add(btn_refazer, "span 3"); // Span across 3 columns

        main();
    }

    public void main() {
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("    jogadores   ");
        System.out.println(p1.getName() + " : " + p1.getSymbol());
        System.out.println(p2.getName() + " : " + p2.getSymbol());
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println();

        tab.create();
    }

    private JLabel createBoardLabel(int index) {
        JLabel jLabel = new JLabel("", SwingConstants.CENTER);
        jLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel.setOpaque(true);
        jLabel.setName((index / 3) + "," + (index % 3));

        ImageIcon icon = new ImageIcon(getClass().getResource("./fundo.png"));
        jLabel.setIcon(icon);

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
        System.out.println("linha: " + row);
        System.out.println("coluna: " + col);
        ImageIcon icon = null;

        if (!currentP.getIA()) {
            if (tab.shot(currentP, verifyPosition(row), verifyPosition(col))) {
                if (currentP == p1) {
                    icon = new ImageIcon(getClass().getResource("./p1.png"));
                    currentP = p2;
                } else {
                    icon = new ImageIcon(getClass().getResource("./p2.png"));
                    currentP = p1;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Posição inválida!");
            }
        } else {
            // Lógica para a IA aqui (caso o jogador atual seja IA)
        }

        if (icon != null) {
            jLabel.setIcon(icon);
        }
    }

    public static int verifyPosition(int num) {
        switch (num) {
            case 0:
                return 0;
            case 1:
                return 2;
            case 2:
                return 4;
        }
        return 0;
    }
}
