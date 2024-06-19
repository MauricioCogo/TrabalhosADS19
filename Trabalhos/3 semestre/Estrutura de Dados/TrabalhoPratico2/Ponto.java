package Trabalho;

public class Ponto {

    private int x;
    private int y;
    private int z;

    public Ponto() {
    }

    public Ponto(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String toString() {
        return "Ponto X: " + this.x + "\nPonto Y: " + this.y + "\nPonto Z: " + this.z;
    }

    public boolean equals(int x, int y, int z) {
        if ((this.x == x) && (this.y == y) && (this.z == z)) {
            return true;
        }
        return false;
    }
}
