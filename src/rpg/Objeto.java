package rpg;

public abstract class Objeto {
    private String nombre;
    private int peso;
    private int valor;

    public Objeto(String nombre, int peso, int valor) {
        this.nombre = nombre;
        this.valor = valor;
        this.peso = peso;
    }

    public Objeto(String nombre, int peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
