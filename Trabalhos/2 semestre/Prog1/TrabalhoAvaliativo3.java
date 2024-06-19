package provamaurico;

import java.util.Scanner;

public class Provamaurico {

    public static int chave;
    public static String msg;
    public static String[] alfa = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    
    
    //exemplo: YLYHUHGHVHQKDUVHPERUUDFKD


    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Insira uma chave, chaves positivas codificam e chaves negativas descodificam: ");
        chave = entrada.nextInt();

        boolean teste = testarChave(chave);

        if (teste == true) {

            System.out.println("Insira uma mensagem: ");
            msg = entrada.next();
            System.out.println("");
            msg = msg.toUpperCase();

            String[] vetor = new String[msg.length()];

            armazMsgVetor(msg, vetor);

            System.out.println("Mensagem (des)criptografada: ");
            for (int i = 0; i < msg.length(); i++) {
                decifraMsg(vetor[i], chave);
            }
            System.out.println("");

        } else {
            System.out.println("Chave invalida");
        }
    }

    public static boolean testarChave(int num) {
        if ((num >= -25) && (num <= 25)) {
            return true;
        } else {
            return false;
        }
    }

    public static void armazMsgVetor(String mensagem, String veto[]) {
        for (int i = 0; i < mensagem.length(); i++) {
            veto[i] = String.valueOf(mensagem.charAt(i));
        }
    }

    public static void decifraMsg(String vet, int chave) {

        for (int i = 0; i < 26; i++) {

            if (vet.equals(alfa[i])) {
                int teste = i + chave;
                if (teste >= 25) {
                    teste = teste - 26;
                }
                if (teste < 0) {
                    teste = teste + 26;
                }
                imprimeMsgDecifrada(teste);
            }

        }
    }

    public static void imprimeMsgDecifrada(int chave) {
        System.out.print(alfa[chave]);
    }
}
