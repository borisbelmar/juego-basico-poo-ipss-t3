package rpg;

import java.util.ArrayList;

public class Personaje {
    final private int MAX_ARMAS = 2;
    final private int MAX_PESO_MOCHILA = 10;
    private String nombre;
    private String color;
    private int vidas;
    private int monedas;
    private Arma[] armas;
    private int armaSeleccionada;
    ArrayList<Objeto> mochila;

    public Personaje(String nombre, String color) {
        this.nombre = nombre;
        this.color = color;
        this.vidas = 3;
        this.monedas = 0;
        this.armas = new Arma[MAX_ARMAS];
        this.armaSeleccionada = 0;
        this.mochila = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getVidas() {
        return vidas;
    }

    public void ganarVida() {
        this.vidas += 1;
    }

    public void perderVida() {
        if (this.vidas > 0) {
            this.vidas -= 1;
        }
        if (this.vidas == 0) {
            System.out.println("El personaje " + this.nombre + " no tiene más vidas. Game Over");
        }
    }

    public int getMonedas() {
        return monedas;
    }

    public void obtenerMonedas(int monedas) {
        this.monedas += monedas;
    }

    public void perderMonedas(int monedas) {
        if (this.monedas >= monedas) {
            this.monedas -= monedas;
        } else {
            this.monedas = 0;
        }
    }

    public Arma getArmaSeleccionada() {
        return this.armas[armaSeleccionada];
    }

    public void setArma(int index, Arma arma) {
        if (index < 0 || index > this.armas.length -1) {
            System.out.println("El índice de arma seleccionado no es válido");
        }
        this.armas[index] = arma;
    }

    public void cambiarArma() {
        if (this.armaSeleccionada == 0) {
            this.armaSeleccionada = 1;
        } else {
            this.armaSeleccionada = 0;
        }
    }

    public void imprimirEstado() {
        System.out.println("========");
        System.out.println("Nombre: " + this.nombre + ", Color: " + this.color + ", Vidas: " + this.vidas + ", Monedas: " + this.monedas);
        System.out.println("========");
    }

    public void imprimirArmas() {
        System.out.println("========");
        System.out.println("Arma seleccionada: " + (this.armas[armaSeleccionada] != null ? this.armas[armaSeleccionada].getNombre() : "Ninguna"));
        System.out.println("Armas de " + this.nombre + ":");
        for (int i = 0; i < this.armas.length; i++) {
            if (this.armas[i] != null) {
                System.out.println("Arma " + (i + 1) + ": " + this.armas[i].getNombre() + ", Daño: " + this.armas[i].getDanio() + ", Peso: " + this.armas[i].getPeso() + ", Valor: " + this.armas[i].getValor());
            } else {
                System.out.println("Arma " + (i + 1) + ": Vacía");
            }
        }
    }

    public int getPesoTotalMochila() {
        int pesoTotal =0;
        for (Objeto objeto : mochila) {
            pesoTotal += objeto.getPeso();
        }
        return pesoTotal;
    }

    public void lootearObjeto(Objeto objeto) {
        if (getPesoTotalMochila() + objeto.getPeso() > MAX_PESO_MOCHILA) {
            System.out.println("No se puede lootear el objeto " + objeto.getNombre() + ". La mochila está llena.");
            return;
        }
        this.mochila.add(objeto);
    }

    public void imprimirInventario() {
        System.out.println("========");
        System.out.println("Inventario de " + this.nombre + ":");
        if (mochila.isEmpty()) {
            System.out.println("La mochila está vacía.");
        } else {
            for (Objeto objeto : mochila) {
                System.out.println("Objeto: " + objeto.getNombre() + ", Peso: " + objeto.getPeso() + ", Valor: " + objeto.getValor());
            }
        }
    }
}
