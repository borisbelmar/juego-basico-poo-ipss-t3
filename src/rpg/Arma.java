package rpg;

public class Arma extends Objeto {
    private int danio;

    public Arma(String nombre, int peso, int valor, int danio) {
        super(nombre, peso, valor);
        this.danio = danio;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }
}
