/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArbolBB;

/**
 *
 * @author Carla Nuñez
 */
public class testArbolb {

    public static void main(String args[]) {
        testArbolb();
    }

    public static void testArbolb() {
        ArbolBinBus arbolito = new ArbolBinBus();
        arbolito.insertar('F');
        arbolito.insertar('B');
        arbolito.insertar('A');
        arbolito.insertar('D');
        arbolito.insertar('C');
        arbolito.insertar('E');
        arbolito.insertar('G');
        arbolito.insertar('I');
        arbolito.insertar('H');

        System.out.println("Arbol" + arbolito.toString());
        System.out.println("Concatenacion " + arbolito.concatenarPosordenDesde('B', 4));

    }
}
