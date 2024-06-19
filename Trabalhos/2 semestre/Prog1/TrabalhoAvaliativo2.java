package prova;

import java.util.Scanner;

public class Exercicio2 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Digite uma string: ");

        String frase = entrada.nextLine();
        String maiu = frase.toUpperCase();
        String esp = maiu.replace(" ", "");
        String pont = esp.replace(".", "");
        String sem = pont.replace(",", "");

        int tam = sem.length();
        int aa = 0;
        int ee = 0;
        int ii = 0;
        int oo = 0;
        int uu = 0;
        int[] vogal = new int[5];
        int maior = 0;

        char[] vetor = new char[tam];
        char letra;

        for (int j = 0; j < tam; j++) {
            letra = sem.charAt(j);
            vetor[j] = letra;
            if (letra == 'A') {
                vogal[0] = aa++;
            }
            if (letra == 'E') {
                vogal[1] = ee++;
            }
            if (letra == 'I') {
                vogal[2] = ii++;
            }
            if (letra == 'O') {
                vogal[3] = oo++;
            }
            if (letra == 'U') {
                vogal[4] = uu++;
            }
        }
        for (int i = 0; i < tam; i++) {
            System.out.println(vetor[i]);
        }
        System.out.println("Maiusculo: " + maiu);
        System.out.println("Sem espaços: " + sem);
        System.out.println("Tamanho: " + tam);
        
        if (aa > ee) {
            if (aa > ii) {
                if (aa > oo) {
                    if (aa > uu) {
                        System.out.println("A letra A aparece mais vezes: " +aa);
                    }
                    else{
                        System.out.println("A letra U aparece mais vezes: " +uu);
                    }
                } else {
                    if (oo > uu) {
                        System.out.println("A letra O aparece mais vezes: " +oo);
                    }
                    else{
                        System.out.println("A letra U aparece mais vezes: " +uu);
                    }
                }
            }else{
                if (ii>oo) {
                    if (ii>uu) {
                        System.out.println("A letra I aparece mais vezes: " +ii);
                    }else{
                        System.out.println("A letra U aparece mais vezes: " +uu);
                    }
                }
            }
        }else {
            if (ee > ii) {
                if (ee > oo) {
                    if (ee > uu) {
                        System.out.println("A letra E aparece mais vezes: " +ee);
                    }else{
                        System.out.println("A letra U aparece mais vezes: " +uu);
                    }
                }else{
                    if (oo>uu) {
                        System.out.println("A letra O aparece mais vezes: " +oo);
                    }else{
                        System.out.println("A letra U aparece mais vezes: " +uu);
                    }
                }
            } else {
                if (ii > oo) {
                    if (ii > uu) {
                        System.out.println("A letra I aparece mais vezes: " +ii);
                    }else{
                        System.out.println("A letra U aparece mais vezes: " +uu);
                    }
                }else{
                    if (oo>uu) {
                        System.out.println("A letra O aparece mais vezes: " +oo);
                    }else{
                        System.out.println("A letra U aparece mais vezes: " +uu);
                    }
                }
            }
        }
    }
}
