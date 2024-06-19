package trabalhoPratico1;

public class ListaEncadeada {

    private Elemento inicio;

    public void adicionar(Conta c) {
        if (this.inicio == null) {
            this.inicio = new Elemento(c);
        } else {
            Elemento e = this.inicio;
            while (e.getProximo() != null) {
                e = e.getProximo();
            }
            e.setProximo(new Elemento(c));
        }
    }

    public void listar() {
        if (this.inicio == null) {
            System.out.println("Lista vazia.");
        }
        try {
            Elemento e = this.inicio;
            System.out.println(e.getConta());
            while (e.getProximo() != null) {
                e = e.getProximo();
                System.out.println(e.toString());
            }
        } catch (Exception e) {
        }
    }

    public void remover(String nome) {
        Elemento e = this.inicio;
        if (e.getConta().nome.equals(nome)) {
            this.inicio = e.getProximo();
            System.out.println("Removido com sucesso!");
        } else {
            while (e.getProximo() != null) {
                if (e.getProximo().getConta().nome.equals(nome)) {
                    e.setProximo(e.getProximo().getProximo());
                } else {
                    e = e.getProximo();
                    System.out.println("Removido com sucesso!");
                }
            }
        }
    }

    public boolean alterar(String nome, String situacao) {
        Elemento e = this.inicio;
        if (e.getConta().nome.equals(nome)) {
            e.getConta().setSituacao(situacao);
            return true;
        } else {
            while (e.getProximo() != null) {
                if (e.getProximo().getConta().nome.equals(nome)) {
                    e.getProximo().getConta().setSituacao(situacao);
                    return true;
                } else {
                    e = e.getProximo();
                }

            }
        }
        return false;
    }

    public Elemento getInicio() {
        return this.inicio;
    }
}
