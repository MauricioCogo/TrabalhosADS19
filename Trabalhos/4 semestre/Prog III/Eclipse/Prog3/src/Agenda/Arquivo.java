package Agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

	public void gravar(Contato contato) {
		try {
			bw = new BufferedWriter(new FileWriter(this.arquivo + ".txt", true));

			bw.write(contato.getNome() + "," + contato.getApelido() + "," + contato.getNumero() + ","
					+ contato.getEmail());
			bw.write("\n");

			bw.close();
		} catch (Exception e) {
			System.out.println("Erro na abertura de arquivo para grava��o.");
		}
	}

	public LinkedList<Contato> ler() {
		String nome = "";
		String[] conteudo;

		LinkedList<Contato> co = new LinkedList<>();
		try {
			br = new BufferedReader(new FileReader(this.arquivo + ".txt"));
			while ((nome = br.readLine()) != null) {

				conteudo = nome.split(",");

				Contato c = new Contato(conteudo[0], conteudo[1], conteudo[2], conteudo[3]);
				co.add(c);
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Erro na abertura de arquivo para leitura.");
		}
		return co;
	}

	public void editar(String nomevelho, Contato contato) {
		LinkedList<Contato> co = new LinkedList<>();
		String linha;

		try (BufferedReader br = new BufferedReader(new FileReader(this.arquivo + ".txt"))) {
			while ((linha = br.readLine()) != null) {
				String[] conteudo = linha.split(",");
				Contato c = new Contato(conteudo[0], conteudo[1], conteudo[2], conteudo[3]);
				if (conteudo[0].equals(nomevelho)) {
					c = contato;
					System.out.println(c.getNome());
				}

				co.add(c);
			}
		} catch (IOException e) {
			System.out.println("Erro na abertura de arquivo para leitura: " + e.getMessage());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.arquivo + ".txt"))) {
			for (Contato con : co) {
				bw.write(con.getNome() + "," + con.getApelido() + "," + con.getNumero() + "," + con.getEmail());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
		}
	}

	public void deletar(String texto) {
		LinkedList<Contato> co = new LinkedList<>();
		String linha;

		try (BufferedReader br = new BufferedReader(new FileReader(this.arquivo + ".txt"))) {
			while ((linha = br.readLine()) != null) {
				String[] conteudo = linha.split(",");
				Contato c = new Contato(conteudo[0], conteudo[1], conteudo[2], conteudo[3]);
				if (!conteudo[0].equals(texto)) {
					co.add(c);
				} else {
					System.out.println("Contato removido: " + conteudo[0]);
				}
			}
		} catch (IOException e) {
			System.out.println("Erro na abertura de arquivo para leitura: " + e.getMessage());
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.arquivo + ".txt"))) {
			for (Contato contato : co) {
				bw.write(contato.getNome() + "," + contato.getApelido() + "," + contato.getNumero() + ","
						+ contato.getEmail());
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
		}
	}
}
