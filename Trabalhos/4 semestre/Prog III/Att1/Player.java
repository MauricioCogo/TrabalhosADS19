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
        this.symbol = symbol;
    }

    public void setIA(boolean ia){
        this.ia = ia;
    }


    public String toString() {
        return "Nome: "+this.name+"\nSimbolo: "+this.symbol+"\n";
    }
}
