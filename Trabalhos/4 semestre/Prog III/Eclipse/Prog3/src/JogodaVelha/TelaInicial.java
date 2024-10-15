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
	private JLabel txt_name;
	private JLabel txt_name2;
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

		btn_comecar = new JButton("Come\u00E7ar");
		btn_comecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				char simbol1 = 'X';
				char simbol2 = 'O';

				if (verifyStrings()) {

					Player p1 = new Player();
					p1.setName(field_name.getText());
					Player p2 = new Player();
					p2.setName(field_name2.getText());
					p2.setIA(chk_computador.isSelected());
					System.out.println(p1.toString());
					System.out.println(p2.toString());
					
					Janela.frame.setContentPane(new TelaJogo(p1,p2));
					Janela.frame.setVisible(true);
				}
			}
		});
		add(btn_comecar, "cell 1 9");
	}

	public boolean verifyStrings() {

		if (field_name.getText().isBlank()) {
			JOptionPane.showMessageDialog(btn_comecar.getParent(), "Nome do player 1 vazio!");
			return false; // Interrompe a execução do método
		}
		if (field_name2.getText().isBlank()) {
			JOptionPane.showMessageDialog(btn_comecar.getParent(), "Nome do player 2 vazio!");
			return false; // Interrompe a execução do método
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
