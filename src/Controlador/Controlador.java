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
        // Inicialitza els productes que ja existeixen al sistema
        Model.inicialitzarProductes();

        int opcio = -1;

        do {
            try {
                // Mostra el menú principal i espera que l'usuari triï una opció
                Vista.mostrarMenuPrincipal();
                opcio = scan.nextInt();

                switch (opcio) {
                    case 1:
                        // Gestionar el magatzem
                        gestionarMagatzem();
                        break;
                    case 2:
                        // Afegir un producte al carret
                        afegirProducte();
                        break;
                    case 3:
                        // Passar per caixa i pagar
                        passarPerCaixa();
                        break;
                    case 4:
                        // Veure què hi ha al carret
                        mostrarCarret();
                        break;
                    case 0:
                        // Sortir del programa
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
                // Mostra el menú de gestió del magatzem
                Vista.mostrarMenuGestioMagatzem();
                opcio = scan.nextInt();

                switch (opcio) {
                    case 1:
                        // Veure productes caducats o a punt de caducar
                        Vista.mostrarElementsCaducitat(Model.filtrarCaducitat());
                        break;
                    case 2:
                        // Veure l'historial de tiquets de compra
                        Vista.mostrarHistorialTiquets(Model.getTiquetsCompra());
                        break;
                    case 3:
                        // Veure els productes tèxtils filtrats per composició
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
                // Mostra el menú per afegir un producte
                Vista.mostrarMenuAfegirProducte();
                opcio = scan.nextInt();

                switch (opcio) {
                    case 1:
                        try {
                            Vista.mostrarMissatge("Afegir producte d'alimentació\n");

                            Vista.mostrarMissatge("Nom producte: ");
                            String nomAlimentacio = scan.next();
                            if (nomAlimentacio.length() > 30)
                                throw new LimitCaractersException("El nom del producte no pot superar els 30 caràcters");

                            Vista.mostrarMissatge("Preu: ");
                            float preuAlimentacio = scan.nextFloat();
                            if (preuAlimentacio < 0) throw new NegatiuException("El preu no pot ser negatiu.");

                            Vista.mostrarMissatge("Codi barres: ");
                            String codiAlimentacio = scan.next();

                            Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                            LocalDate caducitatAlimentacio = null;

                            while (caducitatAlimentacio == null) {
                                try {
                                    String caducitatAlimentacioString = scan.next();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    caducitatAlimentacio = LocalDate.parse(caducitatAlimentacioString, formatter);
                                    DataCaducitatException.verificarDataCaducitat(caducitatAlimentacio);
                                } catch (DateTimeParseException e) {
                                    Vista.mostrarMissatge("La data ha de ser en format 'dd/MM/yyyy'. Intenta-ho de nou." + "\n");
                                    Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                                } catch (DataCaducitatException e) {
                                    Vista.mostrarMissatge(e.getMessage() + " Intenta-ho un altre cop." + "\n");
                                    Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                                    caducitatAlimentacio = null;
                                }
                            }
                            try {
                                Model.afegirProducteCarret(preuAlimentacio, nomAlimentacio, codiAlimentacio, caducitatAlimentacio);
                                Vista.mostrarMissatge("Producte afegit al carret.\n\n ");
                            } catch (LimitProductesException e) {
                                Vista.mostrarMissatge("Error: " + e.getMessage() + "\n\n");
                            }
                        } catch (NegatiuException | LimitCaractersException e) {
                            Vista.mostrarMissatge("Error: " + e.getMessage() + "\n\n");
                        }
                        break;

                    case 2:
                        try {
                            Vista.mostrarMissatge("Afegir producte tèxtil\n");

                            Vista.mostrarMissatge("Nom producte: ");
                            String nomTextil = scan.next();
                            if (nomTextil.length() > 30)
                                throw new LimitCaractersException("El nom del producte no pot superar els 30 caràcters");


                            Vista.mostrarMissatge("Preu: ");
                            float preuTextil = scan.nextFloat();
                            if (preuTextil < 0) throw new NegatiuException("El preu no pot ser negatiu.");

                            Vista.mostrarMissatge("Composició: ");
                            String composicioTextil = scan.next();
                            if (composicioTextil.length() > 30)
                                throw new LimitCaractersException("La composició del producte tèxtil no pot superar els 30 caràcters");

                            Vista.mostrarMissatge("Codi barres: ");
                            String codiTextil = scan.next();





                            if (Model.existeixCodiBarres(Model.carretCompra, codiTextil)) {
                                Vista.mostrarMissatge("No s'ha pogut afegir el producte. Ja existeix un producte amb el mateix codi de barres.\n\n");
                            } else {
                                try {
                                    if (Model.existeixCodiBarres(Model.carretCompra, codiTextil)) {
                                        Vista.mostrarMissatge("Ja existeix un producte tèxtil amb aquest codi de barres al carret.\n");
                                    } else {
                                        Model.afegirProducteCarret(preuTextil, nomTextil, codiTextil, composicioTextil);
                                        Vista.mostrarMissatge("Producte afegit al carret.\n\n");
                                    }
                                } catch (LimitProductesException e) {
                                    Vista.mostrarMissatge("Error: " + e.getMessage() + "\n\n");
                                }
                            }
                        } catch (NegatiuException | LimitCaractersException e) {
                            Vista.mostrarMissatge("Error: " + e.getMessage() + "\n\n");
                        }
                        break;

                    case 3:
                        try {
                            Vista.mostrarMissatge("Afegir producte electrònic\n");

                            Vista.mostrarMissatge("Nom producte: ");
                            String nomElectronic = scan.next();
                            if (nomElectronic.length() > 30)
                                throw new LimitCaractersException("El nom del producte no pot superar els 30 caràcters");

                            Vista.mostrarMissatge("Preu: ");
                            float preuElectronic = scan.nextFloat();
                            if (preuElectronic < 0) throw new NegatiuException("El preu no pot ser negatiu.");

                            Vista.mostrarMissatge("Garantia (dies): ");
                            int garantiaElectronic = scan.nextInt();

                            Vista.mostrarMissatge("Codi barres: ");
                            String codiElectronic = scan.next();

                            try {
                                Model.afegirProducteCarret(nomElectronic, preuElectronic, garantiaElectronic, codiElectronic);
                                Vista.mostrarMissatge("Producte afegit al carret.\n\n");
                            } catch (LimitProductesException e) {
                                Vista.mostrarMissatge("Error: " + e.getMessage() + "\n\n");
                            }
                        } catch (NegatiuException | LimitCaractersException e) {
                            Vista.mostrarMissatge("Error: " + e.getMessage() + "\n\n");
                        }
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
        // Comprova si el carret està buit abans de passar per caixa
        if (Model.carretCompra.isEmpty()) {
            Vista.mostrarMissatge("El carret està buit.");
            return;
        }

        // Calcula el total de la compra
        float totalCompra = 0.0f;
        for (Map.Entry<Producte, Integer> entry : Model.carretCompra.entrySet()) {
            Producte producte = entry.getKey();
            int quantitat = entry.getValue();
            float preuUnitari = producte.calcularPreu();
            totalCompra += preuUnitari * quantitat;
        }

        // Guarda una còpia del carret per generar el tiquet
        Map<Producte, Integer> copiaCarret = new HashMap<>(Model.carretCompra);
        Tiquet tiquet = new Tiquet(LocalDate.now(), copiaCarret, totalCompra);
        Model.tiquetsCompra.add(tiquet);

        // Mostra el tiquet amb els productes i el total a pagar
        Vista.mostrarTicketCompra(copiaCarret, totalCompra);

        // Buida el carret després de la compra
        Model.carretCompra.clear();
    }

    private static void mostrarCarret() {
        // Mostra el contingut del carret
        Vista.mostrarMissatge("Carret\n");
        Vista.mostrarCarret(Model.carretCompra);
    }
}