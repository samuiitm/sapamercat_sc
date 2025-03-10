package Vista;

import Model.Producte;

import java.time.LocalDate;
import java.util.Map;

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

    public static void mostrarMenuGestioMagatzem() {
        System.out.println("------------\n-- GESTIO MAGATZEM ---\n------------");
        System.out.println("1) Caducitat\n" +
                "2) Tiquets de compra\n" +
                "3) Composició tèxtil\n" +
                "0) Tornar");
    }

    public static void mostrarMenuAfegirProducte() {
        System.out.println("---------------\n-- PRODUCTE ---\n---------------");
        System.out.println("1) Alimentació\n" +
                           "2) Tèxtil\n" +
                           "3) Electrònica\n" +
                           "0) Tornar");
    }

    public static void mostrarTicketCompra(Map<Producte, Integer> carretCompra, float totalCompra) {
        LocalDate avui = LocalDate.now();
        System.out.println("--------------------------------\n" +
                "SAPAMERCAT\n" +
                "--------------------------------\n" +
                "Data: " + avui + "\n" +
                "--------------------------------");

        carretCompra.forEach((producte, quantitat) -> {
            float preuUnitari = producte.calcularPreu();
            float preuProducte = preuUnitari * quantitat;
            System.out.printf("%s\t\t%d  %.2f€ \t%.2f€\n", producte.getNom(), quantitat, preuUnitari, preuProducte);
        });

        System.out.println("--------------------------------");
        System.out.printf("Total de la compra: %.2f€\n", totalCompra);
        System.out.println("--------------------------------");
    }

    public static void mostrarCarret(Map<Producte, Integer> carretCompra) {
        if (carretCompra.isEmpty()) {
            System.out.println("El carret està buit.");
        } else {
            System.out.println("Productes en el carret:");
            for (Map.Entry<Producte, Integer> entry : carretCompra.entrySet()) {
                Producte producte = entry.getKey();
                int quantitat = entry.getValue();
                System.out.println(producte.getNom() + " --> " + quantitat);
            }
        }
        System.out.println();
    }

    public static void mostrarMissatge(String missatge) {
        System.out.print(missatge);
    }
}
