package Agenda;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

public class Tabelas extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabelas frame = new Tabelas();
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
	public Tabelas() {
		initComponents();

		Contato c = new Contato("G", "gg", "ggg","asda");
		DefaultTableModel m = (DefaultTableModel) table.getModel();
		m.addRow(c.toLinha());

		// JOptionPane.showMessageDialog(null, table.getValueAt(0, 0));

	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 430);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);

		this.scrollPane = new JScrollPane();

		this.table = new JTable();
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Apelido", "Telefone","Email" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		this.table.getColumnModel().getColumn(0).setResizable(false);
		this.table.getColumnModel().getColumn(0).setPreferredWidth(188);
		this.table.getColumnModel().getColumn(1).setResizable(false);
		this.table.getColumnModel().getColumn(1).setPreferredWidth(120);
		this.table.getColumnModel().getColumn(2).setResizable(false);
		this.table.getColumnModel().getColumn(2).setPreferredWidth(273);

		this.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cel = table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()) + "";
				JOptionPane.showMessageDialog(null, "Celula: " + cel);
			}
		});

		this.scrollPane.setViewportView(this.table);
		contentPane.setLayout(new MigLayout("", "[600px,grow]", "[218px,grow]"));
		this.contentPane.add(this.scrollPane, "cell 0 0,grow");
	}
}
