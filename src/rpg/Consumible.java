package rpg;

public class Consumible extends Objeto {
    private int efecto;

    public Consumible(String nombre, int peso, int valor, int efecto) {
        super(nombre, peso, valor);
        this.efecto = efecto;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }
}
