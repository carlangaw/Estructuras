/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiles;
import ArbolBB.ArbolBinBus;

/**
 *
 * @author Carla Nuñez
 */
public class TestingArbolBB {
     public static void main(String[] args) {
         ArbolBinBus arbol = new ArbolBinBus();
         arbol.insertar(8);
         arbol.insertar(3);
         arbol.insertar(1);
         arbol.insertar(6);
         arbol.insertar(4);
         arbol.insertar(7);
         arbol.insertar(10);
         arbol.insertar(14);
         arbol.insertar(13);
         
         System.out.println("Arbol" +arbol.toString());
         System.out.println(arbol.amplitudSubarbol(10));
     }
}
