/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolHeap;

/**
 *
 * @author Carla Nuñez
 */
public class ArbolHeap {

    private static final int TAM = 10;
    private Comparable[] arregloHeap;
    private int ultimo=0;

    public ArbolHeap() {
        this.arregloHeap = new Comparable[TAM];
        
    }

    public boolean insertar(Comparable elemento) {
        boolean exito = false;
        if (this.ultimo + 1 < TAM) {
            this.ultimo++;
            this.arregloHeap[this.ultimo] = elemento;
            hacerSubir(this.ultimo);
            exito = true;
        }
        return exito;
    }

    public Comparable recuperarCima() {
        //devuelve el elemento que está en la raíz (cima del montículo). Precondición: el árbol no está vacío
        //(si está vacío no se puede asegurar el funcionamiento de la operación).
        Comparable res = null;
        if (!this.esVacio()) {
            res = this.arregloHeap[1];
        }
        return res;

    }

    public boolean eliminarCima() {
        // elimina el elemento de la raíz (o cima del montículo) según el algoritmo que se explicará en la
        //siguiente sección. Si la operación termina con éxito devuelve verdadero y falso si el árbol estaba vacío
        boolean respuesta = true;
        if (this.ultimo == 0) {
            respuesta = false;
        } else {
            this.arregloHeap[1] = this.arregloHeap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            respuesta = true;
        }
        return respuesta;

    }


    private void hacerBajar(int posPadre) {
        int posH;
        Comparable aux = this.arregloHeap[posPadre];
        boolean salir = false;
        while (!salir) {
            posH = posPadre * 2;
            if (posH <= this.ultimo) {
                // aux tiene al menos un hijo (izq) y lo considera menor
                if (posH < this.ultimo) {
                    // hijo menor tiene hermano derecho
                    // el hijo derecho es menor que los dos
                    if (this.arregloHeap[posH + 1].compareTo(this.arregloHeap[posH]) < 0)
                        posH++;
                }
                // compara al hijo menor con el padre
                if (this.arregloHeap[posH].compareTo(aux) < 0) {
                    // el hijo es menor que el padre, los intercambia
                    this.arregloHeap[posPadre] = this.arregloHeap[posH];
                    this.arregloHeap[posH] = aux;
                    posPadre = posH;
                } else {
                    // el padre es menor que sus hijos, está bien ubicado
                    salir = true;
                }
            } else {
                salir = true;
            }
        }
    }
private void hacerSubir(int posHijo) {
        int posP;
        Comparable temp = this.arregloHeap[posHijo];
        boolean seguir = true;
        while (seguir) {
            posP = posHijo / 2;
            if (posP >= 1) {
                if (this.arregloHeap[posP].compareTo(temp) > 0) {
                    this.arregloHeap[posHijo] = this.arregloHeap[posP];
                    this.arregloHeap[posP] = temp;
                    posHijo = posP;
                } else {
                    seguir = false;
                }
            } else {
                seguir = false;
            }

        }
    }
    public boolean esVacio() {
        // devuelve falso si hay al menos un elemento cargado en la tabla y verdadero en caso contrario.
        boolean res = false;
        if (this.ultimo == 0) {
            res = true;
        }
        return res;
    }

    @Override
 public String toString() {
        String s = "";
        int izq, der;
        for (int i = 1; i <= this.ultimo; i++) {
            System.out.print("Nodo: " + this.arregloHeap[i] + " ");
            izq = i * 2;
            der = izq + 1;
            if (izq <= this.ultimo && this.arregloHeap[izq] != null) {
                System.out.print("HI: " + this.arregloHeap[izq]);
            } else {
                System.out.print("HI: -");
            }
            System.out.print("\t");
            if (der <= this.ultimo && this.arregloHeap[der] != null) {
                System.out.println("HD: " + this.arregloHeap[der]);
            } else {
                System.out.println("HD: -");
            }
        }
        return s;
    }
    @Override
    public ArbolHeap clone() {
        //Hace una copia exacta dedl arbolHeap original
        ArbolHeap clonado = new ArbolHeap();
        if (!esVacio()) {
            clonado.arregloHeap = this.arregloHeap.clone();
            clonado.ultimo = this.ultimo;
        }
        return clonado;
    }

}
