/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

import ArbolGen.*;


/**
 *
 * @author Carla Nuñez
 */
public class Lista {

    private Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public boolean insertar(Object elemento, int pos) {
        boolean respuesta = true;
        if (pos < 1 || pos > this.longitud() + 1) {

            respuesta = false;
        } else if (pos == 1) {
            this.cabecera = new Nodo(elemento, this.cabecera);

        } else {
            //avanza hasta el elemento en posicion -1                 
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

    public boolean eliminar(int pos) {
        boolean resultado = true;
        Nodo nuevoNodo;
        if (this.cabecera == null) {
            resultado = false;
        } else if (pos == 1) {
            cabecera = cabecera.getEnlace();
        } else {
            int i = 1;
            nuevoNodo = cabecera;
            while (i < pos - 1) {
                nuevoNodo = nuevoNodo.getEnlace().getEnlace();

                i++;

            }
            nuevoNodo.setEnlace(cabecera);
        }
        return resultado;
    }

    public int localizar(Object elemento) {
        int pos = 1;
        Nodo aux = this.cabecera;

        boolean exito = true;
        while (exito && aux != null) {
            if (aux.getElemento().equals(elemento)) {
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

    public Object recuperar(int pos) {
        int elemento = Integer.MAX_VALUE + 1;
        int i = 1;
        Nodo nuevo = this.cabecera;
//        Nodo nuevo=this.cabecera;
        if (pos >= 1 && pos < this.longitud()) {
            while (i < this.longitud()) {
                if (i == pos) {
                    elemento = (int) nuevo.getElemento();
                } else {
                    nuevo = nuevo.getEnlace();
                    i++;
                }
            }

        }
        return elemento;
    }

    public boolean esVacia() {
        boolean respuesta = false;
        if (this.cabecera == null) {
            respuesta = true;
        }
        return respuesta;
    }

    @Override
    public Lista clone() {
        Lista listaClonada = new Lista();
        if (this.cabecera == null) {
            listaClonada = null;
        } else {
            Nodo aux = this.cabecera;
            Nodo aux2;
            listaClonada.cabecera = new Nodo(aux.getElemento());
            aux2 = listaClonada.cabecera;
            aux = aux.getEnlace();
            while (aux != null) {
                Nodo nodoNuevo = new Nodo(aux.getElemento());
                aux2.setEnlace(nodoNuevo);
                aux2 = nodoNuevo;
                aux = aux.getEnlace();
            }

        }
        return listaClonada;
    }

    public void vaciar() {
        this.cabecera = null;
    }

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
                // agrega el elemento al string
                salida += aux.getElemento();
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

    public void insertarPromedio() {
        int i, suma = 0, div = 0, promedio;
        Nodo aux = this.cabecera;

        while (aux.getEnlace() != null) {
            suma = (int) aux.getElemento() + suma;
            div = div + 1;
            aux = aux.getEnlace();

        }
        suma = (int) aux.getElemento()+ suma;
        div = div + 1;
        promedio = suma / div;
        if (aux.getEnlace() == null) {
            Nodo aux2 = new Nodo(promedio);
            aux.setEnlace(aux2);
        }

    }

    public void eliminarRepetido(int x) {
        Nodo aux = this.cabecera;
        while (aux.getEnlace() != null) {

            if (this.cabecera.getElemento().equals(x)) {
                this.cabecera = aux.getEnlace();
                aux = aux.getEnlace();
            } else if (aux.getEnlace().getElemento().equals(x)) {
                aux.setEnlace(aux.getEnlace().getEnlace());
            } else {
                aux = aux.getEnlace();
            }

        }
        if (this.cabecera.getEnlace() == null) {
            if (this.cabecera.getElemento().equals(x)) {
                this.cabecera = null;
            }
        }
    }

    public void calcularPromedio(int cantidad) {
        Nodo aux;
        int contador = 1;
        int promedio = 0;
        aux = this.cabecera;
        while (aux.getEnlace() != null) {
            if (contador < cantidad) {
                promedio = promedio + (int) aux.getElemento();
                contador++;
            } else {
                promedio = promedio + (int) aux.getElemento();
                Nodo aux2;
                aux2 = new Nodo(promedio);
                aux2.setEnlace(aux.getEnlace());
                aux.setEnlace(aux2);
                promedio = 0;
                contador = 1;
                aux = aux.getEnlace();

            }
            aux = aux.getEnlace();

        }
        if (promedio != 0) {
            promedio = promedio + (int) aux.getElemento();
            Nodo aux3 = new Nodo(promedio);
            aux3.setEnlace(aux.getEnlace());
            aux.setEnlace(aux3);
            aux3.setElemento(promedio);

        }
    }

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
}


