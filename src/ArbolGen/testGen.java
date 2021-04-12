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
        ArbolGen arbolito = new ArbolGen();
        arbolito.insertar(10, 0);
        arbolito.insertar(25, 10);
        arbolito.insertar(33, 10);
        arbolito.insertar(54, 10);
        arbolito.insertar(15, 25);
        arbolito.insertar(47, 33);
        arbolito.insertar(18, 33);
        arbolito.insertar(63, 33);
        arbolito.insertar(9, 18);
        System.out.println(arbolito.misterioso(9).toString());

    }

}
