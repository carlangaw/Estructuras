/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

 */
package ArbolGen;

/**
 *
 * @author Carla Nuñez
 */
public class NodoGen {

    private Object elemento;
    private NodoGen hijoExtremoIzquierdo;
    private NodoGen hermanoDerecho;

    public NodoGen(Object elemento, NodoGen hijoExtremoIzquierdo, NodoGen hermanoDerecho) {
        this.elemento = elemento;
        this.hijoExtremoIzquierdo = hijoExtremoIzquierdo;;
        this.hermanoDerecho = hermanoDerecho;
    }

    public NodoGen(Object elemento) {
        this.elemento = elemento;
    }

    public NodoGen getHermanoDerecho() {
        return hermanoDerecho;
    }

    public NodoGen getHijoExtremoIzquierdo() {
        return hijoExtremoIzquierdo;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }

    public void setHermanoDerecho(NodoGen hermanoDerecho) {
        this.hermanoDerecho = hermanoDerecho;
    }

    public void setHijoExtremoIzquierdo(NodoGen hijoExtremoIzquierdo) {
        this.hijoExtremoIzquierdo = hijoExtremoIzquierdo;
    }

    public boolean tengoHijo(Object elemento) {
        boolean respuesta = false;

        NodoGen hijo = this.hijoExtremoIzquierdo;

        while (hijo != null && !respuesta) {
            if (elemento.equals(hijo.getElemento())) {
                // encontre el elemento
                respuesta = true;
            } else {
                hijo = hijo.getHermanoDerecho();
            }
        }

        return respuesta;
    }

}
