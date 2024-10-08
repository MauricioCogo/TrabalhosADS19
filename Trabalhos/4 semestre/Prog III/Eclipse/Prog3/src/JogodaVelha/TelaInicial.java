package JogodaVelha;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class TelaInicial extends JPanel {

	private JTextField field_name;
	private JTextField field_name2;
	private JTextField field_simbol1;
	private JTextField field_simbol2;
	private JLabel txt_name;
	private JLabel txt_name2;
	private JLabel txt_simbol1;
	private JLabel txt_simbol2;
	private JButton btn_comecar;

	/**
	 * Create the panel.
	 */
	public TelaInicial() {
		setLayout(new MigLayout("", "[][grow][grow][fill]", "[][][][][][][][][][]"));

		JLabel txtBemVindo = new JLabel("Bem vindo ao Jogo da Velha");
		txtBemVindo.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(txtBemVindo, "cell 1 1,growy");

		txt_name = new JLabel("Nome do player 1:");
		add(txt_name, "cell 0 2");

		field_name = new JTextField();
		add(field_name, "cell 1 2,growx");
		field_name.setColumns(10);

		txt_name2 = new JLabel("Nome do player 2:");
		add(txt_name2, "cell 0 4,alignx trailing");

		field_name2 = new JTextField();
		add(field_name2, "cell 1 4,growx");
		field_name2.setColumns(10);

		txt_simbol1 = new JLabel("Simbolo do player 1:");
		add(txt_simbol1, "cell 0 6,alignx trailing");

		field_simbol1 = new JTextField();
		add(field_simbol1, "cell 1 6,growx");
		field_simbol1.setColumns(10);

		txt_simbol2 = new JLabel("Simbolo do player 2:");
		add(txt_simbol2, "cell 0 7,alignx trailing");

		field_simbol2 = new JTextField();
		add(field_simbol2, "cell 1 7,growx");
		field_simbol2.setColumns(10);

		JCheckBox chk_computador = new JCheckBox("Jogar contra o computador?");
		chk_computador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chk_computador.isSelected()) {
					field_name2.setEditable(false);
					field_name2.setEnabled(false);
					field_name2.setText("IA");
				} else {
					field_name2.setEditable(true);
					field_name2.setEnabled(true);

				}
			}
		});
		add(chk_computador, "cell 1 3");

		JCheckBox chck_padrao = new JCheckBox("Simbolos padr�o (X e O)");
		chck_padrao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chck_padrao.isSelected()) {
					field_simbol1.setEditable(false);
					field_simbol1.setEnabled(false);
					field_simbol2.setEditable(false);
					field_simbol2.setEnabled(false);
					field_simbol1.setText("X");
					field_simbol2.setText("O");
				} else {
					field_simbol1.setEditable(true);
					field_simbol1.setEnabled(true);
					field_simbol2.setEditable(true);
					field_simbol2.setEnabled(true);
					field_simbol1.setText("");
					field_simbol2.setText("");
				}
			}
		});
		add(chck_padrao, "cell 1 5");

		btn_comecar = new JButton("Come\u00E7ar");
		btn_comecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				char simbol1 = 'X';
				char simbol2 = 'O';

				if (verifyStrings()) {
					if (field_simbol1.getText().length() > 1) {
						simbol1 = field_simbol1.getText().toUpperCase().charAt(0);
						JOptionPane.showMessageDialog(btn_comecar.getParent(),
								"Voc� digitou mais de um caractere para o player 1. O simbolo dele ser�: " + simbol1);
					}
					if (field_simbol2.getText().length() > 1) {
						simbol2 = field_simbol2.getText().toUpperCase().charAt(0);
						JOptionPane.showMessageDialog(btn_comecar.getParent(),
								"Voc� digitou mais de um caractere para o player 2. O simbolo dele ser�: " + simbol2);
					}

					Player p1 = new Player();
					p1.setName(field_name.getText());
					p1.setSymbol(field_simbol1.getText());
					Player p2 = new Player();
					p2.setName(field_name2.getText());
					p2.setSymbol(field_simbol2.getText());
					p2.setIA(chk_computador.isSelected());
					System.out.println(p1.toString());
					System.out.println(p2.toString());
					
					Janela.frame.setContentPane(new TelaJogo(p1,p2));
				}
			}
		});
		add(btn_comecar, "cell 1 9");
	}

	public boolean verifyStrings() {

		if (field_name.getText().isBlank()) {
			JOptionPane.showMessageDialog(btn_comecar.getParent(), "Nome do player 1 vazio!");
			return false; // Interrompe a execu��o do m�todo
		}
		if (field_name2.getText().isBlank()) {
			JOptionPane.showMessageDialog(btn_comecar.getParent(), "Nome do player 2 vazio!");
			return false; // Interrompe a execu��o do m�todo
		}
		if (field_simbol1.getText().isBlank()) {
			JOptionPane.showMessageDialog(btn_comecar.getParent(), "Simbolo do player 1 vazio!");
			return false; // Interrompe a execu��o do m�todo
		}
		if (field_simbol2.getText().isBlank()) {
			JOptionPane.showMessageDialog(btn_comecar.getParent(), "Simbolo do player 2 vazio!");
			return false; // Interrompe a execu��o do m�todo
		}
		if (field_simbol1.getText().toUpperCase().charAt(0) == field_simbol2.getText().toUpperCase().charAt(0)) {
			JOptionPane.showMessageDialog(btn_comecar.getParent(),
					"N�o � possivel colocar o mesmo simbolo para ambos jogadores!");
			return false;
		}
		return true;

	}

	public static void createAndShowGUI() {
		javax.swing.JFrame frame = new javax.swing.JFrame("Jogo da Velha");
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new TelaInicial());
		frame.setSize(450, 300);
		frame.setLocationRelativeTo(null); // Centraliza a janela
		frame.setVisible(true);
	}
}
