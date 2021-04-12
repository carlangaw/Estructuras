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
public class Nodo {
    Object elemento;
    Nodo enlace;

 public Nodo(Object elemento){
      this.elemento=elemento;
      this.enlace=null;
    }
public Nodo (){
    this.elemento=null;
}
public Nodo(Object elemento, Nodo enlace) {
    this.elemento = elemento;
    this.enlace = enlace;
}

    
public Object getElem(){
    return elemento;
}
public void setElemento (Object elemento){
     this.elemento = elemento;
}
public Nodo getEnlace (){
    return enlace;   
}
public void setEnlace(Nodo enlace) {
    this.enlace = enlace;
}

    void setEnlace(int promedio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

