/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolBB;

/**
 *
 * @author Carla Nuñez
 */
public class NodoArbolBB {
    
    private Comparable elemento;
    private NodoArbolBB izquierdo, derecho;

    //constructor
    public NodoArbolBB() {
        this.elemento = Integer.MAX_VALUE;
        this.izquierdo = null;
        this.derecho = null;

    }

    public NodoArbolBB(Comparable elemNuevo) {
        this.elemento = elemNuevo;
        this.izquierdo = null;
        this.derecho = null;

    }

    public Comparable getElemento() {
        return elemento;
    }

    public void setElemento(Comparable elemento) {
        this.elemento = elemento;
    }

    public NodoArbolBB getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbolBB izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbolBB getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbolBB derecho) {
        this.derecho = derecho;
    }

    void getIzquierdo(NodoArbolBB derecho) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
