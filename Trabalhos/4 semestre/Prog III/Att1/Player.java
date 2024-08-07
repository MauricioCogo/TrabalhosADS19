public class Player {
    private String name;
    private String symbol;
    
    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        String n = name.toUpperCase();
        if (n.equals("IA")) {
            System.out.println(n);
            this.name = n;
        }
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getRandomNum(){
        int num = ((int)Math.random())*3;
        return  num;
    }

    public String toString() {
        return "Nome: "+this.name+"\nSimbolo: "+this.symbol+"\n";
    }
}
