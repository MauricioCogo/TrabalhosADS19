package Trabalho;

public class ListaCDEncadeada {

    private Elemento inicio;
    private Elemento fim;
    private int tamanho;

    public ListaCDEncadeada() {
        this.tamanho = 0;
    }

    public int getTamanho() {
        return this.tamanho;
    }

    public void adicionar(Ponto ponto) {
        if (this.inicio == null) {
            this.inicio = new Elemento(ponto);
            this.inicio.setProximo(this.inicio);
        } else {
            Elemento elemento = this.inicio;
            while (!elemento.getProximo().getPonto().equals(this.inicio.getPonto())) {
                elemento = elemento.getProximo();
            }
            elemento.setProximo(new Elemento(ponto));
            elemento.getProximo().setProximo(this.inicio);
            elemento.getProximo().setAnterior(elemento);
        }
        this.tamanho++;
    }

    public void listar() {
        Elemento elemento = this.inicio;
        if (elemento != null) {
            System.out.println("Inicio: ");
            System.out.println(elemento);
            while (!elemento.getProximo().getPonto().equals(this.inicio.getPonto())) {
                System.out.println("");
                elemento = elemento.getProximo();
                System.out.println(elemento);
            }
            System.out.println("");
            System.out.println("Inicio: ");
            System.out.println(elemento.getProximo());
        } else {
            System.out.println("A lista esta vazia!");
        }
    }

    public Ponto listarIndice(int index) {
        if (this.inicio == null) {
            return null;
        } else {
            if (index == 0) {
                return this.inicio.getPonto();
            } else {
                Elemento e = this.inicio;
                if (index < tamanho) {
                    for (int i = 1; i < index + 1; i++) {
                        e = e.getProximo();
                    }
                    return e.getPonto();
                } else {
                    return null;
                }
            }
        }
    }

    public void listarVizinhos(int x, int y, int z) {
        if (this.inicio == null) {
            System.out.println("Invalido!");;
        } else {
            Elemento e = this.inicio;
            while (!e.getPonto().equals(x, y, z)) {                
                e = e.getProximo();
            }
                System.out.println("Esquerda: ");
                System.out.println(e.getAnterior().getPonto());
                System.out.println("Ponto: ");
                System.out.println(e.getPonto());
                System.out.println("Direita: ");
                System.out.println(e.getProximo().getPonto());            
        }
    }
}
