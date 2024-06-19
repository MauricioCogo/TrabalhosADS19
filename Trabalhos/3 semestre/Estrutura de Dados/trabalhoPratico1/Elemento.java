package trabalhoPratico1;

public class Elemento {
 
    private Conta conta;
    private Elemento proximo;
    
    public Elemento(Conta conta){
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }
    
    
    public Elemento getProximo() {
        return proximo;
    }
    
    public void setProximo(Elemento e){
        this.proximo = e;
    }
    
    public String toString(){
        return conta.toString();
    }
}
