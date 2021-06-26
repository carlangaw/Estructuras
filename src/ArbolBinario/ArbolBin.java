package ArbolBinario;

import lista.Lista;

/**
 *
 * @author Carla Nuñez FAI-1631 / Mauricio Sawicki FAI-2256
 */
//atributos
public class ArbolBin {

    private NodoArbol raiz;

    // Crea un árbol binario vacío
    public ArbolBin() {
        this.raiz = null;
    }

    // Dado un elemento elemNuevo y un elemento elemPadre, inserta elemNuevo como hijo izquierdo o
    //derecho de la primer aparición de elemPadre, según lo indique el parámetro posicion. Para que la operación
    //termine con éxito debe existir un nodo en el árbol con elemento = elemPadre y ese nodo debe tener libre
    //su hijo posicion. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso.
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

    // Devuelve falso si hay al menos un elemento cargado en el árbol y verdadero en caso contrario.
    public boolean esVacio() {
        boolean respuesta = false;
        if (raiz == null) {
            respuesta = true;
        }
        return respuesta;
    }

    private void preOrdenAux(NodoArbol nodo, int pos, Lista lista) {
        if (nodo != null) {
            lista.insertar(nodo.getElemento(), pos);
            preOrdenAux(nodo.getIzquierdo(), pos + 1, lista);
            preOrdenAux(nodo.getDerecho(), lista.longitud() + 1, lista);

        }
    }

    // Devuelve una lista con los elementos del árbol binario en el recorrido en preorden
    public Lista listarPreorden() {
        Lista lista = new Lista();
        preOrdenAux(this.raiz, 1, lista);
        return lista;
    }
    // Dado un elemento devuelve el valor almacenado en su nodo padre (busca la primera aparición de elemento).

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

    // Devuelve una lista con los elementos del árbol binario en el recorrido en posorden
    public Lista listarPosorden() {
        Lista lista2 = new Lista();
        posOrdenAux(this.raiz, lista2);
        return lista2;
    }

    // Devuelve una lista con los elementos del árbol binario en el recorrido en inorden
    public Lista listarInorden() {
        Lista lista3 = new Lista();
        inOrdenAux(this.raiz, lista3);
        return lista3;

    }

    private void inOrdenAux(NodoArbol nodo, Lista lista3) {
        if (nodo != null) {
            inOrdenAux(nodo.getIzquierdo(), lista3);
            lista3.insertar(nodo.getElemento(), lista3.longitud() + 1);
            inOrdenAux(nodo.getDerecho(), lista3);
        }
    }

    private void posOrdenAux(NodoArbol nodo, Lista lista2) {
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

    // Quita todos los elementos de la estructura. El manejo de memoria es similar al explicado anteriormente
    //para estructuras lineales dinámicas.
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

    // Devuelve la altura del árbol, es decir la longitud del camino más largo desde la raíz hasta una hoja
//(Nota: un árbol vacío tiene altura -1 y una hoja tiene altura 0).
    public int altura() {
        int alt;
        alt = alturaAux(this.raiz);
        return alt;
    }

    private int nivelAux(NodoArbol nodoActual, Object elem, int profundidadActual) {
        int res = -1;

        if (nodoActual != null) {
            //visito nodoActual
            //si el elemActual es igual al elem ingresado por parametro devuelvo el nivel
            if (nodoActual.getElemento().equals(elem)) {
                res = profundidadActual;
            } else {
                res = nivelAux(nodoActual.getIzquierdo(), elem, profundidadActual + 1);
                if (res == -1) //corta si lo encontro por izquierda        
                {
                    res = nivelAux(nodoActual.getDerecho(), elem, profundidadActual + 1);
                }

            }
        }
        return res;
    }

    // Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1.
    public int nivel(Object elem) {
        int level = -1;
        if (!this.esVacio()) {
            level = nivelAux(this.raiz, elem, 0);
        }
        return level;
    }

    // Genera y devuelve una cadena de caracteres que indica cuál es la raíz del árbol y quienes son los hijos
    //de cada nodo.
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

    // Devuelve una lista con todos los nodos hojas del arbol.
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

    // Genera y devuelve un árbol binario que es equivalente (igual estructura y contenido de los nodos) que el árbol original.
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

    public boolean verificarPatron(Lista listaPatron) {
        boolean res;
        res = verificarRecursivo(this.raiz, listaPatron, 1);
        return res;

    }

    private boolean verificarRecursivo(NodoArbol raiz, Lista listaPatron, int posicion) {
        boolean aux = false;

        if (posicion > listaPatron.longitud()) {
            aux = true;
        } else if ((raiz != null) && (raiz.getElemento()) == listaPatron.recuperar(posicion)) {

            aux = verificarRecursivo(raiz.getIzquierdo(), listaPatron, posicion + 1);
            if (!aux) {

                aux = verificarRecursivo(raiz.getDerecho(), listaPatron, posicion + 1);
            }
        }
        return aux;
    }

    /*
    clonarInvertido() que devuelve un nuevo árbol, que es una copia del árbol original(this) pero donde los hijos
    están cambiados de lugar. Atención: el método devuelve un nuevo árbol, sin modificar el árbol original.
     */
    public ArbolBin clonarInvertido() {
        ArbolBin arbolClon = new ArbolBin();
        if (this.raiz != null) {
            arbolClon.raiz = new NodoArbol(this.raiz.getElemento(), null, null);
            clonarInvertidoAux(this.raiz, arbolClon.raiz);
        }
        return arbolClon;
    }

    private void clonarInvertidoAux(NodoArbol nOrig, NodoArbol nClon) {
        if (nOrig.getDerecho() != null) {
            //Si el HD del arbol original tiene elemento
            //Creo un nuevo nodo y a ese nodo le seteo el elemento HD del arbol original
            //Ese nuevo nodo creado se lo pongo como HI al arbolClon
            nClon.setIzquierdo(new NodoArbol(nOrig.getDerecho().getElemento(), null, null));
            //Llamo recursivamente para hacer lo mismo con toda la rama izquierda del arbol original
            clonarInvertidoAux(nOrig.getDerecho(), nClon.getIzquierdo());
        }
        if (nOrig.getIzquierdo() != null) {
            //Si el HI del arbol original tiene elemento
            //Creo un nuevo nodo y a ese nodo le seteo el elemento HI del arbol original
            //Ese nuevo nodo creado se lo pongo como HD al arbolClon
            nClon.setDerecho(new NodoArbol(nOrig.getIzquierdo().getElemento(), null, null));
            //Llamo recursivamente para hacer lo mismo con toda la rama derecha del arbol original
            clonarInvertidoAux(nOrig.getIzquierdo(), nClon.getDerecho());
        }
    }

    /*
    Verifica que el árbol otro sea igual al arbol this. 
    No puede usar los métodos básicos del TDA (insertar, existe, etc) 
    y debe de recorrer lo mínimo ambos árboles.
     */
    public boolean equals(ArbolBin otro) {
        return equalsAux(this.raiz, otro.raiz);
    }

    private boolean equalsAux(NodoArbol nodoA1, NodoArbol nodoA2) {
        boolean res = true;
        if (nodoA1 != null && nodoA2 != null) {
            if (!nodoA1.getElemento().equals(nodoA2.getElemento())) {
                res = false;
            } else {
                res = equalsAux(nodoA1.getIzquierdo(), nodoA2.getIzquierdo());

                if (res) {
                    res = equalsAux(nodoA1.getDerecho(), nodoA2.getDerecho());
                }
            }
        }

        return res;
    }

    public void modificarSubarboles(Object d1, Object d2, Object d3) {
        if (this.raiz != null) {
            NodoArbol nodoNuevo = obtenerNodo(this.raiz, d1);
            if (nodoNuevo != null) {
                modificarAux(nodoNuevo, d2, d3);
            }
        }

    }

    private void modificarAux(NodoArbol nodoNuevo, Object d2, Object d3) {

        if (nodoNuevo.getIzquierdo() == null && nodoNuevo.getDerecho() == null) {
            nodoNuevo.setIzquierdo(new NodoArbol(d2));
            nodoNuevo.setDerecho(new NodoArbol(d3));
        }

        if (nodoNuevo.getIzquierdo() != null && nodoNuevo.getDerecho() == null) {
            nodoNuevo.getIzquierdo().setElemento(d2);
            nodoNuevo.setDerecho(new NodoArbol(d3));

        } else {
            nodoNuevo.setIzquierdo(new NodoArbol(d2));
            nodoNuevo.getDerecho().setElemento(d3);
        }

    }
}
