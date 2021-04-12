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
public class Nodo {
    private Object elemento;
    private Nodo enlace;
    
public Nodo (Object elemento, Nodo enlace){
     this.elemento=elemento;
    this.enlace=enlace;
    
}
public Nodo (Object elemento){
    this.elemento=elemento;
}
public Nodo (Nodo enlace){
    this.enlace=enlace;
    
}
public Object getElemento(){
    return this.elemento;
    
}
public Nodo getEnlace(){
    return this.enlace;
}
public void setElemento(Object elemento){
    this.elemento=elemento;
}
public void setEnlace(Nodo enlace){
    this.enlace=enlace;
}
}
