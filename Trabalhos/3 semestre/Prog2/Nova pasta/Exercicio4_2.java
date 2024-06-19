package Aula08_03;

import java.util.Scanner;

public class Exercicio4_2 {

    Scanner e = new Scanner(System.in);

    String nome;
    int idade;
    String[] consulta = new String[10];
    int cont = 1;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setCons() {
        if (cont >= 10) {
            System.out.println("Atingiu o limite de consultas.");
        } else {
            consulta[cont] = e.nextLine();
            cont++;
        }
    }

    public void getCons() {
        for (int i = 1; i < 10; i++) {
            if (consulta[i] != null) {
                System.out.println(i + " - " + consulta[i]);
            }
        }
    }

}
