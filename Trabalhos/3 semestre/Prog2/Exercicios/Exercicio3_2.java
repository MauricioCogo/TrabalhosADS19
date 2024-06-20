package Aula08_03;

public class Exercicio3_2 {

    private int num;
    boolean palpite = true;

    public void setNum() {
        num = (int) (Math.random() * 10000);
    }

    public int getNum() {
        return num;
    }

    public boolean getPalpite() {
        return palpite;
    }

    public void getAcerto(int i) {
        if (i > num) {
            System.out.println("é menor");
            palpite = true;
        }
        if (i < num) {
            System.out.println("é maior");
            palpite = true;
        }
        if(num==i){
            System.out.println("Acertou!");
            palpite = false;
        }
    }
}

