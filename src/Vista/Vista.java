package Vista;

import Model.Producte;

import java.util.ArrayList;

public class Vista {
    public static void mostrarMenuPrincipal() {
        System.out.println("BENVINGUT AL SAPAMERCAT");
        System.out.println("------------\n-- INICI ---\n------------");
        System.out.println("1) Gestionar magatzem\n" +
                           "2) Introduir producte\n" +
                           "3) Passar per caixa\n" +
                           "4) Mostrar carret de compra\n" +
                           "0) Acabar");
    }

    public static void mostrarMenuAfegirProducte() {
        System.out.println("---------------\n-- PRODUCTE ---\n---------------");
        System.out.println("1) Alimentació\n" +
                           "2) Tèxtil\n" +
                           "3) Electrònica\n" +
                           "0) Tornar");
    }

    public static void mostrarCarret(ArrayList<Producte> carretCompra) {
        carretCompra.forEach(System.out::println);
        System.out.println();
    }

    public static void mostrarMissatge(String missatge) {
        System.out.print(missatge);
    }
}
