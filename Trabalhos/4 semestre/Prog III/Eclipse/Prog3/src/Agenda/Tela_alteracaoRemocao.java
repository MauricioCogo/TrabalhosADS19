package Agenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tela_alteracaoRemocao extends JPanel {

	private JTable table;
	private DefaultTableModel model;
	private LinkedList<Contato> contatos;

	public Tela_alteracaoRemocao() {
		setLayout(new MigLayout("", "[][][452px]", "[][426px][]"));

		contatos = carregarContatos();

		model = new DefaultTableModel(new Object[][] {}, new String[] { "Nome:", "Apelido: ", "Email:", "Telefone:" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
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
		add(btn_voltar, "cell 0 0,alignx right");

		add(new JScrollPane(table), "cell 0 1 3 1,alignx left,aligny top");

		JButton editarButton = new JButton("Editar");
		editarButton.addActionListener(e -> editarContato(table.getSelectedRow()));

		JButton removerButton = new JButton("Remover");
		removerButton.addActionListener(e -> removerContato(table.getSelectedRow()));
		add(removerButton, "flowx,cell 2 2,alignx right");
		add(editarButton, "cell 2 2,alignx right");
	}

	private LinkedList<Contato> carregarContatos() {
		Arquivo a = new Arquivo("agenda");
		return a.ler();
	}

	private void mostrarDetalhes(int index) {
		Contato contato = contatos.get(index);
		String detalhes = String.format("Nome: %s\nEmail: %s\nTelefone: %s", contato.getNome(), contato.getEmail(),
				contato.getNumero());
		JOptionPane.showMessageDialog(this, detalhes, "Detalhes do Contato", JOptionPane.INFORMATION_MESSAGE);
	}

	private void removerContato(int index) {
		Arquivo a = new Arquivo("agenda");
		if (index != -1) {
			Contato contato = contatos.get(index);
			model.removeRow(index);
			a.deletar(contato.getNome());
			contatos.remove(index);
			JOptionPane.showMessageDialog(this, "Contato removido com sucesso.");
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um contato para remover.");
		}
	}

	private void editarContato(int index) {
		Arquivo a = new Arquivo("agenda");
		if (index != -1) {
			Contato contato = contatos.get(index);
			String nomeVelho = contato.getNome();
			String novoNome = JOptionPane.showInputDialog(this, "Novo Nome:", contato.getNome());
			String novoApelido = JOptionPane.showInputDialog(this, "Novo Apelido:", contato.getApelido());
			String novoEmail = JOptionPane.showInputDialog(this, "Novo Email:", contato.getEmail());
			String novoNumero = JOptionPane.showInputDialog(this, "Novo Telefone:", contato.getNumero());

			contato.setNome(novoNome);
			contato.setApelido(novoApelido);
			contato.setEmail(novoEmail);
			contato.setNumero(novoNumero);

			model.setValueAt(novoNome, index, 0);
			model.setValueAt(novoApelido, index, 1);
			model.setValueAt(novoEmail, index, 2);
			model.setValueAt(novoNumero, index, 3);

			a.editar(nomeVelho, contato);

			JOptionPane.showMessageDialog(this, "Contato atualizado com sucesso.");
		} else {
			JOptionPane.showMessageDialog(this, "Selecione um contato para editar.");
		}
	}

	private void gravarContatos() {
		Arquivo a = new Arquivo("agenda");
		for (Contato contato : contatos) {
			a.gravar(contato);
		}
	}

	public void voltar() {
		Menu.frame.setContentPane(new Inicio());
		Menu.frame.setVisible(true);
	}
}
