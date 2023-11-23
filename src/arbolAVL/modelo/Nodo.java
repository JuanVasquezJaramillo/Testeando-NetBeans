package arbolAVL.modelo;

/**
 * Representa un nodo que contiene tres campos: valor, izquierdo, derecho y el
 * factor de equilibrio
 *
 * @author Maria Alejandra Solarte Astaiza
 * @author Juan Pablo Vasquez Jaramillo
 */
public class Nodo {

    private Comparable valor;
    private Nodo izquierdo;
    private Nodo derecho;
    private int fe;//factor equilibrio

    public Nodo() {
        valor = null;
        izquierdo = null;
        derecho = null;
        fe = 0;
    }

    public Nodo(Comparable valor) {
        this.valor = valor;
        izquierdo = null;
        derecho = null;
        fe = 0;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public Comparable getValor() {
        return valor;
    }

    public void setValor(Comparable valor) {
        this.valor = valor;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
