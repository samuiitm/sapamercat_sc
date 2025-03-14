package Vista;

import Model.Alimentacio;
import Model.Producte;
import Model.Textil;
import Model.Tiquet;

import java.time.LocalDate;
import java.util.List;
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
        System.out.println("----------------------\n-- GESTIO MAGATZEM ---\n----------------------");
        System.out.println("1) Caducitat\n" +
                "2) Tiquets de compra\n" +
                "3) Composició tèxtil\n" +
                "0) Tornar");
    }

    public static void mostrarHistorialTiquets(List<Tiquet> historialTiquets) {
        if (historialTiquets.isEmpty()) {
            System.out.println("No s'ha guardat cap tiquet de compra");
            return;
        }

        System.out.println("------------------------------------------\n----- HISTORIAL DE TIQUETS DE COMPRA -----\n------------------------------------------");
        for (Tiquet tiquet : historialTiquets) {
            System.out.println("Data de compra: " + tiquet.getDataCompra());
            for (Map.Entry<Producte, Integer> entry : tiquet.getProductesComprats().entrySet()) {
                System.out.printf("%s\t\t\tx%d\t %.2f€\n", entry.getKey().getNom(), entry.getValue(), entry.getKey().calcularPreu());
            }
            System.out.printf("Total: \t\t%.2f€\n", tiquet.getTotalCompra());
            System.out.println("------------------------------------------");
        }
    }

    public static void mostrarMenuAfegirProducte() {
        System.out.println("---------------\n-- PRODUCTE ---\n---------------");
        System.out.println("1) Alimentació\n" +
                           "2) Tèxtil\n" +
                           "3) Electrònica\n" +
                           "0) Tornar");
    }

    public static void mostrarElementsCaducitat(List<Alimentacio> alimentacio) {
        alimentacio.forEach(System.out::println);
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
            System.out.printf("%s\t\tx%d  %.2f€ \t%.2f€\n", producte.getNom(), quantitat, preuUnitari, preuProducte);
        });

        System.out.println("--------------------------------");
        System.out.printf("Total de la compra: %.2f€\n", totalCompra);
        System.out.println("--------------------------------");
    }

    public static void mostrarElementsTextil(List<Textil> textil) {
        textil.forEach(System.out::println);
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
