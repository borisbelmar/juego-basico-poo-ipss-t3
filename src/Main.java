import rpg.Arma;
import rpg.Consumible;
import rpg.Personaje;

public class Main {
    public static void main(String[] args) {
        Personaje mario = new Personaje("Mario", "Rojo");
        Personaje luigi = new Personaje("Luigi", "Verde");

        mario.imprimirEstado();
        luigi.imprimirEstado();

        mario.obtenerMonedas(10);
        mario.obtenerMonedas(100);
        mario.perderMonedas(200);

        mario.imprimirEstado();

        mario.obtenerMonedas(100);

        mario.imprimirEstado();

        Arma espada = new Arma("Espada", 5, 100, 10);
        Arma arco = new Arma("Arco", 2, 200, 20);

        mario.setArma(0, espada);
        mario.setArma(1, arco);

        System.out.println("====== ARMAS ========");
        mario.imprimirEstado();
        mario.imprimirArmas();
        mario.cambiarArma();
        mario.imprimirArmas();

        System.out.println("===== MOCHILA ======");
        Consumible pocion = new Consumible("Poción de Vida", 1, 50, 20);
        Consumible carne = new Consumible("Carne", 2, 30, 10);

        mario.lootearObjeto(pocion);
        mario.lootearObjeto(carne);

        mario.imprimirInventario();

        Arma mandoble = new Arma("Mandoble", 7, 300, 30);

        mario.lootearObjeto(mandoble);
        mario.imprimirInventario();
    }
}
