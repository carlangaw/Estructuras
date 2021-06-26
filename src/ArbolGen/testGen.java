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
public class testGen {

    public static void main(String args[]) {
        testGen();
    }

    public static void testGen() {
        Lista listado = new Lista();
        ArbolGen arbolito = new ArbolGen();
        arbolito.insertar('Ñ', 0);
        arbolito.insertar('M', 'Ñ');
        arbolito.insertar('H', 'M');
        arbolito.insertar('K', 'M');
        arbolito.insertar('A', 'K');
        arbolito.insertar('G', 'Ñ');
        arbolito.insertar('F', 'G');
        arbolito.insertar('W', 'G');
        arbolito.insertar('J', 'W');

//        arbolito.insertar(3, 9);
//        arbolito.insertar(12, 15);
//        arbolito.insertar(20, 15);
//        arbolito.insertar(22, 15);
//        arbolito.insertar(23, 15);
//        arbolito.insertar(27, 15);
        System.out.println("ARBOL " + arbolito.toString());
        char a = 'J';
        char b = 'W';
        System.out.println(a + " Es hijo de " + b + " :" + arbolito.esHijoDe(a, b));

    }

}
