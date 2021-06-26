package lista;

/**
 *
 * @author Carla Nuñez FAI-1631 / Mauricio Sawicki FAI-2256
 */
public class Lista {

    private Nodo cabecera;

    // Crea y devuelve una lista vacía.
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;

    }

    // Agrega el nodoNuevo pasado por parámetro en la posición pos, de manera que la cantidad de elementos
    //de la lista se incrementa en 1. Para una inserción exitosa, la posición recibida debe ser 1  pos 
    //longitud(lista) + 1. Devuelve verdadero si se puede insertar correctamente y falso en caso contrario. 
    public boolean insertar(Object elemento, int pos) {
        boolean respuesta = true;
        if (pos < 1 || pos > this.longitud() + 1) {

            respuesta = false;
        } else if (pos == 1) {
            this.cabecera = new Nodo(elemento, this.cabecera);

        } else {
            //avanza hasta el nodoNuevo en posicion -1                 
            Nodo aux = this.cabecera;
            int i = 1;
            while (i < pos - 1) {
                aux = aux.getEnlace();
                i++;
            }
            //crea el nodo y lo enlaza
            Nodo nuevo = new Nodo(elemento, aux.getEnlace());
            aux.setEnlace(nuevo);
        }
        return respuesta;
    }

    // Devuelve la cantidad de elementos de la lista.
    public int longitud() {
        Nodo nuevoNodo = this.cabecera;
        int contador = 1;
        if (nuevoNodo == null) {
            contador = 0;
        } else {
            while (nuevoNodo.getEnlace() != null) {
                nuevoNodo = nuevoNodo.getEnlace();
                contador++;
            }
        }
        return contador;
    }

// Borra el nodoNuevo de la posición pos, por lo que la cantidad de elementos de la lista disminuye
//en uno. Para una eliminación exitosa, la lista no debe estar vacía y la posición recibida debe ser
//1 <= pos <= longitud(lista). Devuelve verdadero si se pudo eliminar correctamente y falso en caso contrario.
    public boolean eliminar(int pos) {
        boolean resultado = false;
        Nodo nuevoNodo;

        if (pos >= 1 && pos <= this.longitud()) {
            if (pos == 1) {
                cabecera = cabecera.getEnlace();

            } else {
                int i = 1;
                nuevoNodo = cabecera;
                while (i < pos - 1) {
                    nuevoNodo = nuevoNodo.getEnlace();

                    i++;

                }

                nuevoNodo.setEnlace(nuevoNodo.getEnlace().getEnlace());
            }
            resultado = true;

        }
        return resultado;
    }

    // Devuelve la posición en la que se encuentra la primera ocurrencia de elem dentro de la lista. En caso
    //de no encontrarlo devuelve -1.
    public int localizar(Object elemento) {
        int pos = 1;
        Nodo aux = this.cabecera;

        boolean exito = true;
        while (exito && aux != null) {
            if (aux.getElem().equals(elemento)) {
                exito = false;
            } else {
                aux = aux.getEnlace();
                pos++;
            }

        }
        if (exito == true) {
            pos = -1;
        }
        return pos;
    }

    // Devuelve el nodoNuevo de la posición pos. La precondición es que la posición sea válida.
    public Object recuperar(int pos) {
        Object elemento = null;
        boolean respuesta = false;
        int i = 1;
        Nodo nuevo = this.cabecera;

        if (pos >= 1 && pos <= this.longitud()) {
            while (i <= this.longitud() && !respuesta) {
                if (i == pos) {
                    elemento = nuevo.getElem();
                    respuesta = true;
                } else {
                    nuevo = nuevo.getEnlace();
                    i++;
                }

            }

        }
        return elemento;
    }

    // Devuelve verdadero si la lista no tiene elementos y falso en caso contrario.
    public boolean esVacia() {
        boolean respuesta = false;
        if (this.cabecera == null) {
            respuesta = true;
        }
        return respuesta;
    }

    // Devuelve una copia exacta de los datos en la estructura original, y respetando el orden de los mismos,
    //en otra estructura del mismo tipo
    @Override
    public Lista clone() {
        Lista listaClonada = new Lista();
        if (this.cabecera == null) {
            listaClonada = null;
        } else {
            Nodo aux = this.cabecera;
            Nodo aux2;
            listaClonada.cabecera = new Nodo(aux.getElem());
            aux2 = listaClonada.cabecera;
            aux = aux.getEnlace();
            while (aux != null) {
                Nodo nodoNuevo = new Nodo(aux.getElem());
                aux2.setEnlace(nodoNuevo);
                aux2 = nodoNuevo;
                aux = aux.getEnlace();
            }

        }
        return listaClonada;
    }

    // Quita todos los elementos de la lista. 
    public void vaciar() {
        this.cabecera = null;
    }

    // Crea y devuelve una cadena de caracteres formada por todos los elementos de la lista para poder
    //mostrarla por pantalla. Es recomendable utilizar este método únicamente en la etapa de prueba y luego
    //comentar el código.
    @Override
    public String toString() {
        String salida = " ";
        if (this.cabecera == null) {
            salida = "Lista vacia";
        } else {
            salida = "[";
            // se ubica al principio de la estructura
            Nodo aux = this.cabecera;

            // recorre los nodos hasta el final
            while (aux != null) {
                // agrega el nodoNuevo al string
                salida += aux.getElem();
                // avanza al siguiente nodo
                aux = aux.getEnlace();
                if (aux != null) {
                    salida += ",";
                }

            }
            salida += "]";
        }
        return salida;
    }

    //elimina todas las apariciones de elementos iguales a x,
    //haciendo un único recorrido de la estructura y sin usar otras operaciones del TDA.
    public void eliminarApariciones(Object x) {
        Nodo aux = this.cabecera;
        if (aux != null) {
            if (this.cabecera.getElem().equals(x)) {
                this.cabecera = aux.getEnlace();
                if (aux.getEnlace() != null) {
                    aux = aux.getEnlace();
                }

                while (aux.getEnlace() != null) {
                    if (aux.getEnlace().getElem().equals(x)) {
                        aux.setEnlace(aux.getEnlace().getEnlace());
                    } else {
                        aux = aux.getEnlace();
                    }
                }

            }
        }
    }
    //  modifica la lista original para que los elementos aparezcan en orden invertido, haciendo un
    //único recorrido de la estructura y sin usar estructuras auxiliares ni otras operaciones del TDA.

    public void invertir() {
        Nodo anterior = this.cabecera;
        invertirAux(this.cabecera);
        if (anterior != null) {
            anterior.setEnlace(null);
        }
    }

    private void invertirAux(Nodo nodo) {
        if (nodo != null) {
            this.cabecera = nodo;
            invertirAux(nodo.getEnlace());
            if (nodo.getEnlace() != null) {
                nodo.getEnlace().setEnlace(nodo);
            }
        }
    }

    //[0,1,2,0,3,4,5]
    //[5,1,6,7] [0,5,1,0,6,7,0]
    // Recorre la lista una sola vez y agregar al nuevo en la primera posicion y luego lo repite cada x posiciones
    public void agregarElem(Object nuevo, int x) {

        //nuevo nodoNuevo
        //x=2 posicion que quiero agregar el nuevo elem
        int cont = 0;

        if (this.cabecera != null) {
            //agrego el nodoNuevo y enlazo todo 
            this.cabecera = new Nodo(nuevo, this.cabecera);

            Nodo aux = this.cabecera;

            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
                cont++;
                if (cont == x) {
                    //creo el nodo con el elemento nuevo  y lo enlazo enseguida con el  nodo siguiente de aux
                    Nodo nodoNuevo = new Nodo(nuevo, aux.getEnlace());
                    //enlazo el nod nuevo con el anterior
                    aux.setEnlace(nodoNuevo);
                    cont = 0;
                    aux = aux.getEnlace();
                }
            }
        }
    }

    /*
    recibe un número y devuelve una lista nueva
  que contiene todos los elementos de las posiciones múltiplos de num, en el mismo orden encontrado,
  haciendo un único recorrido de las estructuras original y copia; y sin usar otras operaciones del TDA
     */
    public Lista obtenerMultiplos(int num){
        int cont = 1;
        Lista nueva = new Lista();

        Nodo aux2 = null;
        Nodo aux = this.cabecera;

        if (aux != null) {
            while (aux.getEnlace() != null) {
                if (cont % num == 0) {
                    if (nueva.cabecera == null) {
                        nueva.cabecera = new Nodo(aux.getElem(), null);
                        aux2 = nueva.cabecera;

                    } else {
                        Nodo nuevo = new Nodo(aux.getElem(), null);
                        aux2.setEnlace(nuevo);
                        aux2 = aux2.getEnlace();
                        nueva.longitud++;

                    }

                }
                cont++;
                aux = aux.getEnlace();
            }
        }
        return nueva;
    }

    /*
    Busa todas las apariciones de valor1 en la lista, e insertar un nodo con valor2 en la posicion previa.
    Si valor1 esta en posicion 1, debe insertar a valor2 antes y despues de valor1.
    */
    public void insertarPosPrevia(Object valor1, Object valor2) {

        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            if (aux.getElem().equals(valor1)) {
                this.cabecera = new Nodo(valor2, this.cabecera);
                Nodo nuevo = new Nodo(valor2, aux.getEnlace());
                aux.setEnlace(nuevo);
                aux = aux.getEnlace().getEnlace();
            }
            while (aux.getEnlace() != null) {
                if (aux.getEnlace().getElem().equals(valor1)) {
                    Nodo nuevo2 = new Nodo(valor2, aux.getEnlace());
                    aux.setEnlace(nuevo2);
                    aux = aux.getEnlace();
                }

                aux = aux.getEnlace();
            }

        }
    }
}
