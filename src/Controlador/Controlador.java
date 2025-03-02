package Controlador;

import Model.*;
import Vista.Vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {
    public static Scanner scan = new Scanner(System.in);
    public static ArrayList<Producte> carretCompra = new ArrayList<>();

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

                    carretCompra.add(new Alimentacio(preuAlimentacio, nomAlimentacio, codiAlimentacio, caducitatAlimentacio));

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

                    carretCompra.add(new Textil(preuTextil, nomTextil, codiTextil, composicioTextil));

                    Vista.mostrarMissatge("Producte afegit al carret.\n");
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

                    carretCompra.add(new Electronica(preuElectronic, nomElectronic, codiElectronic, garantiaElectronic));

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

    }

    private static void mostrarCarret() {

    }
}