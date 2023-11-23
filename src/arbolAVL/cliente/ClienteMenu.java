package arbolAVL.cliente;

import arbolAVL.graficar.BTreePrinter;
import arbolAVL.modelo.ArbolAVL;
import arbolAVL.modelo.Entero;
import java.util.Scanner;

/**
 *
 * @author Maria Alejandra Solarte Astaiza
 * @author Juan Pablo Vasquez Jaramillo
 */
public class ClienteMenu {

    public static void userMenu() {
        Scanner leer = new Scanner(System.in);
        ArbolAVL abo = new ArbolAVL();
        //20, 10, 40, 50, 90, 30, 60 y 70.
        try {
            abo.insertar(new Entero(20));
            abo.insertar(new Entero(10));
            abo.insertar(new Entero(40));

            abo.insertar(new Entero(50));
            abo.insertar(new Entero(90));
            abo.insertar(new Entero(30));
            abo.insertar(new Entero(60));
            abo.insertar(new Entero(70));

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());

        }

        System.out.println("Árbol original");
        BTreePrinter.printNode(abo.getRaiz());
        //abo.feArbol(abo.getRaiz());
        /*
        System.out.println("Elimiando");
        abo.eliminar(new Entero(50));
         */
        try {
            int opc = 0;
            do {
                System.out.println("******************************************");
                System.out.println("BIENVENIDO AL MENÚ DE: ÁRBOLES AVL");
                System.out.println("1.Insertar un nuevo nodo.");
                System.out.println("2.Eliminar un nodo existente.");
                System.out.println("3.Ver el árbol.");
                System.out.println("4.Ver factor de equilibrio del árbol.");
                System.out.println("******************************************");

                System.out.println("Ingresa una opción:");
                opc = leer.nextInt();

                switch (opc) {
                    case 1:
                        System.out.println("-Insertar un nuevo nodo.");
                        System.out.println("Ingrese el valor del nodo:");
                        int dato = leer.nextInt();
                        abo.insertar(new Entero(dato));
                        System.out.println("Nodo insertado con éxito...");
                        BTreePrinter.printNode(abo.getRaiz());

                        break;

                    case 2:
                        System.out.println("-Eliminar un nodo existente.");
                        System.out.println("Ingresa el nodo a eliminar:");
                        int datoE = leer.nextInt();
                        ArbolAVL arbol;
                        abo.eliminar(new Entero(datoE));
                        System.out.println("Nodo eliminado con éxito...");
                        BTreePrinter.printNode(abo.getRaiz());

                        break;

                    case 3:
                        System.out.println("-Ver el árbol.");
                        BTreePrinter.printNode(abo.getRaiz());
                        break;
                    case 4:
                        System.out.println("-Ver factores de equilibrio de cada nodo del árbol.");
                        abo.feArbol(abo.getRaiz());
                        System.out.println("");
                        BTreePrinter.printNode(abo.getRaiz());

                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida, intenta de nuevo...");
                }

            } while (opc != 5);
        } catch (Exception e) {
            System.out.println("Ups! algo salió mal\n" + e);
        }
    }
;
}
