# Juego Básico POO - IPSS T3

Proyecto Java que simula un mini juego de rol (RPG) para practicar los fundamentos de la **Programación Orientada a Objetos (POO)**: un `Personaje` que gana/pierde vidas y monedas, porta un arreglo fijo de armas y recolecta objetos en una mochila de capacidad limitada.

## Estructura del proyecto

```
src/
├── Main.java            # Punto de entrada, crea personajes y prueba las funcionalidades
└── rpg/
    ├── Objeto.java       # Clase abstracta base (nombre, peso, valor)
    ├── Arma.java         # Objeto con daño (hereda de Objeto)
    ├── Consumible.java   # Objeto con efecto (hereda de Objeto)
    └── Personaje.java    # Entidad principal: vidas, monedas, armas[], mochila ArrayList<Objeto>
```

## Cómo ejecutar

```bash
cd src
javac Main.java rpg/*.java
java Main
```

---

## Bases de la POO

La Programación Orientada a Objetos modela el problema como **objetos** que combinan estado (atributos) y comportamiento (métodos). Los cuatro pilares, todos presentes en este proyecto:

### 1. Encapsulamiento

Los atributos son `private` y solo se acceden mediante getters/setters, protegiendo la coherencia del estado interno.

```java
// Personaje.java
private int monedas;

public int getMonedas() {
    return monedas;
}

public void perderMonedas(int monedas) {
    if (this.monedas >= monedas) {
        this.monedas -= monedas;
    } else {
        this.monedas = 0; // nunca queda en negativo
    }
}
```

### 2. Abstracción

`Objeto` es una clase **abstracta**: define lo común a todo ítem (nombre, peso, valor) pero no se puede instanciar directamente, porque no representa un ítem concreto del juego.

```java
// Objeto.java
public abstract class Objeto {
    private String nombre;
    private int peso;
    private int valor;
    // ...
}
```

### 3. Herencia

`Arma` y `Consumible` **extienden** `Objeto`, reutilizando sus atributos/métodos y agregando los propios (`danio`, `efecto`).

```java
// Arma.java
public class Arma extends Objeto {
    private int danio;

    public Arma(String nombre, int peso, int valor, int danio) {
        super(nombre, peso, valor); // llama al constructor de Objeto
        this.danio = danio;
    }
}
```

### 4. Polimorfismo

`Personaje.mochila` es una colección de `Objeto`, pero puede contener tanto `Arma` como `Consumible`. El mismo código (`lootearObjeto`, `imprimirInventario`) trata a cualquier subtipo de manera uniforme sin saber de qué clase concreta es.

```java
// Main.java
mario.lootearObjeto(new Consumible("Poción de Vida", 1, 50, 20));
mario.lootearObjeto(new Arma("Mandoble", 7, 300, 30));
```

---

## Tipos de datos en Java

Java distingue entre **tipos primitivos** y **tipos de referencia**.

| Tipo primitivo | Uso en el proyecto |
|---|---|
| `int` | `vidas`, `monedas`, `peso`, `valor`, `danio`, `efecto` |
| `boolean` | Resultado implícito de comparaciones (`if (this.vidas > 0)`) |

Un primitivo almacena el valor directamente y se compara con `==`.

```java
private int vidas;   // valor numérico, no un objeto
private int monedas;
```

| Tipo de referencia | Uso en el proyecto |
|---|---|
| `String` | `nombre`, `color` |
| `Arma[]` | arreglo de armas del personaje |
| `ArrayList<Objeto>` | mochila de inventario |
| `Objeto`, `Arma`, `Consumible` | objetos del juego |

Un tipo de referencia almacena una **dirección** al objeto en memoria; variables del mismo tipo pueden apuntar al mismo objeto o a `null`.

```java
private String nombre;   // referencia a un objeto String
private Arma[] armas;    // referencia a un arreglo
```

También se usan constantes con `final`, que no pueden reasignarse tras la inicialización:

```java
final private int MAX_ARMAS = 2;
final private int MAX_PESO_MOCHILA = 10;
```

---

## Arrays (arreglos)

Un **array** tiene **tamaño fijo**, definido al crearlo, y todos sus elementos son del mismo tipo. `Personaje` usa un array para las armas porque el personaje solo puede portar un número **exacto y limitado** (2):

```java
// Personaje.java
private Arma[] armas;

public Personaje(String nombre, String color) {
    this.armas = new Arma[MAX_ARMAS]; // tamaño fijo = 2, no puede crecer
}

public void setArma(int index, Arma arma) {
    if (index < 0 || index > this.armas.length - 1) {
        System.out.println("El índice de arma seleccionado no es válido");
    }
    this.armas[index] = arma; // acceso por índice
}
```

Recorrido típico con `.length` e índice:

```java
for (int i = 0; i < this.armas.length; i++) {
    if (this.armas[i] != null) {
        System.out.println("Arma " + (i + 1) + ": " + this.armas[i].getNombre());
    }
}
```

Características clave:
- Tamaño **inmutable** una vez creado (`new Arma[MAX_ARMAS]`).
- Posiciones sin asignar quedan en `null` (para tipos de referencia) y deben chequearse.
- Acceso O(1) por índice vía `.length` y `[]`.

---

## ArrayList

`ArrayList<E>` es una lista **dinámica** (crece y encoge sola) de la API de colecciones (`java.util`). `Personaje` la usa para la mochila porque el número de ítems recolectados es variable, solo acotado por un **peso** máximo, no por una cantidad fija:

```java
// Personaje.java
import java.util.ArrayList;

ArrayList<Objeto> mochila;

public Personaje(String nombre, String color) {
    this.mochila = new ArrayList<>(); // sin tamaño fijo
}

public void lootearObjeto(Objeto objeto) {
    if (getPesoTotalMochila() + objeto.getPeso() > MAX_PESO_MOCHILA) {
        System.out.println("No se puede lootear el objeto " + objeto.getNombre() + ". La mochila está llena.");
        return;
    }
    this.mochila.add(objeto); // crece dinámicamente
}
```

Recorrido con `for-each` y métodos propios de la colección (`isEmpty()`):

```java
public void imprimirInventario() {
    if (mochila.isEmpty()) {
        System.out.println("La mochila está vacía.");
    } else {
        for (Objeto objeto : mochila) {
            System.out.println("Objeto: " + objeto.getNombre() + ", Peso: " + objeto.getPeso());
        }
    }
}
```

Características clave:
- Tamaño **dinámico**: `add()`/`remove()` ajustan la capacidad automáticamente.
- Solo admite tipos de referencia (`ArrayList<Objeto>`, no `ArrayList<int>`).
- Trae métodos listos: `add`, `isEmpty`, `size`, `get`, `remove`, etc.

### Array vs ArrayList

| Aspecto | `Array` (`Arma[]`) | `ArrayList` (`ArrayList<Objeto>`) |
|---|---|---|
| Tamaño | Fijo al crearse | Dinámico |
| Tipos admitidos | Primitivos y referencias | Solo referencias (usa *wrappers* para primitivos) |
| Uso en el proyecto | Armas del personaje (máx. 2, slots fijos) | Mochila (cantidad variable, limitada por peso) |
| Métodos útiles | `.length`, `[]` | `.add()`, `.isEmpty()`, `.size()`, `.get()` |
