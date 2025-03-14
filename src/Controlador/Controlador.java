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
                // Mostrem el menú per afegir productes
                Vista.mostrarMenuAfegirProducte();
                opcio = scan.nextInt();

                switch (opcio) {
                    case 1:
                        try {
                            // Afegir producte d'alimentació
                            Vista.mostrarMissatge("Afegir producte d'alimentació\n");

                            // Demanem el nom del producte
                            Vista.mostrarMissatge("Nom producte: ");
                            String nomAlimentacio = scan.next();

                            // Comprovem que el nom no sigui massa llarg
                            if (nomAlimentacio.length() > 30)
                                throw new LimitCaractersException("El nom del producte no pot superar els 30 caràcters");

                            // Demanem el preu
                            Vista.mostrarMissatge("Preu: ");
                            float preuAlimentacio = scan.nextFloat();

                            // Comprovem que el preu no sigui negatiu
                            if (preuAlimentacio < 0) throw new NegatiuException("El preu no pot ser negatiu.");

                            // Demanem el codi de barres
                            Vista.mostrarMissatge("Codi barres: ");
                            String codiAlimentacio = scan.next();

                            // Comprovem que el codi de barres no sigui massa llarg
                            if (codiAlimentacio.length() > 30)
                                throw new LimitCaractersException("El codi de barres del producte no pot superar els 30 caràcters");


                            // Demanem la data de caducitat
                            Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                            LocalDate caducitatAlimentacio = null;

                            // Bucle per assegurar que l'usuari introdueixi bé la data
                            while (caducitatAlimentacio == null) {
                                try {
                                    String caducitatAlimentacioString = scan.next();
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    caducitatAlimentacio = LocalDate.parse(caducitatAlimentacioString, formatter);

                                    // Verifiquem que la data no sigui anterior a avui
                                    DataCaducitatException.verificarDataCaducitat(caducitatAlimentacio);
                                } catch (DateTimeParseException e) {
                                    Vista.mostrarMissatge("La data ha de ser en format 'dd/MM/yyyy'. Intenta-ho de nou.\n");
                                    Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                                } catch (DataCaducitatException e) {
                                    Vista.mostrarMissatge(e.getMessage() + " Intenta-ho un altre cop.\n");
                                    Vista.mostrarMissatge("Data de caducitat (dd/mm/aaaa): ");
                                    caducitatAlimentacio = null;
                                }
                            }

                            // Afegim el producte al carret
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
                            // Afegir producte tèxtil
                            Vista.mostrarMissatge("Afegir producte tèxtil\n");

                            // Demanem el nom del producte
                            Vista.mostrarMissatge("Nom producte: ");
                            String nomTextil = scan.next();

                            // Comprovem la longitud del nom
                            if (nomTextil.length() > 30)
                                throw new LimitCaractersException("El nom del producte no pot superar els 30 caràcters");

                            // Demanem el preu
                            Vista.mostrarMissatge("Preu: ");
                            float preuTextil = scan.nextFloat();

                            // Comprovem que el preu no sigui negatiu
                            if (preuTextil < 0) throw new NegatiuException("El preu no pot ser negatiu.");

                            // Demanem la composició
                            Vista.mostrarMissatge("Composició: ");
                            String composicioTextil = scan.next();

                            // Comprovem la longitud de la composició
                            if (composicioTextil.length() > 30)
                                throw new LimitCaractersException("La composició del producte tèxtil no pot superar els 30 caràcters");

                            // Demanem el codi de barres
                            Vista.mostrarMissatge("Codi barres: ");
                            String codiTextil = scan.next();

                            // Comprovem que el codi de barres no sigui massa llarg
                            if (codiTextil.length() > 30)
                                throw new LimitCaractersException("El codi de barres del producte no pot superar els 30 caràcters");

                            // Comprovem si ja existeix un producte tèxtil amb aquest codi de barres
                            if (Model.existeixCodiBarres(Model.carretCompra, codiTextil)) {
                                Vista.mostrarMissatge("No s'ha pogut afegir el producte. Ja existeix un producte amb el mateix codi de barres.\n\n");
                            } else {
                                try {
                                    Model.afegirProducteCarret(preuTextil, nomTextil, codiTextil, composicioTextil);
                                    Vista.mostrarMissatge("Producte afegit al carret.\n\n");
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
                            // Afegir producte electrònic
                            Vista.mostrarMissatge("Afegir producte electrònic\n");

                            // Demanem el nom del producte
                            Vista.mostrarMissatge("Nom producte: ");
                            String nomElectronic = scan.next();

                            // Comprovem la longitud del nom
                            if (nomElectronic.length() > 30)
                                throw new LimitCaractersException("El nom del producte no pot superar els 30 caràcters");

                            // Demanem el preu
                            Vista.mostrarMissatge("Preu: ");
                            float preuElectronic = scan.nextFloat();

                            // Comprovem que el preu no sigui negatiu
                            if (preuElectronic < 0) throw new NegatiuException("El preu no pot ser negatiu.");

                            // Demanem la garantia
                            Vista.mostrarMissatge("Garantia (dies): ");
                            int garantiaElectronic = scan.nextInt();

                            // Demanem el codi de barres
                            Vista.mostrarMissatge("Codi barres: ");
                            String codiElectronic = scan.next();

                            // Comprovem que el codi de barres no sigui massa llarg
                            if (codiElectronic.length() > 30)
                                throw new LimitCaractersException("El codi de barres del producte no pot superar els 30 caràcters");

                            // Afegim el producte al carret
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
            Vista.mostrarMissatge("El carret està buit.\n\n");
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