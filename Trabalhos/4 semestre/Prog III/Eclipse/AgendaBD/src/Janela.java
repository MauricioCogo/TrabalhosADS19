import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Janela extends JFrame {

	private JPanel contentPane;
	private Connection conexao;
	private JTextArea saida;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela frame = new Janela();
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
	public Janela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[111.00][grow,center][grow,right]", "[][][133.00,grow][]"));
		
		JButton btn_buscar = new JButton("Consultar");
		btn_buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarFuncionarios();
			}
		});
		contentPane.add(btn_buscar, "cell 1 1,alignx center");
		
		saida = new JTextArea();
		saida.setEditable(false);
		saida.setEnabled(false);
		contentPane.add(saida, "cell 1 2,grow");
	}
	
	public void consultarFuncionarios() {
		conectar();
		String sql = "SELECT * FROM funcionario";
		
		try {
			PreparedStatement ps = (PreparedStatement) conexao.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				saida.append("ID: " + rs.getString("id")+"\n");
				saida.append("NOME: " + rs.getString("nome")+"\n");
				saida.append("EMAIL: " + rs.getString("email")+"\n");
				saida.append("CARGO: " + rs.getString("cargo")+"\n");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void conectar() {
		try {
			this.conexao = DriverManager.getConnection("jdbc:mysql://localhost/agenda","root","");
			System.out.println("Conectou!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void encerrar() {
		try {
			this.conexao.close();
			System.out.println("Fechou!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
