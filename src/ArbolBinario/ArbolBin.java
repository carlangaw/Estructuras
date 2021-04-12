/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolBinario;

import lista.Lista;

/**
 *
 * @author Carla Nuñez
 */
//atributos
public class ArbolBin {

    private NodoArbol raiz;

    //constructor
    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char posicion) {
        boolean respuesta = true;
        if (this.raiz == null) {
            //si la raiz del arbol esta vacia, entonces inserto el elementoNuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            //si no esta vacio busca al padre
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                if (posicion == 'I' && nodoPadre.getIzquierdo() == null) {
                    //si el padre existe y no tiene HI lo agrega
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                } else if (posicion == 'D' && nodoPadre.getDerecho() == null) {
                    nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                } else {
                    respuesta = false;
                }

            } else {
                respuesta = false;
            }
        }
        return respuesta;
    }

    public boolean esVacio() {
        boolean respuesta = false;
        if (raiz == null) {
            respuesta = true;
        }
        return respuesta;
    }

    public void preOrdenAux(NodoArbol nodo, int pos, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElemento(), pos);
            preOrdenAux(nodo.getIzquierdo(), pos + 1, lista);
            preOrdenAux(nodo.getDerecho(), lista.longitud() + 1, lista);

        }
    }

    public Lista listarPreorden() {
        Lista lista = new Lista();
        preOrdenAux(this.raiz, 1, lista);
        return lista;
    }

    public Object padre(Object hijo) {
        Object esPadre;
        NodoArbol padre = padreRecursivo(hijo, this.raiz);
        if (padre == null) {
            esPadre = null;
        } else {
            esPadre = padreRecursivo(hijo, this.raiz).getElemento();
        }
        return esPadre;
    }

    private NodoArbol padreRecursivo(Object elem, NodoArbol n) {
        NodoArbol retorno = null;
        if (n != null) {
            if (n.getElemento().equals(elem)) {
                retorno = null;
            } else {
                if (n.getDerecho() != null) {
                    if (n.getDerecho().getElemento().equals(elem)) {
                        retorno = n;

                    } else {
                        retorno = padreRecursivo(elem, n.getDerecho());
                    }

                }
            
            if (n.getIzquierdo() != null) {
                if (n.getIzquierdo().getElemento().equals(elem)) {
                    retorno = n;
                }

            }

            if (retorno == null) {
                retorno = padreRecursivo(elem, n.getIzquierdo());
            }
        }
        }
        
        return retorno;
    }

    public Lista listarPosorden() {
        Lista lista2 = new Lista();
        posOrdenAux(this.raiz, lista2);
        return lista2;
    }

    public Lista listarInorden() {
        Lista lista3 = new Lista();
        inOrdenAux(this.raiz, lista3);
        return lista3;

    }

    public void inOrdenAux(NodoArbol nodo, Lista lista3) {
        if (nodo != null) {
            inOrdenAux(nodo.getIzquierdo(), lista3);
            lista3.insertar(nodo.getElemento(), lista3.longitud() + 1);
            inOrdenAux(nodo.getDerecho(), lista3);
        }
    }

    public void posOrdenAux(NodoArbol nodo, Lista lista2) {
        if (nodo != null) {
            posOrdenAux(nodo.getIzquierdo(), lista2);
            posOrdenAux(nodo.getDerecho(), lista2);
            lista2.insertar(nodo.getElemento(), lista2.longitud() + 1);
        }

    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        //metodo privado que busca un elemento y devuelv un nodo
        NodoArbol resultado = null;
        if (n != null) {

            if (n.getElemento().equals(buscado)) {
                //si el buscado es n,lo devuelve
                resultado = n;
            } else {
                //no es el buscado: busca el primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                //si no encuentra el HI, busca el HD  

                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public void vaciar() {
        this.raiz = null;
    }

    private int alturaAux(NodoArbol n) {
        int cont = -1;
        if (n != null) {
            int izq = alturaAux(n.getIzquierdo());
            int derecho = alturaAux(n.getDerecho());
            cont = Math.max(izq, derecho) + 1;
        }

        return cont;
    }

    public int altura() {
        int alt;
        alt = alturaAux(this.raiz);
        return alt;
    }

    private int nivelAux(NodoArbol nodo, int elemento) {
        int nivel;
        if (nodo == null) {
            nivel = Integer.MIN_VALUE;
        } else if (nodo.getElemento().equals(elemento)) {
            nivel = 0;
        } else {
            nivel = nivelAux(nodo.getIzquierdo(), elemento);
            if (nivel == Integer.MIN_VALUE) {
                nivel = nivelAux(nodo.getDerecho(), elemento);
            }

        }
        if (nivel != Integer.MIN_VALUE) {
            nivel++;
        }
        return nivel;
    }

    public int nivel(int elemento) {
        return nivelAux(this.raiz, elemento) - 1;
    }

    @Override
    public String toString() {
        String cad;
        if (!this.esVacio()) {
            cad = "raiz: " + this.raiz.getElemento() + "\n";
            cad += "========================\n";
            cad += stringRecursivo(this.raiz) + "\n";
        } else {
            cad = "Arbol vacío";
        }
        return cad;
    }

    private String stringRecursivo(NodoArbol n) {
        String cadena = "";
        if (n != null) {
            if (n.getIzquierdo() != null) {
                cadena += "Nodo: " + n.getElemento() + " Hijo Izquierdo: " + n.getIzquierdo().getElemento();
            } else {
                cadena += "Nodo: " + n.getElemento() + " Hijo Izquierdo: vacio";
            }
            if (n.getDerecho() != null) {
                cadena += " Hijo Derecho: " + n.getDerecho().getElemento();
            } else {
                cadena += " Hijo Derecho: vacio ";
            }
            cadena += "\n";
            if (n.getIzquierdo() != null) {
                cadena += stringRecursivo(n.getIzquierdo());
            }
            if (n.getDerecho() != null) {
                cadena += stringRecursivo(n.getDerecho());
            }
        }

        return cadena;
    }

//    public boolean verificarPatron(Lista listaPatron) {
//        boolean res;
//        res = verificarRecursivo(this.raiz, listaPatron, 1);
//        return res;
//
//    }
//
//    private boolean verificarRecursivo(NodoArbolInt raiz, Lista listaPatron, int posicion) {
//        boolean aux = false;
//       
//        System.out.println(posicion + " " + listaPatron.longitud());
//        if (posicion > listaPatron.longitud()) {            
//            aux = true;
//        } else if ((raiz != null) && (raiz.getElemento())) == (listaPatron.recuperar(posicion)) {
//            
//            aux = verificarRecursivo(raiz.getIzquierdo(), listaPatron, posicion + 1);
//            if (!aux) {                
//            
//                aux = verificarRecursivo(raiz.getDerecho(), listaPatron, posicion + 1);
//            }
//        }
//        return aux;
//    }
    public Lista frontera() {
        Lista lista = new Lista();

        int pos = 1;
        fronteraRec(this.raiz, lista, pos);

        return lista;
    }

    private void fronteraRec(NodoArbol a, Lista lista2, int pos) {
        if (a != null) {
            if (a.getDerecho() == null && a.getIzquierdo() == null) {

                lista2.insertar(a.getElemento(), pos);

            } else {
                fronteraRec(a.getDerecho(), lista2, pos);
                fronteraRec(a.getIzquierdo(), lista2, pos);

            }
        }
    }

    public ArbolBin clon() {
        ArbolBin arbolRec = new ArbolBin();
        if (this.esVacio() != true) {
            arbolRec.raiz = clonRecursivo(this.raiz);
        }

        return arbolRec;
    }

    private NodoArbol clonRecursivo(NodoArbol nodo) {
        NodoArbol aux = new NodoArbol();
        aux.setElemento(nodo.getElemento());
        if (nodo.getIzquierdo() != null) {
            aux.setDerecho(clonRecursivo(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            aux.setIzquierdo(clonRecursivo(nodo.getDerecho()));
        }
        return aux;
    }

    @Override
    public ArbolBin clone() {
        ArbolBin arbolRec = new ArbolBin();
        if (this.esVacio() != true) {
            arbolRec.raiz = clonAux(this.raiz);
        }

        return arbolRec;
    }

    private NodoArbol clonAux(NodoArbol nodo) {
        NodoArbol aux = new NodoArbol();
        aux.setElemento(nodo.getElemento());
        if (nodo.getIzquierdo() != null) {
            aux.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            aux.setDerecho(clonAux(nodo.getDerecho()));
        }
        return aux;
    }

}
