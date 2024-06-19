package Trabalho;

public class Elemento {

    private Ponto ponto;

    private Elemento proximo;
    private Elemento anterior;

    public Elemento(Ponto ponto) {
        this.ponto = ponto;
    }

    public Ponto getPonto() {
        return ponto;
    }

    public void setPonto(Ponto ponto) {
        this.ponto = ponto;
    }

    public Elemento getProximo() {
        return proximo;
    }

    public void setProximo(Elemento proximo) {
        this.proximo = proximo;
    }

    public Elemento getAnterior() {
        return anterior;
    }

    public void setAnterior(Elemento anterior) {
        this.anterior = anterior;
    }

    public String toString() {
        return ponto.toString();
    }

}
