/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista;

/**
 *
 * @author Carla Nuñez
 */
public class Test {

    public static void main(String[] args) {
        int respuesta;
        int elemento;
        // Creo una instancia de la clase llamada PilaInt
        Lista listaEnteros = new Lista();
        Lista listaClonada = new Lista();
        
        
        
        do{
            System.out.println("Elija una de las siguientes opciones");
            System.out.println("Si no desea elejir alguna opcion ingrese -1");
            System.out.println("1) Insertar");
            System.out.println("2) Elimiar");
            System.out.println("3) Longitud");
            System.out.println("4) La pila esta vacia? true:false");
            System.out.println("5) Vaciar arreglo");
            System.out.println("6) Clonar");  
            System.out.println("7) Convierte a String");  
            System.out.println("8) Localizar");
            System.out.println("9) Recuperar");
            System.out.println("10) Mostrar valor promedio");
            System.out.println("11) Cargar automaticamente");
            System.out.println("12) Eliminar repeticion");
            System.out.println("13) Sumar promedio");
            respuesta=TecladoIn.readLineInt();
            
            
            switch (respuesta){
                case -1:
                    break;
                case 1: // insertar
                    int pos,i,j=0;
                    System.out.println("Ingrese cuanto quiere que sea la lista");
                    i=TecladoIn.readLineInt();
                    while(j<i){
                    System.out.println("Ingrese un entero");
                    elemento=TecladoIn.readLineInt();
                    System.out.println("Ingrese una posicion entre 1 y "+ (listaEnteros.longitud()+1));
                    pos=TecladoIn.readLineInt();
                    
                    if(!listaEnteros.insertar(elemento,pos)) 
                                System.err.println("ERROR:No se pudo insertar el elemento");       
                    j++;
                    }
                    break;
                  
                case 2: // eliminar
                    System.out.println("ingrese la posicion del elemento que desea elminar");
                    pos=TecladoIn.readLineInt();
                   
                    if(!listaEnteros.eliminar(pos))
                        System.err.println("ERROR: La lista esta vacia");
                    else{
                        
                    }
                    break;
                    
                case 3: // longitud
                    System.out.println("La longitud es:[" + listaEnteros.longitud()+"]");
                    break;
                    
                case 4: // verificar si la lista esta vacia
                    if( listaEnteros.esVacia())
                        System.out.println("La lista no tiene elementos");
                    else
                    {
                        System.out.println("La lista contiene elementos");
                    }
                            
                break;
                case 5: // vaciar lista
                   listaEnteros.vaciar();
                    break;
                case 6: // clonar lista
                    listaClonada = listaEnteros.clone();
                    System.out.println("La pila clonada contiene [" +listaClonada.toString()+"]");
                    break;
                               
                case 7:
                    System.out.println("La lista contiene [" +listaEnteros.toString()+"]");
                    break;
                case 8:
                    int localizado;
                    System.out.println("Ingrese el elemento que desea localizar");
                    elemento=TecladoIn.readLineInt();
                    localizado=listaEnteros.localizar(elemento);
                    if(localizado==-1){
                        System.out.println("No se pudo localizar el elemento");
                    }
            else{
                        //localizar
                System.out.println("El elemento se encuentra en la posicion:" +localizado);
                    }
                    break;
                case 9:
                    //lrecuperar
                    System.out.println("Ingrese la posicion del elemento que desea recuperar entre 1 y " +(listaEnteros.longitud()));
                    pos=TecladoIn.readLineInt();
                    System.out.println(listaEnteros.recuperar(pos));
                    break;
                case 10:
                    listaEnteros.insertarPromedio();
                    break;
                case 11:// cargar atomaticamente
                    listaEnteros.insertar(7, 1);
                    listaEnteros.insertar(4, 2);
                    listaEnteros.insertar(1, 3);
                    listaEnteros.insertar(9, 4);
                    listaEnteros.insertar(2, 5);
                    listaEnteros.insertar(3, 6);
                    
                    
                    break;
                case 12:
                    System.out.println("Ingrese un elemento del cual desea eliminar la repeticion");
                    elemento=TecladoIn.readLineInt();
                    listaEnteros.eliminarRepetido(elemento);
                    System.out.println(listaEnteros.toString());
                    break;
                case 13:
                    System.out.println("Ingrese una cantidad a calcular");
                    elemento=TecladoIn.readLineInt();
                    listaEnteros.calcularPromedio(elemento);
                    break;
                default:
                    System.err.println("ERROR: numero mal ingresado. Saliendo del programa");
                    respuesta = -1;
                    break;
           
            }
            
        
        
        } while (respuesta != -1);

        
    }
    
        
       
}

