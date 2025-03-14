package Controlador;

import Excepcions.*;
import Model.*;
import Vista.Vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Controlador {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Model.inicialitzarProductes();

        int opcio = -1;

        do {
            try {
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
                        throw new EnumFailException("Entrada no vàlida.");
                }
            } catch (EnumFailException e) {
                Vista.mostrarMissatge(e.getMessage() + "\n\n");
            }
        } while (opcio != 0);
    }

    private static void gestionarMagatzem() {
        int opcio = -1;

        do {
            try {
                Vista.mostrarMenuGestioMagatzem();
                opcio = scan.nextInt();

                switch (opcio) {
                    case 1:
                        Vista.mostrarElementsCaducitat(Model.filtrarCaducitat());
                        break;
                    case 2:
                        Vista.mostrarHistorialTiquets(Model.getTiquetsCompra());
                        break;
                    case 3:
                        Vista.mostrarElementsTextil(Model.filtrarComposicio());
                        break;
                    case 0:
                        break;
                    default:
                        throw new EnumFailException("Entrada no vàlida");
                }
            } catch (EnumFailException e) {
                Vista.mostrarMissatge(e.getMessage() + "\n\n");
            }
        } while (opcio != 0);
    }

    private static void afegirProducte() {
        int opcio = -1;

        do {
            try {
                Vista.mostrarMenuAfegirProducte();
                opcio = scan.nextInt();

                switch (opcio) {
                    case 1:
                        Vista.mostrarMissatge("Afegir producte d'alimentació\n");

                        Vista.mostrarMissatge("Nom producte: ");
                        String nomAlimentacio = scan.next();
                        Vista.mostrarMissatge("Preu: ");
                        float preuAlimentacio = scan.nextFloat();
                        Vista.mostrarMissatge("Codi barres: ");
                        String codiAlimentacio = scan.next();

                        Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                        LocalDate caducitatAlimentacio = null;
                        while (caducitatAlimentacio == null) {
                            try {
                                String caducitatAlimentacioString = scan.next();
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                caducitatAlimentacio = LocalDate.parse(caducitatAlimentacioString, formatter);
                            } catch (DateTimeParseException e) {
                                Vista.mostrarMissatge("La data ha de ser en format 'dd/MM/yyyy'. Intenta-ho de nou.\n");
                                Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                            }
                        }

                        Model.afegirProducteCarret(preuAlimentacio, nomAlimentacio, codiAlimentacio, caducitatAlimentacio);
                        Vista.mostrarMissatge("Producte afegit al carret.\n\n ");
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

                        Model.afegirProducteCarret(preuTextil, nomTextil, composicioTextil, codiTextil);
                        Vista.mostrarMissatge("Producte afegit al carret.\n\n");
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

                        Model.afegirProducteCarret(nomElectronic, preuElectronic, garantiaElectronic, codiElectronic);
                        Vista.mostrarMissatge("Producte afegit al carret.\n\n");
                        break;

                    case 0:
                        break;

                    default:
                        throw new EnumFailException("Opció no vàlida.");
                }
            } catch (EnumFailException e) {
                Vista.mostrarMissatge(e.getMessage() + "\n\n");
            }
        } while (opcio != 0);
    }

    private static void passarPerCaixa() {
        if (Model.carretCompra.isEmpty()) {
            Vista.mostrarMissatge("El carret està buit.");
            return;
        }

        float totalCompra = 0.0f;
        for (Map.Entry<Producte, Integer> entry : Model.carretCompra.entrySet()) {
            Producte producte = entry.getKey();
            int quantitat = entry.getValue();
            float preuUnitari = producte.calcularPreu();
            totalCompra += preuUnitari * quantitat;
        }

        Map<Producte, Integer> copiaCarret = new HashMap<>(Model.carretCompra);
        Tiquet tiquet = new Tiquet(LocalDate.now(), copiaCarret, totalCompra);
        Model.tiquetsCompra.add(tiquet);

        Vista.mostrarTicketCompra(copiaCarret, totalCompra);
        Model.carretCompra.clear();
    }

    private static void mostrarCarret() {
        Vista.mostrarMissatge("Carret\n");
        Vista.mostrarCarret(Model.carretCompra);
    }
}