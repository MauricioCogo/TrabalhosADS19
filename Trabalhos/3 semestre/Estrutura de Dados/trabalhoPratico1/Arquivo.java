package trabalhoPratico1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {

    private BufferedReader br;
    private BufferedWriter bw;
    private String arquivo;

    public Arquivo(String arquivo) {
        this.arquivo = arquivo;
        try {
            br = new BufferedReader(new FileReader(this.arquivo + ".txt"));
            br.close();
        } catch (IOException e) {
            try {
                bw = new BufferedWriter(new FileWriter(this.arquivo + ".txt"));
                bw.close();
            } catch (IOException e1) {
            }
        }
    }

    public void gravar(ListaEncadeada contas) {
        try {
            bw = new BufferedWriter(new FileWriter(this.arquivo + ".txt"));
            Elemento elemento = contas.getInicio();
            bw.write(elemento.getConta().getNome() + "\n");
            bw.write(elemento.getConta().getValor() + "\n");
            bw.write(elemento.getConta().getSituacao() + "\n");
            while (elemento.getProximo() != null) {
                elemento = elemento.getProximo();
                bw.write(elemento.getConta().getNome() + "\n");
                bw.write(elemento.getConta().getValor() + "\n");
                bw.write(elemento.getConta().getSituacao() + "\n");
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Erro na abertura de arquivo para grava��o.");
        }
    }

    public ListaEncadeada ler() {
        ListaEncadeada contas = new ListaEncadeada();
        String nome = "";
        String valor = "";
        String situacao = "";
        try {
            br = new BufferedReader(new FileReader(this.arquivo + ".txt"));
            while ((nome = br.readLine()) != null) {
                valor = br.readLine();
                situacao = br.readLine();
                Conta c = new Conta(nome, valor, situacao);
                contas.adicionar(c);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro na abertura de arquivo para leitura.");
        }
        return contas;
    }
}
