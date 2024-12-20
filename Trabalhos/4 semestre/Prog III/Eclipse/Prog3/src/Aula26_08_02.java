import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Aula26_08_02 extends JFrame {

	private JPanel contentPane;
	private JTextField txt_result;
	private int count;

	private LinkedList<JButton> lista;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		String a = "";
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aula26_08_02 frame = new Aula26_08_02();
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

	public Aula26_08_02() {
		this.lista = new LinkedList<>();

		Calculadora c = new Calculadora();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][][grow]", "[][][][][][][][]"));
		setLocationRelativeTo(null);

		txt_result = new JTextField();
		contentPane.add(txt_result, "cell 3 1,growx");
		txt_result.setColumns(10);

		JButton bt_um = new JButton("1");
		contentPane.add(bt_um, "cell 2 2");
		bt_um.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "1");
			}
		});

		JButton bt_dois = new JButton("2");
		contentPane.add(bt_dois, "flowx,cell 3 2");
		bt_dois.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "2");
			}
		});

		JButton bt_tres = new JButton("3");
		contentPane.add(bt_tres, "cell 3 2");
		bt_tres.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "3");
			}
		});

		JButton bt_quatro = new JButton("4");
		contentPane.add(bt_quatro, "cell 2 3");
		bt_quatro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "4");
			}
		});

		JButton bt_cinco = new JButton("5");
		contentPane.add(bt_cinco, "flowx,cell 3 3");
		bt_cinco.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "5");
			}
		});

		JButton bt_seis = new JButton("6");
		contentPane.add(bt_seis, "cell 3 3");
		bt_seis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "6");
			}
		});

		JButton bt_sete = new JButton("7");
		contentPane.add(bt_sete, "cell 2 4");
		bt_sete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "7");
			}
		});

		JButton bt_oito = new JButton("8");
		contentPane.add(bt_oito, "flowx,cell 3 4");
		bt_oito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "8");
			}
		});

		JButton bt_nove = new JButton("9");
		contentPane.add(bt_nove, "cell 3 4");
		bt_nove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "9");
			}
		});

		JButton bt_zero = new JButton("0");
		contentPane.add(bt_zero, "cell 3 5");
		bt_zero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText(txt_result.getText() + "0");
			}
		});
		
		JButton limpar = new JButton("CE");
		contentPane.add(limpar, "cell 0 6");
		limpar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txt_result.setText("");
				
			}
		});

		JButton bt_calcular = new JButton("Calcular");
		contentPane.add(bt_calcular, "cell 3 7");
		bt_calcular.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txt_result.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "N�o foi possivel calcular pois n�o possui numeros!");
				} else {
					String[] op = new String[2];

					if (c.getOperacao() == null) {
						JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois n�o tem operador!");
					} else {
						op = txt_result.getText().split(Pattern.quote(c.getOperacao()));

						if (op.length == 1) {
							JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois n�o tem o segundo numero!!");
						} else {
							if ((txt_result.getText().matches("[0-9]+\\Q" + c.getOperacao() + "\\E[0-9]+"))) {

								c.setNum1(Integer.parseInt(op[0]));
								c.setNum2(Integer.parseInt(op[1]));

								switch (c.getOperacao()) {
								case "+":
									txt_result.setText(c.soma() + "");
									break;
								case "-":
									txt_result.setText(c.subtracao() + "");
									break;
								case "*":
									txt_result.setText(c.multi() + "");
									break;
								case "/":
									txt_result.setText(c.divisao() + "");
									break;
								case "^":
									txt_result.setText(c.potencia() + "");
									break;
								}
							}else {
								JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois contem letras na senten�a!");
							}
						}
					}
				}
			}
		});

		JButton bt_mais = new JButton("+");
		contentPane.add(bt_mais, "cell 0 2");
		bt_mais.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifica()) {
					if (verifica_num(txt_result.getText())) {
						txt_result.setText(txt_result.getText() + "+");
						c.setOperacao("+");
					} else {
						JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois ja possui outro operador!");
					}
				} else {
					txt_result.setText(txt_result.getText() + "+");
					c.setOperacao("+");
				}

			}
		});

		JButton bt_menos = new JButton("-");
		contentPane.add(bt_menos, "cell 0 3");
		bt_menos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifica()) {
					if (verifica_num(txt_result.getText())) {
						txt_result.setText(txt_result.getText() + "-");
						c.setOperacao("-");
					} else {
						JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois ja possui outro operador!");
					}
				} else {
					txt_result.setText(txt_result.getText() + "-");
					c.setOperacao("-");
					count++;
				}
			}
		});

		JButton bt_vezes = new JButton("*");
		contentPane.add(bt_vezes, "cell 0 4");
		bt_vezes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifica()) {
					if (verifica_num(txt_result.getText())) {
						txt_result.setText(txt_result.getText() + "*");
						c.setOperacao("*");
					} else {
						JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois ja possui outro operador!");
					}
				} else {
					txt_result.setText(txt_result.getText() + "*");
					c.setOperacao("*");
				}

			}
		});

		JButton bt_divisao = new JButton("/");
		contentPane.add(bt_divisao, "cell 0 5");
		bt_divisao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifica()) {
					if (verifica_num(txt_result.getText())) {
						txt_result.setText(txt_result.getText() + "/");
						c.setOperacao("/");
					} else {
						JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois ja possui outro operador!");
					}
				} else {
					txt_result.setText(txt_result.getText() + "/");
					c.setOperacao("/");
				}

			}
		});
		JButton potencia = new JButton("^");
		contentPane.add(potencia, "cell 4 2");
		potencia.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (verifica()) {
					if (verifica_num(txt_result.getText())) {
						txt_result.setText(txt_result.getText() + "^");
						c.setOperacao("^");
					} else {
						JOptionPane.showMessageDialog(null, "N�o � possivel calcular pois ja possui outro operador!");
					}
				} else {
					txt_result.setText(txt_result.getText() + "^");
					c.setOperacao("^");
				}

			}
		});
	}

	public boolean verifica_num(String txt) {
		int num = Integer.parseInt(txt);
		if (num < 0) {
			return true;
		}

		return false;
	}

	public boolean verifica() {
		if (txt_result.getText().contains("+") || txt_result.getText().contains("-")
				|| txt_result.getText().contains("/") || txt_result.getText().contains("*") || txt_result.getText().contains("^")) {
			return true;
		}
		return false;
	}
}
