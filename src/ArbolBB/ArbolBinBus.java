/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolBB;

import ArbolBinario.ArbolBin;
import ArbolBinario.NodoArbol;

/**
 *
 * @author Carla Nuñez
 */
public class ArbolBinBus {

    private NodoArbolBB raiz;

    //constructor
    public ArbolBinBus() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elemento) {
        boolean respuesta = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbolBB(elemento);

        } else {
            respuesta = insertarAux(this.raiz, elemento);
        }

        return respuesta;
    }

    private boolean insertarAux(NodoArbolBB n, Comparable elemento) {
        boolean exito = true;
        if (elemento.compareTo(n.getElemento()) == 0) {
            //reportar error, elemento repetido
            exito = false;
        } else if (elemento.compareTo(n.getElemento()) < 0) {
            //voy del lado izquierdo
            if (n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elemento);
            } else {
                n.setIzquierdo(new NodoArbolBB(elemento));
            }
        } else if (n.getDerecho() != null) {
            exito = insertarAux(n.getDerecho(), elemento);
        } else {
            n.setDerecho(new NodoArbolBB(elemento));
        }
        return exito;

    }

    public void vaciar() {
        //Vacia al arbol
        this.raiz = null;
    }

    public boolean eliminarMax() {
        //El max elemento siempre esta en el subarbol derecha de la raiz
        boolean exito = false;
        //CASO especial donde la raiz es el elemento max
        if (!this.esVacio()) {
            exito = true;
            if (this.raiz.getDerecho() == null) {
                if (this.raiz.getIzquierdo() != null) {
                    this.raiz = this.raiz.getIzquierdo();
                } else {
                    this.raiz = null;
                }
            } else {
                //Caso general
                eliminarMaxAux(this.raiz, null);
            }
        }
        return exito;
    }

    private void eliminarMaxAux(NodoArbolBB n, NodoArbolBB padre) {
        if (n.getDerecho() != null) {
            eliminarMaxAux(n.getDerecho(), n);

        } else if (n.getIzquierdo() != null) {
            padre.setDerecho(n.getIzquierdo());
        } else {
            padre.setDerecho(null);
        }
    }

    public boolean eliminarMin() {
        //El min elemento siempre esta en el subarbol izq de la raiz
        boolean exito = false;
        //CASO especial donde la raiz es el elemento min
        if (!this.esVacio()) {
            exito = true;
            if (this.raiz.getIzquierdo() == null) {
                if (this.raiz.getDerecho() != null) {
                    this.raiz = this.raiz.getDerecho();
                } else {
                    this.raiz = null;
                }
            } else {
                //Caso general
                eliminarMinAux(this.raiz, null);
            }
        }
        return exito;
    }

    private void eliminarMinAux(NodoArbolBB n, NodoArbolBB padre) {
        if (n.getIzquierdo() != null) {
            eliminarMinAux(n.getIzquierdo(), n);

        } else if (n.getDerecho() != null) {
            padre.setIzquierdo(n.getDerecho());
        } else {
            padre.setIzquierdo(null);
        }
    }

    public boolean pertenece(Comparable elem) {
        // Devuelve verdadero si el elemento recibido por parámetro está en el árbol y falso en caso contrario.
        boolean pertenece = perteneceAux(this.raiz, elem);

        return pertenece;
    }

    private boolean perteneceAux(NodoArbolBB n, Comparable elem) {
        boolean pertenece = false;
        if (!pertenece) {
            if (n != null) {
                if (n.getElemento().compareTo(elem) == 0) {
                    // el elem es la raiz
                    pertenece = true;
                } else if (elem.compareTo(n.getElemento()) < 0) {
                    pertenece = perteneceAux(n.getIzquierdo(), elem);
                } else {
                    pertenece = perteneceAux(n.getDerecho(), elem);
                }
            }
        }
        return pertenece;
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = false;
        if (elem.compareTo(this.raiz.getElemento()) < 0) {
            exito = eliminarAux(this.raiz.getIzquierdo(), elem, this.raiz);
        } else {
            exito = eliminarAux(this.raiz.getDerecho(), elem, this.raiz);
        }
        return exito;
    }

    private boolean eliminarAux(NodoArbolBB n, Comparable elem, NodoArbolBB padre) {
        boolean exito = false;
        if (n != null) {
            if (!exito) {
                if (elem.compareTo(n.getElemento()) == 0) {
                    // Verifico si el nodo es hoja
                    if (n.getIzquierdo() == null && n.getDerecho() == null) {
                        // el nodo es hoja
                        eliminarCasoUno(n, padre);

                        exito = true;
                        // El nodo tiene almenos un hijo
                    } else {
                        if (n.getElemento().compareTo(padre.getElemento()) > 0) {
                            /*
							 * el nodo N es mayor al padre, por lo tanto se encuentra a su derecha Verifico
							 * si el nodo tiene 1 hijo
                             */
                            eliminarCasoDos(n, padre, true);
                        } else {
                            // el nodo N es menor al padre, por lo tanto se encuentra a su izquierda
                            eliminarCasoDos(n, padre, false);
                        }
                        exito = true;

                    }
                } else if (elem.compareTo(n.getElemento()) < 0) {
                    exito = eliminarAux(n.getIzquierdo(), elem, n);
                } else {
                    exito = eliminarAux(n.getDerecho(), elem, n);
                }
            }

        }
        return exito;
    }

    private void eliminarCasoUno(NodoArbolBB n, NodoArbolBB padre) {
        if (padre.getIzquierdo() == n) {
            padre.setIzquierdo(null);
        } else {
            padre.setDerecho(null);
        }
    }

    private void eliminarCasoDos(NodoArbolBB n, NodoArbolBB padre, boolean derecha) {
        if (derecha) {
            if (n.getIzquierdo() == null && n.getDerecho() != null) {
                // el nodo N tiene un solo hijo derecho
                padre.setDerecho(n.getDerecho());
            }
            if (n.getIzquierdo() != null && n.getDerecho() == null) {
                // el nodo tiene un solo hijo izquierdo
                padre.setDerecho(n.getIzquierdo());
            }
        } else {

        }

    }

    public Lista listar() {
        //Muestra una lista con el recorrido en preOrden
        Lista listar = new Lista();
        listarAux(this.raiz, listar, 1);
        return listar;

    }

    public void listarAux(NodoArbolBB n, Lista lista, int pos) {
        // recorrido en preOrden
        if (n != null) {
            lista.insertar(n.getElemento(), pos);
            listarAux(n.getIzquierdo(), lista, pos + 1);
            listarAux(n.getDerecho(), lista, lista.longitud() + 1);

        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        // recorre parte del árbol (sólo lo necesario) y devuelve una lista ordenada con los elementos que se
        //encuentran en el intervalo [elemMinimo, elemMaximo].
        Lista lista = new Lista();
        listarRangoAux(lista, this.raiz, min, max);
        return lista;
    }

    private void listarRangoAux(Lista lista, NodoArbolBB n, Comparable min, Comparable max) {
        // listar rango de menor a mayor
        if (n != null) {

            if (max.compareTo(n.getElemento()) >= 0) {
                listarRangoAux(lista, n.getDerecho(), min, max);
            }

            if (max.compareTo(n.getElemento()) >= 0 && min.compareTo(n.getElemento()) <= 0) {
                lista.insertar(n.getElemento(), 1);
            }
            if (min.compareTo(n.getElemento()) <= 0) {
                listarRangoAux(lista, n.getIzquierdo(), min, max);
            }
        }
    }

    private Comparable minimoElemAux(NodoArbolBB n) {
        Comparable elemento = null;
        if (n != null) {
            if (n.getIzquierdo() == null) {
                elemento = n.getElemento();
            } else {
                elemento = minimoElemAux(n.getIzquierdo());
            }

        }
        return elemento;
    }

    public Comparable minElem() {
        //Devuelve el elemento minimo del arbol
        Comparable minimo;
        minimo = minimoElemAux(this.raiz);
        return minimo;
    }

    public Comparable maxElem() {
        //Devuelve el elemento maximo del arbol
        Comparable maximo;
        maximo = maxElemAux(this.raiz);
        return maximo;

    }

    private Comparable maxElemAux(NodoArbolBB n) {
        Comparable elemento = null;
        if (n != null) {
            if (n.getDerecho() == null) {
                elemento = n.getElemento();
            } else {
                elemento = maxElemAux(n.getDerecho());
            }
        }
        return elemento;
    }

    public boolean esVacio() {
        //Devuelve true si esta vacio , en caso contrario falso.
        boolean respuesta = false;
        if (this.raiz == null) {
            respuesta = true;
        }
        return respuesta;
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

    private String stringRecursivo(NodoArbolBB n) {
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

    public boolean eliminarMinimoEliminar(Comparable elemento) {
        boolean respuesta;
        respuesta = eliminarMinimoAux(this.raiz, elemento);
        return respuesta;
    }

    private boolean eliminarMinimoAux(NodoArbolBB a, Comparable elemento) {
        boolean respuesta = false;
        if (a != null) {
            if (elemento.compareTo(a.getElemento()) == 0) {

                NodoArbolBB aux = a;
                while (aux.getIzquierdo().getIzquierdo() != null) {
                    aux = aux.getIzquierdo();
                }
                if (aux.getIzquierdo().getDerecho() == null) {
                    aux.setIzquierdo(null);
                } else {
                    aux.setIzquierdo(aux.getIzquierdo().getDerecho());
                }
                respuesta = true;
            } else if (elemento.compareTo(a.getIzquierdo().getElemento()) < 0) {
                respuesta = eliminarMinimoAux(a.getIzquierdo(), elemento);
            } else {
                respuesta = eliminarMinimoAux(a.getDerecho(), elemento);
            }
        }
        return respuesta;
    }

    @Override
    public ArbolBinBus clone() {
        ArbolBinBus arbolRec = new ArbolBinBus();
        if (this.esVacio() != true) {
            arbolRec.raiz = clonAux(this.raiz);
        }

        return arbolRec;
    }

    private NodoArbolBB clonAux(NodoArbolBB nodo) {
        NodoArbolBB aux = new NodoArbolBB();
        aux.setElemento(nodo.getElemento());
        if (nodo.getIzquierdo() != null) {
            aux.setIzquierdo(clonAux(nodo.getIzquierdo()));
        }
        if (nodo.getDerecho() != null) {
            aux.setDerecho(clonAux(nodo.getDerecho()));
        }
        return aux;
    }

    public NodoArbolBB obtenerNodo(Comparable x) {
        Comparable elemento;
        NodoArbolBB nodo = this.raiz;
        NodoArbolBB resultado = null;
        while (resultado == null && nodo != null) {
            elemento = nodo.getElemento();
            if (elemento.equals(x)) {
                resultado = nodo;
            } else if (elemento.compareTo(x) < 0) {
                nodo = nodo.getDerecho();
            } else {
                nodo = nodo.getIzquierdo();
            }
        }
        return nodo;
    }

    private NodoArbolBB obtenerNodo2(NodoArbolBB n, Comparable e) {
        NodoArbolBB nuevo = null;
        if (n != null) {
            if (n.getElemento().compareTo(e) == 0) {
                nuevo = n;

            } else if (n.getElemento().compareTo(e) > 0) {
                nuevo = obtenerNodo2(n.getIzquierdo(), e);
            } else {
                nuevo = obtenerNodo2(n.getDerecho(), e);
            }

        }
        return nuevo;
    }

    public Lista listarMayorQue(Comparable elemento, Comparable valor) {
        Lista lista = new Lista();
        NodoArbolBB nodoPadre = obtenerNodo(elemento);
        if (nodoPadre != null) {
            listarMayorAux(nodoPadre, lista, valor);
        }
        return lista;
    }

    private void listarMayorAux(NodoArbolBB nodo, Lista lista, Comparable x) {
        if (nodo != null) {
            Comparable elemento = nodo.getElemento();
            listarMayorAux(nodo.getDerecho(), lista, x);
            if (x.compareTo(elemento) < 0) {
                lista.insertar(elemento, 1);
                listarMayorAux(nodo.getIzquierdo(), lista, x);
            }
        }
    }
//
//    public NodoArbolBB clonarParteInvertida(Comparable elem) {
//        NodoArbolBB clonado = new NodoArbolBB();
//        if (!this.esVacio()) {
//            clonado.raiz = clonarInvertAux(this.raiz, elem);
//        }
//        return clonado;
//
//    }

//    private NodoArbolBB clonarInvertAux(NodoArbolBB n, Comparable elem) {
//
//        NodoArbolBB raizNueva = null;
//        if (n != null) {
//
//            if (n.getElemento().compareTo(elem) == 0) {
//                //Encontro el elemento entonces se llama a un clonar con esa raiz
//                raizNueva = clonarParteAux(n);
//
//            } else if (elem.compareTo(n.getElemento()) < 0) {
//                //Baja por la rama izquierda
//                raizNueva = clonarInvertAux(n.getIzquierdo(), elem);
//            } else {
//                //Baja por la rama derecha
//                raizNueva = clonarInvertAux(n.getDerecho(), elem);
//            }
//        }
//
//        return raizNueva;
//    }
//
//    private NodoArbolBB clonarParteAux(NodoArbolBB n) {
//
//        NodoArbolBB nuevo = new NodoArbolBB(n.getElemento(), null, null);
//
//        if (n.getDerecho() != null) {
//            nuevo.setIzquierdo(clonarParteAux(n.getDerecho()));
//        }
//        if (n.getIzquierdo() != null) {
//            nuevo.setDerecho(clonarParteAux(n.getIzquierdo()));
//        }
//
//        return nuevo;
//    }
    public int amplitudSubarbol(Comparable elem){
        int valor=-1;
        if(!esVacio()){
            NodoArbolBB nodo= obtenerNodo2(this.raiz,elem);
            if(nodo!=null && nodo.getElemento().compareTo(elem)==0){
                valor= mayor(nodo)-menor(nodo);
            }
        }
        return valor;
    }
    private int mayor (NodoArbolBB nodo){
        int res=-2;
        if(nodo.getDerecho()!=null){
            nodo= nodo.getDerecho();
            while(nodo.getDerecho()!=null){
                nodo=nodo.getDerecho();
                
            }
            res=(int)nodo.getElemento();
        }
         res=(int)nodo.getElemento();
        return res;
    }
    private int menor (NodoArbolBB nodo){
        int res=-2;
        if(nodo.getIzquierdo()!=null){
            nodo=nodo.getIzquierdo();
            while(nodo.getIzquierdo()!=null){
                nodo=nodo.getIzquierdo();
            }
            res=(int)nodo.getElemento();
        }
         res=(int)nodo.getElemento();
        return res;
    }
    public int diferenciaCandidatos(Comparable elem) {
        int valor = -1;

        if (!esVacio()) {
            NodoArbolBB nodo = obtenerNodo2(this.raiz, elem);
            if (nodo != null && nodo.getElemento().compareTo(elem) == 0) {
                valor = menorDerecho(nodo)- mayorIzq(nodo);

                
            }
        }
        return valor;
    }

    private int mayorIzq(NodoArbolBB nodo) {
        int res = -2;
        if (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
            if (nodo.getDerecho() != null) {
                while (nodo.getDerecho() != null) {
                    nodo = nodo.getDerecho();
                }
            }
            res = (int) nodo.getElemento();
        }
        return res;
    }

    private int menorDerecho(NodoArbolBB nodo) {
        int res = -2;
        if (nodo.getDerecho() != null) {
            nodo = nodo.getDerecho();
            if (nodo.getIzquierdo() != null) {
                while (nodo.getIzquierdo() != null) {
                    nodo = nodo.getIzquierdo();
                }

            }
            res = (int) nodo.getElemento();
        }

        return res;
    }
}

//    private int diferenciaAux(NodoArbolBB nodo) {
//        int total, y = 0, x = 0;
//
//        NodoArbolBB hijoD = nodo.getDerecho();
//        NodoArbolBB hijoIzq = nodo.getIzquierdo();
//        while (hijoD != null) {
//            NodoArbolBB hijoAuxD = hijoD.getIzquierdo();
//            if (hijoAuxD == null) {
//                x = (int) hijoD.getElemento();
//
//            } else if (hijoAuxD != null && hijoAuxD.getIzquierdo() == null && hijoAuxD.getDerecho() == null) {
//                x = (int) hijoAuxD.getElemento();
//
//            } else {
//                diferenciaAux(hijoAuxD.getIzquierdo());
//
//            }
//            hijoD = hijoD.getIzquierdo();
//        }
//
//        while (hijoIzq != null) {
//            NodoArbolBB hijoAuxIzq = hijoIzq.getDerecho();
//            if (hijoAuxIzq == null) {
//                y = (int) hijoIzq.getElemento();
//
//            } else if (hijoAuxIzq != null && hijoAuxIzq.getDerecho() == null && hijoAuxIzq.getIzquierdo() == null) {
//                y = (int) hijoAuxIzq.getElemento();
//
//            } else {
//                diferenciaAux(hijoAuxIzq.getDerecho());
//            }
//            hijoIzq = hijoIzq.getDerecho();
//        }
//
//        total = x - y;
//        return total;
//    }
//}
