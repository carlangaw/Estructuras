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

    private void eliminarMaxAux(NodoArbolBB nodo, NodoArbolBB padre) {
        if (nodo.getDerecho() != null) {
            eliminarMaxAux(nodo.getDerecho(), nodo);

        } else if (nodo.getIzquierdo() != null) {
            padre.setDerecho(nodo.getIzquierdo());
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

    private void eliminarMinAux(NodoArbolBB nodo, NodoArbolBB padre) {
        if (nodo.getIzquierdo() != null) {
            eliminarMinAux(nodo.getIzquierdo(), nodo);

        } else if (nodo.getDerecho() != null) {
            padre.setIzquierdo(nodo.getDerecho());
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
    public Lista listarMayorIgual(Comparable elem) {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarMayorIgualAux(this.raiz, lista, elem);
        }
        return lista;
    }

    private void listarMayorIgualAux(NodoArbolBB nodo, Lista lista, Comparable elem) {
        if (nodo != null) {
            if (elem.compareTo(nodo.getElemento()) <= 0) {
                listarMayorIgualAux(nodo.getDerecho(), lista, elem);
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);
                if (nodo.getIzquierdo() != null) {
                    listarMayorIgualAux(nodo.getIzquierdo(), lista, elem);
                }
            } else {
                listarMayorIgualAux(nodo.getDerecho(), lista, elem);
            }
        }
    }

    public Lista listarMenorIgual(Comparable elem) {
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarMenorAux(this.raiz, lista, elem);
        }
        return lista;
    }

    private void listarMenorAux(NodoArbolBB nodo, Lista lista, Comparable elem) {
        if (nodo != null) {
            if (elem.compareTo(nodo.getElemento()) >= 0) {

                listarMenorAux(nodo.getIzquierdo(), lista, elem);
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);

                if (nodo.getDerecho() != null) {
                    listarMenorAux(nodo.getDerecho(), lista, elem);
                }
            } else {
                listarMenorAux(nodo.getIzquierdo(), lista, elem);

            }
        }

    }

    public boolean eliminarMinimo2() {
        boolean exito = false;
        if (this.raiz != null) {
            NodoArbolBB nodo = this.raiz;
            NodoArbolBB izquierdo = nodo.getIzquierdo();
            if (izquierdo == null) {
                // el nodo derecho puede ser null o no
                this.raiz = nodo.getDerecho();
            } else {
                // bajo por la izquierda
                while (izquierdo.getIzquierdo() != null) {
                    nodo = izquierdo;
                    izquierdo = nodo.getIzquierdo();
                }
                // el nodo derecho puede ser null o no
                nodo.setIzquierdo(izquierdo.getDerecho());
            }
            exito = true;
        }
        return exito;
    }

    public boolean eliminarMaximo2() {
        boolean exito = false;
        if (this.raiz != null) {
            NodoArbolBB nodo = this.raiz;
            NodoArbolBB derecho = nodo.getDerecho();
            if (derecho == null) {
                this.raiz = nodo.getIzquierdo();
            } else {
                while (derecho.getDerecho() != null) {
                    nodo = derecho;
                    derecho = nodo.getDerecho();
                }
                nodo.setDerecho(derecho.getIzquierdo());
            }
            exito = true;
        }
        return exito;
    }
//Devuelve un nuevo arbol que es una copia del subarbol original cuya raiz es elemento dado
    //y cada hijo esta cambiado de lugar. Si el elem no existe el arbol devuelve es vacio.

    public ArbolBinBus clonarParteInvertida(Comparable elem) {
        ArbolBinBus arbolRetorno = new ArbolBinBus();
        if (this.raiz != null) {
            NodoArbolBB padre = obtenerNodo(elem);
            if (padre != null) {
                arbolRetorno.raiz = new NodoArbolBB(padre.getElemento());
                clonarParteInvertidaAux(padre, arbolRetorno.raiz);
            }
        }
        return arbolRetorno;
    }

    private void clonarParteInvertidaAux(NodoArbolBB padre, NodoArbolBB arbol) {
        if (padre != null) {

            if (padre.getDerecho() != null) {
                NodoArbolBB hijoDer = new NodoArbolBB(padre.getDerecho().getElemento());
                arbol.setIzquierdo(hijoDer);

                clonarParteInvertidaAux(padre.getDerecho(), arbol.getIzquierdo());
            }
            if (padre.getIzquierdo() != null) {
                NodoArbolBB hijoIzq = new NodoArbolBB(padre.getIzquierdo().getElemento());
                arbol.setDerecho(hijoIzq);
                clonarParteInvertidaAux(padre.getIzquierdo(), arbol.getDerecho());

            }
        }
    }

    //Retorna la diferencia entre el menor elemento del lado derecho y el mayor elemento del lado izquierdo del subarbol
    //cuya raiz es el elemento pasado por parametro. Si el elemento no existe retorna -1 y si alguno de los subarboles es nulo
    //retorna -2
    public int diferenciaCandidatos(Comparable elem) {
        int res = -1;
        if (this.raiz != null) {

            NodoArbolBB nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                if (nuevo.getIzquierdo() == null && nuevo.getDerecho() == null) { //Se puede hacer en el public????
                    res = -2;
                } else {
                    res = diferenciaCandidatosAuxIzq(nuevo) - diferenciaCandidatosAuxDer(nuevo);
                }
            }

        }
        return res;
    }

    private int diferenciaCandidatosAuxIzq(NodoArbolBB nodo) {
        int menorElemDerecho;
        if (nodo.getDerecho() != null) {
            NodoArbolBB aux = nodo.getDerecho();
            if (aux.getIzquierdo() != null) {
                while (aux.getIzquierdo() != null) {
                    aux = aux.getIzquierdo();
                }
            }

            menorElemDerecho = (int) aux.getElemento();
        } else {
            menorElemDerecho = (int) nodo.getElemento();
        }
        return menorElemDerecho;
    }

    private int diferenciaCandidatosAuxDer(NodoArbolBB nodo) {
        int mayorElemIzquierdo;
        if (nodo.getIzquierdo() != null) {
            NodoArbolBB aux = nodo.getIzquierdo();
            if (aux.getDerecho() != null) {
                while (aux.getDerecho() != null) {
                    aux = aux.getDerecho();
                }
            }
            mayorElemIzquierdo = (int) aux.getElemento();
        } else {
            mayorElemIzquierdo = (int) nodo.getElemento();
        }
        return mayorElemIzquierdo;
    }
    //Retorna el candidato que tenga la menor diferencia con elem, si solo hay un candidato devuelve siempre ese.
    //Si elemento no existe retorna 0, y si ambos subarboles son nulos retorna -1. 

    public int mejorCandidato(Comparable elem) {
        int res = 0, mayor, menor;
        if (this.raiz != null) {
            NodoArbolBB nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                if (nuevo.getIzquierdo() == null && nuevo.getDerecho() == null) { //Se puede hacer en el public????
                    res = -1;
                } else {
                    mayor = diferenciaCandidatosAuxDer(nuevo); // Mayor elem del subarbol izquierdo
                    System.out.println("Mayor es: " + mayor);
                    menor = diferenciaCandidatosAuxIzq(nuevo); // Menor elem del subarbol derecho
                    System.out.println("Menor es: " + menor);
                    if ((int) menor - (int) elem < (int) elem - (int) mayor) {
                        res = menor;
                    } else {
                        res = mayor;
                    }
                }
            }
        }
        return res;
    }
    //Retorna la diferencia entre el mayor y el menor elemento del subarbol cuya raiz es el elemento pasado por parametro.
    //Si el elemento no existe retorna -1 y si alguna de los subarboles es nulo retorna la diferencia entre el hijo que está
    //y la raíz del subarbol. Si los dos hijos son null devuelve 0 ya que null - null = 0

    public int amplitudSubarbol(Comparable elem) {
        int res = 0;
        if (this.raiz != null) {
            NodoArbolBB nuevo = obtenerNodo(elem);
            if (nuevo != null) {
                if (nuevo.getIzquierdo() == null && nuevo.getDerecho() == null) { //CONSULTAR SI HAGO LA RESTA DEL HIJO QUE ESTÁ Y LA RAÍZ DEL SUBARBOL O LO AHORRO CON ESTA SENTENCIA?
                    res = 0;
                } else {
                    res = amplitudSubarbolDer(nuevo) - amplitudSubarbolIzq(nuevo);
                }
            } else {
                res = -1;
            }
        }
        return res;
    }

    private int amplitudSubarbolDer(NodoArbolBB nodo) {
        int mayorElem;
        if (nodo.getDerecho() != null) {
            NodoArbolBB aux = nodo.getDerecho();
            while (aux.getDerecho() != null) {
                aux = aux.getDerecho();
            }
            mayorElem = (int) aux.getElemento();
        } else {
            mayorElem = (int) nodo.getElemento();
        }

        return mayorElem;
    }

    private int amplitudSubarbolIzq(NodoArbolBB nodo) {
        int menorElem;
        if (nodo.getIzquierdo() != null) {
            NodoArbolBB aux = nodo.getIzquierdo();
            while (aux.getIzquierdo() != null) {
                aux = aux.getIzquierdo();
            }
            menorElem = (int) aux.getElemento();
        } else {
            menorElem = (int) nodo.getElemento();
        }

        return menorElem;
    }

    public String concatenarPosordenDesde(Comparable elem, int x) {
        String retorno = "@@@";
        if (this.raiz != null) {
            NodoArbolBB subArbol = obtenerNodo(elem);
            if (subArbol != null) {
                if (subArbol.getIzquierdo() == null && subArbol.getDerecho() == null) {
                    retorno = "@@@";
                } else {
                    retorno = concatenarAux(subArbol, x, 0);
                }
            }
        }
        return retorno;
    }

    private String concatenarAux(NodoArbolBB n, int x, int cont) {
        String concatenacion = " ";
        String cad = "@";

        if (n != null) {
            concatenarAux(n.getIzquierdo(), x, cont + 1);
             concatenacion = concatenacion + n.getElemento();
            concatenarAux(n.getDerecho(), x, cont + 1);
            concatenacion = concatenacion + n.getElemento();
        }
   
        return concatenacion;
    }
}
