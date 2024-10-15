package JogodaVelha;

public class Player {
    private String name = "IA";
    private String symbol;
    private boolean ia = false;
    
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
    public boolean getIA(){
        return ia;
    }

    public void setName(String name) {
        if (name.equals("null")) {
            this.name = "IA";
        }else{
            this.name = name;
        }
    }

    public void setSymbol(String symbol) {
        symbol = symbol.toUpperCase();
        char s = symbol.charAt(0);
        this.symbol = Character.toString(s);
    }

    public void setIA(boolean ia){
        this.ia = ia;
    }

    public void getWinner() {
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("    " + this.getName() + " ganhou!");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
    
    public String toString() {
        return "Nome: "+this.name+"\nSimbolo: "+this.symbol+"\n";
    }
}
