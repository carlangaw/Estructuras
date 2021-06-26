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
//atributos

    private NodoGen raiz;
// constructor

    public ArbolGen() {
        this.raiz = null;
    }

    /*
   Quita todos los elementos de la estructura. El manejo de memoria es similar al explicado anteriormente
   para estructuras lineales dinámicas.
     */
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
// Devuelve una lista con los elementos del árbol en el recorrido en posorden

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

//    public boolean insertar(Object elemNuevo, Object elemPadre) {
//         Dado un elemento elemNuevo y un elemento elemPadre, inserta elemNuevo como hijo izquierdo o
//        derecho de la primer aparición de elemPadre, según lo indique el parámetro posicion. Para que la operación
//        termine con éxito debe existir un nodo en el árbol con elemento = elemPadre y ese nodo debe tener libre
//        su hijo posicion. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso. 
//        boolean exito = true;
//
//        if (this.raiz == null) {
//            Si el arbol esta vacio ponemos el elemento nuevo en la raiz
//            this.raiz = new NodoGen(elemNuevo);
//        } else {
//            Debemos buscar si existe el padre
//            NodoGen padre = obtenerNodo(this.raiz, elemPadre);
//            if (padre != null) {
//                Existe el padre
//                NodoGen nodoNuevo = new NodoGen(elemNuevo);
//                NodoGen nodoIzquierdo = padre.getHijoExtremoIzquierdo();
//                Si el padre no tiene hijo izq , inserto el nuevo como hijo izquierdo
//                if (nodoIzquierdo == null) {
//                    padre.setHijoExtremoIzquierdo(nodoNuevo);
//                } else {
//                    Si tiene hijo izquierdo, debo insertarlo al final de sus hermanos derechos
//                    while (nodoIzquierdo.getHermanoDerecho() != null) {
//                        nodoIzquierdo = nodoIzquierdo.getHermanoDerecho();
//                    }
//                    nodoIzquierdo.setHermanoDerecho(nodoNuevo);
//                }
//            } else {
//                exito = false;
//            }
//        }
//        return exito;
//    }
    public boolean insertar(Object elemNuevo, Object elemPadre) {
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

    // Devuelve verdadero si el elemento pasado por parámetro está en el árbol, y falso en caso contrario.
    public boolean pertenece(Object elemento) {

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

    /*
     Si el elemento se encuentra en el árbol, devuelve una lista con el camino desde la raíz hasta dicho
    elemento (es decir, con los ancestros del elemento). Si el elemento no está en el árbol devuelve la lista
    vacía.
     */
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
//
//    private NodoGen obtenerNodo(NodoGen raiz, Object buscar) {
//        NodoGen respuesta = null;
//        NodoGen aux = null;
//        if (raiz != null) {
//            if (raiz.getElemento().equals(buscar)) {
//                respuesta = raiz;
//            } else {
//                aux = raiz.getHijoExtremoIzquierdo();
//                while (aux != null && respuesta == null) {
//                    respuesta = obtenerNodo(aux, buscar);
//                    aux = aux.getHermanoDerecho();
//                }
//            }
//        }
//        return respuesta;
//    }
//Metodo que me devuelve el nodo del elemento buscado que envio por parametro

    private NodoGen obtenerNodo(NodoGen nodo, Object elemento) {
        NodoGen res = null;
        if (nodo != null) {
            if (nodo.getElemento().equals(elemento)) {
                res = nodo;
            } else {
                res = obtenerNodo(nodo.getHijoExtremoIzquierdo(), elemento);
                if (res == null) {
                    res = obtenerNodo(nodo.getHermanoDerecho(), elemento);
                }
            }
        }
        return res;
    }

    public Object padre(Object elemento) {
        //Si se cumple que raiz es vacia o el elemento de la raiz es igual al elemento enviado por parametro
        //Devuelvo null, sino devuelvo el padre
        return (this.raiz == null || this.raiz.getElemento().equals(elemento))
                ? null : padreAux(this.raiz, elemento);
    }

    private Object padreAux(NodoGen nodoActual, Object elemento) {
        Object res = null;
        if (nodoActual != null) {
            NodoGen sig = nodoActual.getHijoExtremoIzquierdo();
            while (sig != null && !sig.getElemento().equals(elemento)) {
                sig = sig.getHermanoDerecho();
            }
            if (sig != null) {
                res = nodoActual.getElemento();
            } else {
                res = padreAux(nodoActual.getHijoExtremoIzquierdo(), elemento);
                if (res == null) {
                    res = padreAux(nodoActual.getHermanoDerecho(), elemento);
                }
            }
        }
        return res;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        int cont = 0;
        Lista lista = new Lista();
        if (this.raiz != null) {
            listarEntreNivelesAux(this.raiz, lista, niv1, niv2, cont);
        }
        return lista;
    }

    private void listarEntreNivelesAux(NodoGen nodo, Lista lista, int nivMin, int nivMax, int contNivel) {
        if (nodo != null) {

            // Llamado recursivo con HEI
            if (contNivel < nivMax) {
                listarEntreNivelesAux(nodo.getHijoExtremoIzquierdo(), lista, nivMin, nivMax, contNivel + 1);

            }
            if (contNivel >= nivMin) {
                lista.insertar(nodo.getElemento(), lista.longitud() + 1);
            }
            if (contNivel < nivMax) {
                if (nodo.getHijoExtremoIzquierdo() != null) {
                    // Baja a HD, mirando desde el padre
                    NodoGen hijo = nodo.getHijoExtremoIzquierdo().getHermanoDerecho();

                    while (hijo != null) {
                        // Llamado recursivo
                        listarEntreNivelesAux(hijo, lista, nivMin, nivMax, contNivel + 1);

                        // Avanzo sobre los hermanos, mirando desde el padre
                        hijo = hijo.getHermanoDerecho();
                    }
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
        boolean resultado = false;
        if (this.raiz != null) {
            resultado = verificarCaminoAux(listado, this.raiz, 1);
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
//    public boolean verificarCamino(Lista lista) {
//        return verificarCaminoAux(this.raiz, lista, 1);
//    }
//
//    private boolean verificarCaminoAux(NodoGen nodo, Lista lista, int pos) {
//        boolean res = false;
////        //Caso base raiz igual al primer elemento de la lista
//        if (nodo != null) {
//            Object elem = lista.recuperar(pos);
//            if (nodo.getElemento().equals(elem)) {
//                if (lista.longitud() == pos) {
//                    res = true;
//                } else {
//                    res = verificarCaminoAux(nodo.getHijoExtremoIzquierdo(), lista, pos + 1);
//                }
//            } else {
//                res = verificarCaminoAux(nodo.getHermanoDerecho(), lista, pos);
//            }
//        }
//        return res;
//    }

    /*
    Devuelve una lista con los elementos del árbol en el recorrido por niveles 
     */
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
        int grado = -1;
        if (this.raiz != null) {
            grado = gradoAux(this.raiz);

        }
        return grado;
    }

    private int gradoAux(NodoGen nodo) {
        int cant = 0;
        int cantAux = 0;
        if (nodo != null) {
            NodoGen hijo = nodo.getHijoExtremoIzquierdo();
            while (hijo != null) {
                cant++;
                hijo = hijo.getHermanoDerecho();

            }
            hijo = nodo.getHijoExtremoIzquierdo();
            while (hijo != null) {
                cantAux = gradoAux(hijo);
                hijo = hijo.getHermanoDerecho();
                if (cant >= cantAux) {

                    cantAux = cant;

                }

            }

        }

        return cantAux;
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

    public int gradoSubarbol(Object elemento) {
        int cant = -1;
        if (this.raiz != null) {
            NodoGen nodo = obtenerNodo(this.raiz, elemento);
            if (nodo != null) {
                cant = gradoSubAux(nodo);
            }
        }
        return cant;
    }

    private int gradoSubAux(NodoGen nodo) {
        int cont = 0;

        if (nodo.getHijoExtremoIzquierdo() != null) {
            cont++;
            NodoGen hijo = nodo.getHijoExtremoIzquierdo();

            while (hijo.getHermanoDerecho() != null) {
                cont++;
                hijo = hijo.getHermanoDerecho();

            }
        }
        return cont;
    }

    public void insertarEnPos(Object elem, Object padre, int pos) {
        /*
        Inserta elem como hijo de padre en la posicion pos dentro de la lista de hermano. Si valor de pos es invalido
        debera insertar elem como ultimo hermano derecho.
         */

        if (this.raiz != null) {
            NodoGen nodo = obtenerNodo(this.raiz, padre);
            if (nodo != null) {
                insertarPosAux(nodo, elem, pos);
            }
        }
    }

    private void insertarPosAux(NodoGen raiz, Object elem, int pos) {
        int cont = 0; //voy a contar la cantidad de hermanos
        NodoGen nuevo = new NodoGen(elem);
        if (raiz.getHijoExtremoIzquierdo() != null) {
            cont++;
            NodoGen hijo = raiz.getHijoExtremoIzquierdo();
            //caso base si pos=1 es decir no tiene hei
            if (cont == pos) {
                raiz.setHijoExtremoIzquierdo(nuevo);
                nuevo.setHermanoDerecho(hijo);

            } else {
                while (hijo.getHermanoDerecho() != null && cont < pos) {
                    cont++;
                    if (cont <= pos) {
                        NodoGen posterior = hijo.getHermanoDerecho(); //D
                        //hijo vale C

                        if (cont == pos) {
                            hijo.setHermanoDerecho(nuevo);
                            nuevo.setHermanoDerecho(posterior);

                        }

                    }
                    hijo = hijo.getHermanoDerecho();
                }
                if (hijo.getHermanoDerecho() == null) //caso posicion invalida
                {
                    hijo.setHermanoDerecho(nuevo);
                }

            }
        } else {
            //pos 1 y no tengo hijo izq
            raiz.setHijoExtremoIzquierdo(nuevo);
        }
    }

    public Lista listarHastaNivel(int niv) {
        Lista listaniv = new Lista();
        int cont = 0;

        if (this.raiz != null) {
            listarHastaAux(niv, this.raiz, listaniv, cont);
        }
        return listaniv;
    }

    private void listarHastaAux(int niv, NodoGen nodo, Lista listaniv, int cont) {

        if (nodo != null) {
            if (cont <= niv) {
                listaniv.insertar(nodo.getElemento(), listaniv.longitud() + 1);

                NodoGen aux = nodo.getHijoExtremoIzquierdo();
                while (aux != null) {
                    listarHastaAux(niv, aux, listaniv, cont + 1);
                    aux = aux.getHermanoDerecho();

                }

            }
        }
    }

    public boolean esHijoDe(Object a, Object b) {
        boolean res = false;
        if (this.raiz != null) {
            res = esHijoDeAux(this.raiz, a, b, false, false);
        }
        return res;
    }

    private boolean esHijoDeAux(NodoGen n, Object a, Object b, boolean retorno, boolean encontrado) {
        if (n != null) {
            if (n.getElemento().equals(b)) { //b elemento padre del elemento a
                encontrado = true;
                NodoGen hijoIzq = n.getHijoExtremoIzquierdo();
                while (hijoIzq != null && encontrado && !retorno) {
                    if (hijoIzq.getElemento().equals(a)) { //pregunto si es el hijo 'a'
                        retorno = true; // es hijo
                    } else {
                        hijoIzq = hijoIzq.getHermanoDerecho();
                    }

                }
                retorno = esHijoDeAux(n.getHijoExtremoIzquierdo().getHermanoDerecho(), a, b, retorno, encontrado);

            } else {
                //si no es el elemento a buscar 'b' se va por el recorrido de los hijos izquierdos
               
                retorno = esHijoDeAux(n.getHijoExtremoIzquierdo(), a, b, retorno, encontrado);
                //guardo al nodo retornado anterior para recorrer sobre sus hermanos
                NodoGen ant= n;
               //en caso de recorrer por lado izq y no encontrar al elemento B, me voy por los hermanos
                if (!encontrado && !retorno) {
                    retorno = esHijoDeAux(ant.getHermanoDerecho(), a, b, retorno, encontrado);
                }

            }

        }

        return retorno;
    }

}
