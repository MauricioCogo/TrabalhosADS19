package Agenda;

import javax.swing.JPanel;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Inicio extends JPanel {

	/**
	 * Create the panel.
	 */
	public Inicio() {
		setLayout(new MigLayout("", "[grow][][grow]", "[][][]"));
		
		JLabel agenda = new JLabel("Agenda do Mauricio!!!");
		agenda.setFont(new Font("Tahoma", Font.PLAIN, 19));
		add(agenda, "cell 1 1,growx,aligny center");
		
		JLabel fotomacaco = new JLabel("");
		fotomacaco.setIcon(new ImageIcon(Inicio.class.getResource("/Agenda/jiminlar-macaco-memes.gif")));
		add(fotomacaco, "cell 1 2");

	}

}
