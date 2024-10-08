package Agenda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class Tela_cadastro extends JPanel {

    private JTextField nomeField, emailField, telefoneField, apelidoField;

    public Tela_cadastro() {
        setLayout(new MigLayout("", "[67.00px][204px,grow]", "[][fill][fill][fill][][]"));
        
		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltar();
			}
		});
		add(btn_voltar, "cell 0 0,alignx right");

        add(new JLabel("Nome:"), "cell 0 1,grow");
        nomeField = new JTextField();
        add(nomeField, "cell 1 1,grow");

        add(new JLabel("Email:"), "cell 0 2,grow");
        emailField = new JTextField();
        add(emailField, "cell 1 2,grow");
        
        add(new JLabel("Apelido:"), "cell 0 3,grow");
        apelidoField = new JTextField();
        add(apelidoField, "cell 1 3,grow");

        add(new JLabel("Telefone:"), "cell 0 4,grow");
        telefoneField = new JTextField();
        add(telefoneField, "cell 1 4,grow");

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(e -> salvarContato());
        add(salvarButton, "cell 1 5,alignx right,growy");
    }

    private void salvarContato() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();
        String apelido = apelidoField.getText();

        if (!nome.isEmpty() && !email.isEmpty() && !telefone.isEmpty() && !apelido.isEmpty()) {
            Contato novoContato = new Contato(nome, apelido, telefone, email);
            Arquivo a = new Arquivo("agenda");
            a.gravar(novoContato);
            JOptionPane.showMessageDialog(this, "Contato salvo!");
            nomeField.setText("");
            emailField.setText("");
            telefoneField.setText("");
            apelidoField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
        }
    }
    
	public void voltar() {
		Menu.frame.setContentPane(new Inicio());
		Menu.frame.setVisible(true);
	}
}
