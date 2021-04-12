/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolBinario;

/**
 *
 * @author Carla Nuñez
 */
public class NodoArbol {

    private Object elemento;
    private NodoArbol izquierdo, derecho;

    //constructor
    public NodoArbol() {
        this.elemento = Integer.MAX_VALUE;
        this.izquierdo = null;
        this.derecho = null;

    }

    public NodoArbol(Object elemNuevo) {
        this.elemento = elemNuevo;
        this.izquierdo = null;
        this.derecho = null;

    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public NodoArbol getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoArbol izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoArbol getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoArbol derecho) {
        this.derecho = derecho;
    }


}
