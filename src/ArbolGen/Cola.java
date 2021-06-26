
package ArbolGen;



/**
 *
 * @author Carla Nuñez FAI-1631 / Mauricio Sawicki FAI-2256
 */
public class Cola {
    

    private Nodo fin;
    private Nodo frente;
    
    // Crea y devuelve una cola vacía
    public Cola() {
        this.fin = null;
        this.frente = null;
    }

    public boolean poner(Object elemento) {
        // Pone el elemento al final de la cola. Devuelve verdadero si el elemento se pudo agregar en la estructura
        //y falso en caso contrario.
        Nodo aux;
        boolean respuesta = true;
        aux = new Nodo(elemento);
        if (frente == null) {
            this.frente = aux;
            this.fin = frente;
        } else {
            fin.setEnlace(aux);
            fin = aux;
        }
        return respuesta;
    }

    public boolean sacar() {
        // Saca el elemento que está en el frente de la cola. Devuelve verdadero si el elemento se pudo sacar (la
        //estructura no estaba vacía) y falso en caso contrario.
        boolean respuesta = true;
        if (this.frente == null) {
            respuesta = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;

            }
        }
        return respuesta;
    }

    public Object obtenerFrente() {
        // Devuelve el elemento que está en el frente. Precondición: la cola no está vacía.
        Object elemento;
        if (!this.esVacia()) {
            elemento = this.frente.getElem();
        } else {
            elemento = null;
        }
        return elemento;

    }
    
    // Saca todos los elementos de la estructura 
    public void vaciar() {
        this.fin = null;
        this.frente = null;
    }

    
    // Devuelve verdadero si la cola no tiene elementos y falso en caso contrario
    public boolean esVacia() {
        boolean respuesta = false;
        if (this.frente == null && this.fin == null) {
            respuesta = true;
        }
        return respuesta;
    }


    // Devuelve una copia exacta de los datos en la estructura original, y respetando el orden de los mismos,
    //en otra estructura del mismo tipo
    @Override
    public Cola clone() {
        Cola colaClon = new Cola();
        Nodo aux1 = this.frente;
        //Creo el primer nodo de la cola auxiliar
        colaClon.frente = new Nodo(aux1.getElem(), null);
        //Me muevo al 2do nodo de la cola original
        aux1 = aux1.getEnlace();

        Nodo aux2 = colaClon.frente;

        while (aux1 != null) {
            //Crea el nodo y lo enlaza a continuacion de aux2, se repite n-1 veces
            aux2.setEnlace(new Nodo(aux1.getElem(), null));
            aux2 = aux2.getEnlace();
            aux1 = aux1.getEnlace();
        }
        colaClon.fin = aux2;

        return colaClon;
    }

    
    // Crea y devuelve una cadena de caracteres formada por todos los elementos de la cola para poder
    //mostrarla por pantalla. Es recomendable utilizar este método únicamente en la etapa de prueba y luego
    //comentar el código.
    @Override
    public String toString() {
        String s;

        if (this.esVacia()) {
            s = "La cola esta vacia";
        } else {
            s = "[";
            Nodo aux = this.frente;
            while (aux != null) {
                //agrega el texto del elemento que tiene el nodo y avanza
                s += " " + aux.getElem();
                aux = aux.getEnlace();
            }
            s += "]";
        }
        return s;
    }
}


