import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Arquivo {
	private BufferedReader br;
	private BufferedWriter bw;
	private String arquivo;
	
	public Arquivo(String arquivo){
		this.arquivo = arquivo;
		try {
			br = new BufferedReader(new FileReader(this.arquivo + ".txt"));
			br.close();
		} catch (IOException e) {
			try {
				bw = new BufferedWriter(new FileWriter(this.arquivo + ".txt"));
				bw.close();
			} catch (IOException e1) {}
		}
	}
	
	public void gravar(Contato contato){
		try{			
			bw = new BufferedWriter(new FileWriter(this.arquivo + ".txt"));
			bw.write(contato.getNome()+"\n");
			bw.write(contato.getApelido()+"\n");
			bw.write(contato.getEmail()+"\n");
			bw.write(contato.getNumero()+"\n");					
			bw.close();					
		}catch (Exception e) {
			System.out.println("Erro na abertura de arquivo para gravação.");
		}
	}
	

	public Contato ler(){
		String nome = "";
		String apelido = "";
		String email = "";
		String numero = "";
		String conteudo = "";
		try{
			br = new BufferedReader(new FileReader(this.arquivo + ".txt"));
			while((nome = br.readLine()) != null){
				apelido = br.readLine();
				numero = br.readLine();
				email = br.readLine();
				conteudo 
			}
			br.close();
		}catch (Exception e) {
			System.out.println("Erro na abertura de arquivo para leitura.");
		}
		return contas;
	}
}
