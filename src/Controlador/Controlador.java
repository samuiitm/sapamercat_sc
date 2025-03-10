package Controlador;

import Model.*;
import Vista.Vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controlador {
    public static Scanner scan = new Scanner(System.in);
    public static Map<Producte, Integer> carretCompra = new HashMap<>();

    public static void main(String[] args) {
        int opcio;
        do {
            Vista.mostrarMenuPrincipal();
            opcio = scan.nextInt();

            switch (opcio) {
                case 1:
                    gestionarMagatzem();
                    break;
                case 2:
                    afegirProducte();
                    break;
                case 3:
                    passarPerCaixa();
                    break;
                case 4:
                    mostrarCarret();
                    break;
                case 0:
                    Vista.mostrarMissatge("Sortint del programa...");
                    break;
                default:
                    Vista.mostrarMissatge("Opció no vàlida.");
                    break;
            }
        } while (opcio != 0);
    }

    private static void gestionarMagatzem() {
        int opcio;

        do {
            Vista.mostrarMenuGestioMagatzem();
            opcio = scan.nextInt();

            switch (opcio) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 0:
                    break;
                default:
                    Vista.mostrarMissatge("Opció no vàlida.");
                    break;
            }
        } while (opcio != 0);
    }

    private static void afegirProducte() {
        int opcio;

        do {
            Vista.mostrarMenuAfegirProducte();
            opcio = scan.nextInt();

            switch(opcio) {
                case 1:
                    Vista.mostrarMissatge("Afegir producte d'alimentació\n");

                    Vista.mostrarMissatge("Nom producte: ");
                    String nomAlimentacio = scan.next();

                    Vista.mostrarMissatge("Preu: ");
                    float preuAlimentacio = scan.nextFloat();

                    Vista.mostrarMissatge("Codi barres: ");
                    String codiAlimentacio = scan.next();

                    Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                    String caducitatAlimentacioString = scan.next();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate caducitatAlimentacio = LocalDate.parse(caducitatAlimentacioString, formatter);

                    Alimentacio alimentacio = new Alimentacio(preuAlimentacio, nomAlimentacio, codiAlimentacio, caducitatAlimentacio);
                    Model.afegirAlCarret(carretCompra, alimentacio);


                    Vista.mostrarMissatge("Producte afegit al carret.\n");
                    break;
                case 2:
                    Vista.mostrarMissatge("Afegir producte tèxtil\n");

                    Vista.mostrarMissatge("Nom producte: ");
                    String nomTextil = scan.next();

                    Vista.mostrarMissatge("Preu: ");
                    float preuTextil = scan.nextFloat();

                    Vista.mostrarMissatge("Composició: ");
                    String composicioTextil = scan.next();

                    Vista.mostrarMissatge("Codi barres: ");
                    String codiTextil = scan.next();

                    if (Model.existeixCodiBarres(carretCompra, codiTextil)) {
                        Vista.mostrarMissatge("No es pot afegir el producte. Ja existeix un producte amb el mateix codi de barres.\n");
                    } else {
                        Textil textil = new Textil(preuTextil, nomTextil, codiTextil, composicioTextil);
                        Model.afegirAlCarret(carretCompra, textil);
                        Vista.mostrarMissatge("Producte afegit al carret.\n");
                    }
                    break;
                case 3:
                    Vista.mostrarMissatge("Afegir producte electrònic\n");

                    Vista.mostrarMissatge("Nom producte: ");
                    String nomElectronic = scan.next();

                    Vista.mostrarMissatge("Preu: ");
                    float preuElectronic = scan.nextFloat();

                    Vista.mostrarMissatge("Garantia (dies): ");
                    int garantiaElectronic = scan.nextInt();

                    Vista.mostrarMissatge("Codi barres: ");
                    String codiElectronic = scan.next();

                    Electronica electronica = new Electronica(preuElectronic, nomElectronic, codiElectronic, garantiaElectronic);
                    Model.afegirAlCarret(carretCompra, electronica);

                    Vista.mostrarMissatge("Producte afegit al carret.\n");
                    break;
                case 0:
                    break;
                default:
                    Vista.mostrarMissatge("Opció no vàlida.");
                    break;
            }
        } while (opcio != 0);
    }

    private static void passarPerCaixa() {
        float totalCompra = 0.0f;

        for (Map.Entry<Producte, Integer> entry : carretCompra.entrySet()) {
            Producte producte = entry.getKey();
            int quantitat = entry.getValue();

            float preuUnitari = producte.calcularPreu();
            totalCompra += preuUnitari * quantitat;
        }

        Vista.mostrarTicketCompra(carretCompra, totalCompra);

        carretCompra.clear();
    }

    private static void mostrarCarret() {
        Vista.mostrarMissatge("Carret\n");
        Vista.mostrarCarret(carretCompra);
    }
}