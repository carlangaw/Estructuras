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
public class ArbolGen {

    private NodoGen raiz;

    public void vaciar() {
        this.raiz = null;
    }

    public boolean esVacio() {
        /**
         * Devuelve true si el arbol no contiene elementos o falso en caso
         * contrario
         */
        boolean respuesta = (this.raiz == null);

        return respuesta;
    }

    public Lista listarPreorden() {
        /**
         * Devuelve una lista con los elementos del arbol recorridos en pre
         * orden
         */
        Lista lista = new Lista();

        if (this.raiz != null) {
            listarPreOrdenAux(this.raiz, lista);
        }

        return lista;
    }

    private void listarPreOrdenAux(NodoGen nodo, Lista lista) {
        /**
         * metodo privado que carga una lista con los elementos del arbol
         * recorridos en pre orden _
         */

        if (nodo != null) {
            lista.insertar(nodo.getElemento(), lista.longitud() + 1);

            NodoGen hijo = nodo.getHijoExtremoIzquierdo();

            while (hijo != null) {
                listarPreOrdenAux(hijo, lista);

                hijo = hijo.getHermanoDerecho();
            }

        }
    }

    public Lista listarPosorden() {
        Lista listaPosorden = new Lista();
        if (!this.esVacio()) {
            listarposordenAux(this.raiz, listaPosorden, listaPosorden.longitud());
        }
        return listaPosorden;
    }

    private void listarposordenAux(NodoGen nodo, Lista lP, int pos) {

        if (nodo != null) {
            listarposordenAux(nodo.getHermanoDerecho(), lP, pos);
            lP.insertar(nodo.getElemento(), pos + 1);
            listarposordenAux(nodo.getHijoExtremoIzquierdo(), lP, pos);
        }
    }

    public Lista listarInorden() {
        /**
         * Devuelve una lista con los elementos del arbol recorridos en in orden
         */

        Lista lista = new Lista();

        if (this.raiz != null) {
            listarInOrdenAux(this.raiz, lista);
        }

        return lista;
    }

    private void listarInOrdenAux(NodoGen nodo, Lista lista) {
        /**
         * metodo privado que carga una lista con los elementos del arbol
         * recorridos en in orden
         */
        if (nodo != null) {
            if (nodo.getHijoExtremoIzquierdo() != null) {
                // lamo recursivo con HEI
                listarInOrdenAux(nodo.getHijoExtremoIzquierdo(), lista);
            }

            lista.insertar(nodo.getElemento(), lista.longitud() + 1);

            if (nodo.getHijoExtremoIzquierdo() != null) {
                // bajo a mi HD, mirando desde el padre
                NodoGen hijo = nodo.getHijoExtremoIzquierdo().getHermanoDerecho();

                while (hijo != null) {
                    // llamo recursivo
                    listarInOrdenAux(hijo, lista);

                    // avanzo sobre los hermanos, mirando desde el padre
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
//         Dado un elemento elemNuevo y un elemento elemPadre, inserta elemNuevo como hijo izquierdo o
//        derecho de la primer aparición de elemPadre, según lo indique el parámetro posicion. Para que la operación
//        termine con éxito debe existir un nodo en el árbol con elemento = elemPadre y ese nodo debe tener libre
//        su hijo posicion. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso. 
        boolean exito = true;

        if (this.raiz == null) {
            //Si el arbol esta vacio ponemos el elemento nuevo en la raiz
            this.raiz = new NodoGen(elemNuevo);
        } else {
            //Debemos buscar si existe el padre
            NodoGen padre = obtenerNodo(this.raiz, elemPadre);
            if (padre != null) {
                //Existe el padre
                NodoGen nodoNuevo = new NodoGen(elemNuevo);
                NodoGen nodoIzquierdo = padre.getHijoExtremoIzquierdo();
                //Si el padre no tiene hijo izq , inserto el nuevo como hijo izquierdo
                if (nodoIzquierdo == null) {
                    padre.setHijoExtremoIzquierdo(nodoNuevo);
                } else {
                    //Si tiene hijo izquierdo, debo insertarlo al final de sus hermanos derechos
                    while (nodoIzquierdo.getHermanoDerecho() != null) {
                        nodoIzquierdo = nodoIzquierdo.getHermanoDerecho();
                    }
                    nodoIzquierdo.setHermanoDerecho(nodoNuevo);
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    public boolean pertenece(Object elemento) {
        //Ingresa un elemento por parametro , y lo busco en mi arbol, si ese elemento pertenece al arbol devuelvo true, en caso contrario devuelvo false.

        boolean respuesta = true;
        NodoGen aux = obtenerNodo(this.raiz, elemento);
        if (aux == null) {
            respuesta = false;
        }
        return respuesta;
    }

    public int nivel(Object n) {
        // Devuelve el nivel de un elemento en el árbol. Si el elemento no existe en el árbol devuelve -1.        
        return nivelRec(n, this.raiz, 0);
    }

    private int nivelRec(Object elem, NodoGen n, int niv) {
        int nivel = -1;
        NodoGen aux = null;
        if (n != null) {
            if (n.getElemento().equals(elem)) {
                nivel = niv;
            } else {
                aux = n.getHijoExtremoIzquierdo();
                while (aux != null && nivel == -1) {
                    nivel = nivelRec(elem, aux, niv + 1);
                    aux = aux.getHermanoDerecho();
                }

            }
        }

        return nivel;
    }

    public Lista ancestros(Object elem) {
        Lista list = new Lista();
        if (elem.equals(this.raiz.getElemento()) || this.esVacio()) {
        } else {
            ancestrosAux(this.raiz, null, elem, list);
        }
        return list;
    }

    private void ancestrosAux(NodoGen nodo, Object padre, Object elem, Lista ls) {

        if (nodo != null) {
            if (nodo.getElemento().equals(elem)) {
                ls.insertar(padre, ls.longitud() + 1);
            } else {
                ancestrosAux(nodo.getHijoExtremoIzquierdo(), nodo.getElemento(), elem, ls);
                if (ls.esVacia()) {
                    ancestrosAux(nodo.getHermanoDerecho(), padre, elem, ls);
                } else if (padre != null) {
                    ls.insertar(padre, ls.longitud() + 1);
                }

            }

        }
    }

    private NodoGen obtenerNodo(NodoGen raiz, Object buscar) {
        NodoGen respuesta = null;
        NodoGen aux = null;
        if (raiz != null) {
            if (raiz.getElemento().equals(buscar)) {
                respuesta = raiz;
            } else {
                aux = raiz.getHijoExtremoIzquierdo();
                while (aux != null && respuesta == null) {
                    respuesta = obtenerNodo(aux, buscar);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return respuesta;
    }

    public Object padre(Object elemento) {
        /**
         * Dado el elemento pasado por parametro devuelve el valor almacenado en
         * su padre (busca la primera ocurrencia)
         */

        Object resultado = null;

        if (this.raiz != null) {
            resultado = padreAux(this.raiz, elemento);
        }

        return resultado;
    }

    private Object padreAux(NodoGen nodo, Object buscado) {
        /**
         * Metodo privado que devuelve el valor almacenado en el padre de la
         * primer ocurrencia del elemento pasado por parametro
         */
        Object resultado = null;

        if (nodo != null) {
            if (nodo.tengoHijo(buscado)) {
                resultado = nodo.getElemento();
            } else {
                NodoGen hijo = nodo.getHijoExtremoIzquierdo();

                while (hijo != null && resultado == null) {
                    // cond de corte
                    resultado = padreAux(hijo, buscado);

                    // avanzo sobre los hermanos
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }

        return resultado;
    }

    private boolean verificarCaminoAux(Lista listado, NodoGen a, int pos) {
        NodoGen aux;
        boolean respuesta = false;
        if (a != null) {
            if (pos < listado.longitud()) {
                if (a.getElemento().equals(listado.recuperar(pos))) {
                    aux = a.getHijoExtremoIzquierdo();
                    while (respuesta == false && aux != null) {
                        respuesta = verificarCaminoAux(listado, aux, pos + 1);
                        aux = aux.getHermanoDerecho();

                    }
                }
            } else if (pos == listado.longitud()) {
                respuesta = a.getElemento().equals(listado.recuperar(pos));
            } else {
                respuesta = true;
            }
        }
        return respuesta;
    }

    public Lista listarEntreNiveles(int nivelMinimo, int nivelMaximo) {
        Lista listaCargada = new Lista();
        int nivelActual = 0;
        listarEntreNivelesAux(this.raiz, listaCargada, nivelMinimo, nivelMaximo, nivelActual);
        return listaCargada;

    }

    private void listarEntreNivelesAux(NodoGen a, Lista listado, int nivelMinimo, int nivelMaximo, int nivelActual) {

        if (a != null) {
            if (nivelActual < nivelMaximo) {
                listarEntreNivelesAux(a.getHijoExtremoIzquierdo(), listado, nivelMinimo, nivelMaximo, nivelActual + 1);
            }
            if (nivelActual >= nivelMinimo) {
                listado.insertar(a.getElemento(), listado.longitud() + 1);
            }
            if (a.getHijoExtremoIzquierdo() != null) {
                NodoGen aux = null;
                aux = a.getHijoExtremoIzquierdo().getHermanoDerecho();
                while (aux != null) {
                    listarEntreNivelesAux(aux, listado, nivelMinimo, nivelMaximo, nivelActual + 1);
                    aux = aux.getHermanoDerecho();

                }

            }
        }

    }

    public boolean eliminar(Object elemento) {
        boolean respuesta = false;
        if (this.raiz.getElemento().equals(elemento)) {
            this.raiz = null;
            respuesta = true;
        } else {
            respuesta = eliminarAux(this.raiz, elemento);
        }
        return respuesta;

    }

    private boolean eliminarAux(NodoGen nodo, Object elemento) {
        boolean respuesta = false;
        if (nodo != null) {
            if (nodo.getHijoExtremoIzquierdo() != null && elemento.equals(nodo.getHijoExtremoIzquierdo().getElemento())) {
                nodo.setHijoExtremoIzquierdo(nodo.getHijoExtremoIzquierdo().getHermanoDerecho());
                respuesta = true;
            }

            if (nodo.getHermanoDerecho() != null && elemento.equals(nodo.getHermanoDerecho().getElemento())) {
                nodo.setHermanoDerecho(nodo.getHermanoDerecho().getHermanoDerecho());
            } else {
                NodoGen aux = nodo.getHijoExtremoIzquierdo();
                while (aux != null) {
                    eliminarAux(aux, elemento);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return respuesta;
    }

    public boolean verificarCamino(Lista listado) {
        boolean resultado;
        resultado = verificarCaminoAux(listado, this.raiz, 1);
        return resultado;
    }

    public Lista listarPorNiveles() {
        Lista ls = new Lista();
        Cola q = new Cola();

        q.poner(this.raiz);
        while (!q.esVacia()) {
            NodoGen nodo = (NodoGen) q.obtenerFrente();
            q.sacar();
            ls.insertar(nodo.getElemento(), ls.longitud() + 1);
            nodo = nodo.getHijoExtremoIzquierdo();
            while (nodo != null) {
                q.poner(nodo);
                nodo = nodo.getHermanoDerecho();
            }
        }

        return ls;
    }

    @Override
    public String toString() {
        String s;
        if (this.esVacio()) {
            s = "[]";
        } else {
            s = toStringAux(this.raiz);
        }
        return s;
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            //Visita del nodo n
            s += n.getElemento().toString() + " ->";
            NodoGen hijo = n.getHijoExtremoIzquierdo();
            while (hijo != null) {
                s += hijo.getElemento().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            //Comienza recorrido de los hijos de n llamando recursivamente
            //para que cada hijo agregue a su subcadena a la cadena general
            hijo = n.getHijoExtremoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public int grado() {
        //Metodo que devuelve la cantidad de hojas que tiene un arbol

        int grado = gradoAux(this.raiz, 0);

        return grado;
    }

    private int gradoAux(NodoGen nodo, int cantMax) {
        int cant = 1;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoExtremoIzquierdo();
            while (hijo != null) {
                gradoAux(hijo, cantMax);
                hijo = hijo.getHermanoDerecho();
                cant++;
                if (cant >= cantMax) {
                    cantMax = cant;
                }
            }
        }
        return cantMax;
    }

    @Override
    public ArbolGen clone() {
        // Genera y devuelve una cadena de caracteres que indica cuál es la raíz del árbol y quienes son los hijos
        //de cada nodo
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nuevo = new NodoGen(nodo.getElemento());
        if (nodo.getHijoExtremoIzquierdo() != null) {
            nuevo.setHijoExtremoIzquierdo(cloneAux(nodo.getHijoExtremoIzquierdo()));
        }
        if (nodo.getHermanoDerecho() != null) {
            nuevo.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }
        return nuevo;
    }

    public int altura() {
        return alturaAux(this.raiz);
    }

    private int alturaAux(NodoGen nodo) {
        int a = -1;
        int b = -1;

        if (nodo != null) {
            NodoGen hijo = nodo.getHijoExtremoIzquierdo();
            while (hijo != null) {
                a = alturaAux(hijo);
                if (a > b) {
                    b = a;
                }
                hijo = hijo.getHermanoDerecho();
            }

            b++;
        }

        return b;
    }

    public Lista frontera() {
        Lista ls = new Lista();

        if (!this.esVacio()) {
            fronteraAux(this.raiz, ls);
        }
        return ls;
    }

    private void fronteraAux(NodoGen nodo, Lista ls) {

        if (nodo.getHijoExtremoIzquierdo() == null) {
            //Si el nodo es hoja lo inserto en la lista
            ls.insertar(nodo.getElemento(), ls.longitud() + 1);
        } else {
            //Si el nodo no es hoja continuo bajando
            fronteraAux(nodo.getHijoExtremoIzquierdo(), ls);
            //Sigo por los hermanos
            NodoGen hijo = nodo.getHijoExtremoIzquierdo().getHermanoDerecho();
            while (hijo != null) {
                fronteraAux(hijo, ls);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Object misterioso(Object elem) {

        Object resultado = null;

        if (this.raiz != null && this.raiz.getElemento() != elem) {

            NodoGen nodo = misteriosoAux(this.raiz, elem);

            if (nodo != null) {

                resultado = nodo.getElemento();

            }

        }

        return resultado;

    }

    private NodoGen misteriosoAux(NodoGen n, Object elem) {

        NodoGen res = null;

        if (n != null) {

            NodoGen hijo = n.getHijoExtremoIzquierdo();

            boolean encontrado = false;

            while (hijo != null && !encontrado) {

                if ((hijo.getElemento().equals(elem))) {

                    res = n;

                    encontrado = true;

                } else {

                    hijo = hijo.getHermanoDerecho();

                }

            }

            hijo = n.getHijoExtremoIzquierdo();

            while (res == null && hijo != null) {

                res = misteriosoAux(hijo, elem);

                hijo = hijo.getHermanoDerecho();

            }

        }

        return res;

    }
}
