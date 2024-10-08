package Agenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Tela_consulta extends JPanel {

	private JTable table;
	private DefaultTableModel model;
	private LinkedList<Contato> contatos;

	public Tela_consulta() {
		setLayout(new MigLayout("", "[][87.00px][350.00,grow][grow,right]", "[][][446px]"));

		contatos = carregarContatos();

		model = new DefaultTableModel(new Object[] { "Nome", "Apelido", "Email", "Telefone" }, 0);
		table = new JTable(model);

		for (Contato contato : contatos) {
			model.addRow(
					new Object[] { contato.getNome(), contato.getApelido(), contato.getEmail(), contato.getNumero() });
		}

		JButton btn_voltar = new JButton("Voltar");
		btn_voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				voltar();
			}
		});
		add(btn_voltar, "cell 1 0,alignx left");

		JLabel label = new JLabel("Buscar por nome:");
		add(label, "cell 1 1,alignx left");

		JTextField buscaField = new JTextField(15);
		add(buscaField, "cell 2 1,growx");

		JButton buscarButton = new JButton("Buscar");
		add(buscarButton, "cell 3 1,alignx right");
		buscarButton.addActionListener(e -> buscarContato(buscaField.getText()));

		add(new JScrollPane(table), "cell 1 2 3 1,grow");
	}

	private LinkedList<Contato> carregarContatos() {
		Arquivo a = new Arquivo("agenda");
		return a.ler();
	}

	private void buscarContato(String nome) {
		model.setRowCount(0);
		for (Contato contato : contatos) {
			if (contato.getNome().toLowerCase().contains(nome.toLowerCase())) {
				model.addRow(new Object[] { contato.getNome(), contato.getApelido(), contato.getEmail(),
						contato.getNumero() });
			}
		}
		if (model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "Contato não encontrado.");
		}
	}

	public void voltar() {
		Menu.frame.setContentPane(new Inicio());
		Menu.frame.setVisible(true);
	}
}
