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
public class testArbolBB {

    static String sOk = "\u001B[32m OK! \u001B[0m", sErr = " \u001B[31m ERROR \u001B[0m";
    public static final String NEGRO = "\u001B[30m";
    public static final String ROJO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARILLO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLANCO = "\u001B[37m";

    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        ArbolBinBus arbol = new ArbolBinBus();
        System.out.println("********************************");
        System.out.println("*      Insercion basica        *");
        System.out.println("********************************");

        System.out.println("Checkeo si es vacio " + ((arbol.esVacio()) ? sOk : sErr));
        System.out.println("Intento vaciar arbol vacio ");
        arbol.vaciar();
        System.out.println("Inserto el 10 en raiz " + ((arbol.insertar(10)) ? sOk : sErr));
        System.out.println("Inserto el 6 " + ((arbol.insertar(6)) ? sOk : sErr));
        System.out.println("Inserto el 4" + ((arbol.insertar(4)) ? sOk : sErr));
        System.out.println("Inserto el 5 " + ((arbol.insertar(5)) ? sOk : sErr));
        System.out.println("Inserto el 13 " + ((arbol.insertar(13)) ? sOk : sErr));
        System.out.println("Inserto el 20 " + ((arbol.insertar(20)) ? sOk : sErr));
        System.out.println("Inserto el 9  " + ((arbol.insertar(9)) ? sOk : sErr));
        System.out.println("Inserto el 10 " + ((arbol.insertar(10)) ? sOk : sErr));
        System.out.println("Inserto el 15 " + ((arbol.insertar(15)) ? sOk : sErr));
        System.out.println("\n toString()  deberia dar: \n\n \t \t \t      10"
                + " \n \t \t 6 \t \t \t 13"
                + " \n \t 4 \t \t 9 \t \t \t 20 "
                + " \n \t\t 5 \t \t \t 15"
                + "\t \t --> \t \t" + arbol.toString());

        System.out.println(ANSI_YELLOW_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("*      Muestra Clon    *");
        System.out.println("********************************");
        ArbolBinBus clon = new ArbolBinBus();
        clon = arbol.clone();
        System.out.println(clon.toString());
        System.out.println(ANSI_RED_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("*      Listar Rango  *");
        System.out.println("********************************");
        System.out.println("Si el elemento minimo es 4 y el maximo 20 --> Deberia mostrar [4,5,6,9,10,13,15,20]");
        System.out.println("Lista Rango: " + arbol.listarRango(4, 20));
        System.out.println("Si el elemento minimo es 9 y el maximo 15 --> Deberia mostrar [9,10,13,15]");
        System.out.println("Lista Rango: " + arbol.listarRango(9, 15));
        System.out.println("Si el elemento minimo es 25 y el maximo 80 --> Deberia mostrar [Lista Vacia]");
        System.out.println("Lista Rango: " + arbol.listarRango(25, 80));
        System.out.println(ANSI_BLUE_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("*      Muestra el elemento Minimo      *");
        System.out.println("********************************");
        System.out.println("Deberia mostrar 4 " + "---> " + arbol.minElem());
        System.out.println("********************************");
        System.out.println("*      Muestra el elemento Maximo     *");
        System.out.println("********************************");
        System.out.println("Deberia Mostrar 20" + "---> " + arbol.maxElem());
        System.out.println(ANSI_PURPLE_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("*      Metodo Pertenece  *");
        System.out.println("********************************");
        System.out.println("Consulto si el elemento 10 pertenece al arbol deberia dar OK " + ((arbol.pertenece(10)) ? sOk : sErr));
        System.out.println("Consulto si el elemento 21 pertenece al arbol deberia dar ERROR " + ((arbol.pertenece(21)) ? sOk : sErr));
        System.out.println("Consulto si el elemento 15 pertenece al arbol deberia dar OK " + ((arbol.pertenece(15)) ? sOk : sErr));
        System.out.println("Consulto si el elemento 5 pertenece al arbol deberia dar OK " + ((arbol.pertenece(5)) ? sOk : sErr));
        System.out.println("Consulto si el elemento 100 pertenece al arbol deberia dar ERROR " + ((arbol.pertenece(100)) ? sOk : sErr));
        System.out.println(ANSI_GREEN_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("*      Eliminar Minimo       *");
        System.out.println("********************************");
        System.out.println("Deberia eliminar el numero 4" + ((arbol.eliminarMin() ? sOk : sErr)));

        System.out.println("Deberia eliminar el numero 5" + ((arbol.eliminarMin() ? sOk : sErr)));

        System.out.println("Deberia eliminar el numero 6" + ((arbol.eliminarMin() ? sOk : sErr)));
        System.out.println(arbol.toString());
        System.out.println(ANSI_CYAN_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("*      Eliminar Maximo      *");
        System.out.println("********************************");
        System.out.println("Deberia eliminar el numero 20" + ((arbol.eliminarMax() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 15" + ((arbol.eliminarMax() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 13" + ((arbol.eliminarMax() ? sOk : sErr)));
        System.out.println(arbol.toString());
        System.out.println(ANSI_PURPLE_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("*      Muestra el elemento Minimo  despues de eliminar    *");
        System.out.println("********************************");
        System.out.println("Deberia mostrar 9 " + "---> " + arbol.minElem());
        System.out.println("********************************");
        System.out.println("*      Muestra el elemento Maximo despues de eliminar *");
        System.out.println("********************************");
        System.out.println("Deberia Mostrar 10" + "---> " + arbol.maxElem());
        System.out.println(ANSI_WHITE_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("   Eliminar Minimo con el clon ");
        System.out.println("********************************");
        System.out.println("Deberia eliminar el numero 4" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 5" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 6" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 9" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 10" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 13" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 15" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia eliminar el numero 20" + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("Deberia TIRAR ERROR POR ARBOL VACIO " + ((clon.eliminarMin() ? sOk : sErr)));
         System.out.println(clon.toString());
        System.out.println("********************************");
        System.out.println("   Eliminar Maximo con el clon ");
        System.out.println("********************************");
        System.out.println("Deberia TIRAR ERROR POR ARBOL VACIO" + ((clon.eliminarMax() ? sOk : sErr)));
        

        System.out.println(clon.toString());
        System.out.println(ANSI_CYAN_BACKGROUND + "-------------------------------------------------------------------------------------------------------"
                + "-------------------------------------------------------------------------------------------" + RESET);
        System.out.println("********************************");
        System.out.println("   Vacio el arbol y el Clon  ");
        System.out.println("********************************");
        arbol.vaciar();
        clon.vaciar();
        System.out.println("Arbol -->" + arbol.toString());
        System.out.println("Clon -->" + clon.toString());
        System.out.println("SI TRATO DE ELIMINAR EL MINIMO DEBE DAR ERROR --> " + ((arbol.eliminarMin() ? sOk : sErr)));
        System.out.println("SI TRATO DE ELIMINAR EL MAXIMO DEBE DAR ERROR -->" + ((arbol.eliminarMax() ? sOk : sErr)));
        System.out.println("SI TRATO DE ELIMINAR EL MINIMO EN EL CLON DEBE DAR ERROR --> " + ((clon.eliminarMin() ? sOk : sErr)));
        System.out.println("SI TRATO DE ELIMINAR EL MAXIMO EN EL CLON DEBE DAR ERROR --> " + ((clon.eliminarMax() ? sOk : sErr)));

    }
}
