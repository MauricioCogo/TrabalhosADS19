package trabalhoPratico1;


public class Conta {
    
    String nome;
    String valor;
    String situacao;
    
    public Conta(){
        
    }

    public Conta(String nome, String valor, String situacao) {
        this.nome = nome;
        this.valor = valor;
        this.situacao = situacao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
    
    public String toString(){
        return "\nNome: "+nome+"\nValor: "+valor+"\nSituação: "+situacao;
    }
}
