package Model;

import Excepcions.LimitProductesException;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import java.time.LocalDate;
import java.util.*;

public class Model {
    public static Map<Producte, Integer> carretCompra = new HashMap<>(); // HashMap on es guardaran els productes a comprar
    public static List<Tiquet> tiquetsCompra = new ArrayList<>(); // ArrayList on es guardaran els tiquets de compra
    static List<Producte> productesMagatzem = new ArrayList<>(); // Arraylist on es guardaran els productes del magatzem

    /**
     * Mètode per a inicialitzar els productes del magatzem
     */
    public static void inicialitzarProductes() {
        afegirProducteMagatzem(2.99f, "Llet", "101", LocalDate.of(2025, 3, 10));
        afegirProducteMagatzem(1.50f, "Pa", "102", LocalDate.of(2025, 3, 15));
        afegirProducteMagatzem(5.99f, "Formatge", "103", LocalDate.of(2025, 4, 1));
        afegirProducteMagatzem(3.20f, "Iogurt", "104", LocalDate.of(2025, 3, 25));
        afegirProducteMagatzem(2.10f, "Mantega", "105", LocalDate.of(2025, 4, 5));
        afegirProducteMagatzem(8.99f, "Pollastre", "106", LocalDate.of(2025, 3, 20));
        afegirProducteMagatzem(4.75f, "Tonyina", "107", LocalDate.of(2026, 1, 1));
        afegirProducteMagatzem(1.99f, "Galetes", "108", LocalDate.of(2025, 5, 10));
        afegirProducteMagatzem(3.49f, "Arròs", "109", LocalDate.of(2026, 6, 30));
        afegirProducteMagatzem(2.99f, "Pasta", "110", LocalDate.of(2026, 7, 15));

        afegirProducteMagatzem(15.99f, "Samarreta", "201", "100% Cotó");
        afegirProducteMagatzem(25.50f, "Pantalons", "202", "Denim");
        afegirProducteMagatzem(30.00f, "Jaqueta", "203", "Polièster");
        afegirProducteMagatzem(12.99f, "Mitjons", "204", "Llana");
        afegirProducteMagatzem(18.75f, "Bufanda", "205", "Cotó i seda");
        afegirProducteMagatzem(22.40f, "Guants", "206", "Cuir");
        afegirProducteMagatzem(28.99f, "Sabates", "207", "Pell sintètica");
        afegirProducteMagatzem(35.00f, "Abric", "208", "Llana");
        afegirProducteMagatzem(9.99f, "Cinturó", "209", "Cuir");
        afegirProducteMagatzem(14.50f, "Barret", "210", "Cotó");

        afegirProducteMagatzem(499.99f, "Portàtil", "301", 730);
        afegirProducteMagatzem(999.99f, "Telèfon mòbil", "302", 730);
        afegirProducteMagatzem(199.99f, "Auriculars sense fils", "303", 365);
        afegirProducteMagatzem(79.99f, "Teclat mecànic", "304", 365);
        afegirProducteMagatzem(59.99f, "Ratolí gaming", "305", 365);
        afegirProducteMagatzem(150.00f, "Monitor 24 polzades", "306", 128);
        afegirProducteMagatzem(49.99f, "Altaveus Bluetooth", "307", 365);
        afegirProducteMagatzem(89.99f, "Smartwatch", "308", 365);
        afegirProducteMagatzem(29.99f, "Carregador ràpid", "309", 365);
        afegirProducteMagatzem(399.99f, "Tablet", "310", 128);
    }

    // Mètodes polimòrfics per a afegir un tipus de producte un altre en funció dels paràmetres introduïts
    public static void afegirProducteMagatzem(float preu, String nom, String codiBarres, LocalDate caducitat) {
        productesMagatzem.add(new Alimentacio(preu, nom, codiBarres, caducitat));
    }

    public static void afegirProducteMagatzem(float preu, String nom, String codiBarres, String composicioTextil) {
        productesMagatzem.add(new Textil(preu, nom, codiBarres, composicioTextil));
    }

    public static void afegirProducteMagatzem(float preu, String nom, String codiBarres, int garantia) {
        productesMagatzem.add(new Electronica(preu, nom, codiBarres, garantia));
    }

    /**
     * Mètode per a filtrar els productes de tipus Alimentació per data de caducitat
     * @return Una List de tipus Alimentació ordenada per data de caducitat
     */
    public static List<Alimentacio> filtrarCaducitat() {
        List<Alimentacio> productesAlimentacio = new ArrayList<>();
        for (Object producte : productesMagatzem) {
            if (producte instanceof Alimentacio) {
                productesAlimentacio.add((Alimentacio) producte);
            }
        }
        productesAlimentacio.sort(Comparator.comparing(Alimentacio::getDataCaducitat));
        return productesAlimentacio;
    }

    /**
     * Mètode per filtrar els productes de tipus tèxtil per composició tèxtil
     * @return Una List de tipus Tèxtil ordenada per composició tèxtil (A-Z)
     */
    public static List<Textil> filtrarComposicio() {
        List<Textil> productesTextil = new ArrayList<>();
        for (Object producte : productesMagatzem) {
            if (producte instanceof Textil) {
                productesTextil.add((Textil) producte);
            }
        }
        productesTextil.sort(Comparator.comparing(Textil::getComposicioTextil));
        return productesTextil;
    }

    // Mètodes polimòrfics per afegir nous productes al carret de la compra en funció dels paràmetres introduïts
    public static void afegirProducteCarret(float preuAlimentacio, String nomAlimentacio, String codiAlimentacio, LocalDate caducitatAlimentacio) {
        Alimentacio alimentacio = new Alimentacio(preuAlimentacio, nomAlimentacio, codiAlimentacio, caducitatAlimentacio);
        Model.afegirAlCarret(Model.carretCompra, alimentacio);
    }

    public static void afegirProducteCarret(Float preuTextil, String nomTextil, String codiTextil, String composicioTextil) {
        Textil textil = new Textil(preuTextil, nomTextil, codiTextil, composicioTextil);
        Model.afegirAlCarret(Model.carretCompra, textil);
    }

    public static void afegirProducteCarret(String nomElectronic, float preuElectronic, int garantiaElectronic, String codiElectronic) {
        Electronica electronic = new Electronica(preuElectronic, nomElectronic, codiElectronic, garantiaElectronic);
        Model.afegirAlCarret(Model.carretCompra, electronic);
    }

    // Mètode per a fer comprovacions abans d'afegir el producte definitivament
    public static void afegirAlCarret(Map<Producte, Integer> carretCompra, Producte producte) {
        if (carretCompra.size() >= 100) {
            throw new LimitProductesException("S'ha superat el límit de 100 productes al carret.");
        }

        if (carretCompra.containsKey(producte)) {
            carretCompra.put(producte, carretCompra.get(producte) + 1);
        } else {
            carretCompra.put(producte, 1);
        }
    }

    /**
     * Mètode que ens permet saber si existeix un producte tèxtil amb el codi de barres introduït
     * @param carretCompra Map dels productes del carret de la compra
     * @param codiBarres Codi de barres introduït
     * @return True/false en funció de si existeix un producte tèxtil amb el codi de barres introduït
     */
    public static boolean existeixCodiBarres(Map<Producte, Integer> carretCompra, String codiBarres) {
        return carretCompra.keySet().stream()
                .filter(p -> p instanceof Textil)
                .anyMatch(p -> p.getCodiBarres().equals(codiBarres));
    }

    // GETTERS
    public static List<Tiquet> getTiquetsCompra() {
        return tiquetsCompra;
    }

    public static Map<Producte, Integer> getCarretCompra() {
        return carretCompra;
    }
}