import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Aula26_08 extends JFrame {

	private JPanel contentPane;
	private JTextField edit_modelo;
	private JTextField edit_cor;
	private JTextField edit_ano;
	private JTextField txt_modelo;
	private JTextField txt_cor;
	private JTextField txt_ano;
	private JButton btn_registrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aula26_08 frame = new Aula26_08();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Aula26_08() {
		setTitle("Caarros");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][::100px,center][][grow][][][]", "[][][][][][][][][][][]"));
		
		JLabel dados = new JLabel("Dados do meu MeuCarro");
		
		contentPane.add(dados, "cell 3 1,alignx center,aligny baseline");
		
		JLabel modelo = new JLabel("Modelo: ");
		contentPane.add(modelo, "cell 2 2,alignx left");
		
		edit_modelo = new JTextField();
		contentPane.add(edit_modelo, "cell 3 2,growx");
		edit_modelo.setColumns(10);
		
		
		JLabel cor = new JLabel("Cor: ");
		contentPane.add(cor, "cell 2 3,alignx left");
		
		edit_cor = new JTextField();
		contentPane.add(edit_cor, "cell 3 3,growx");
		edit_cor.setColumns(10);
		
		JLabel ano = new JLabel("Ano: ");
		contentPane.add(ano, "cell 2 4,alignx left");
		
		edit_ano = new JTextField();
		contentPane.add(edit_ano, "cell 3 4,growx");
		edit_ano.setColumns(10);
		
		btn_registrar = new JButton("Registrar");
		contentPane.add(btn_registrar, "cell 3 5,growx");
		
		JLabel lblConsultarMeuCarro = new JLabel("Consultar meu carro");
		contentPane.add(lblConsultarMeuCarro, "cell 3 6");
		
		txt_modelo = new JTextField();
		txt_modelo.setColumns(10);
		contentPane.add(txt_modelo, "cell 3 7,growx");
		
		txt_cor = new JTextField();
		txt_cor.setColumns(10);
		contentPane.add(txt_cor, "cell 3 8,growx");
		
		txt_ano = new JTextField();
		txt_ano.setColumns(10);
		contentPane.add(txt_ano, "cell 3 9,growx");
		
		btn_registrar.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				cadastrar();
				
			}
		});
		
	}
	
	public void cadastrar() {
		Veiculo v = new Veiculo();
		
		if(edit_modelo.getText().isEmpty() || edit_cor.getText().isEmpty() || edit_ano.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite todos os valores!");
		}else {
			if(edit_ano.getText().matches("\\d{4}")) {
				v.setModelo(edit_modelo.getText());
				v.setCor(edit_cor.getText());
				v.setAno(Integer.parseInt(edit_ano.getText()));
				System.out.println("Cadastrado");
				txt_modelo.setText(v.getModelo());
				txt_cor.setText(v.getCor());
				txt_ano.setText(v.getAno()+"");
			}else {
				JOptionPane.showMessageDialog(null, "Ano invalido!");
			}
		}


	
	}

}
