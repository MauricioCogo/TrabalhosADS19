package JogodaVelha;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Janela extends JFrame {

	static Janela frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Janela();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setTitle("Jogo da velha maneiro!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Janela() {
		initComponents();
		this.setContentPane(new TelaInicial());
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		setLocationRelativeTo(null);
	}

}
