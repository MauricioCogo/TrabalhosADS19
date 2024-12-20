package Agenda;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

    private JPanel contentPane;
    public static Menu frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                frame = new Menu();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Menu() {
        initComponents();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        setResizable(false);
        setLocationRelativeTo(null);
        this.setContentPane(new Inicio());
    }

    public void initComponents() {
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnContatos = new JMenu("Contatos");
        menuBar.add(mnContatos);
        
        JMenuItem item_cadastrar = new JMenuItem("Cadastrar");
        item_cadastrar.addActionListener(e -> abrirTelaCadastro());
        mnContatos.add(item_cadastrar);
        
        JMenuItem item_consultar = new JMenuItem("Consultar");
        item_consultar.addActionListener(e -> abrirTelaConsulta());
        mnContatos.add(item_consultar);
        
        JMenuItem item_edt_rmv = new JMenuItem("Editar ou remover");
        item_edt_rmv.addActionListener(e -> abrirTelaEdtRmv());
        mnContatos.add(item_edt_rmv);
        
        JMenu mnSobre = new JMenu("Sobre");
        menuBar.add(mnSobre);
        
        JMenuItem item_desenvolvedor = new JMenuItem("Desenvolvedor");
        item_desenvolvedor.addActionListener(e -> 
            JOptionPane.showMessageDialog(frame, "Desenvolvedor: Mauricio Carvalho Cogo\nTurma: ADS 19\nInstituto Federal Farroupilha - Campus S�o Vicente do Sul"));
        mnSobre.add(item_desenvolvedor);
        
        JMenuItem item_software = new JMenuItem("Programa");
        item_software.addActionListener(e -> 
            JOptionPane.showMessageDialog(frame, "Agenda do MauricioCogo\nVers�o: 1.1"));
        mnSobre.add(item_software);
    }

    public void abrirTelaCadastro() {
        this.setContentPane(new Tela_cadastro());
        this.revalidate();
        this.repaint();
    }

    public void abrirTelaConsulta() {
        this.setContentPane(new Tela_consulta());
        this.revalidate();
        this.repaint();
    }

    public void abrirTelaEdtRmv() {
        this.setContentPane(new Tela_alteracaoRemocao());
        this.revalidate();
        this.repaint();
    }
}
