package Model;

import Excepcions.LimitProductesException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    public static Map<Producte, Integer> carretCompra = new HashMap<>();

    public static Map<Producte, Integer> getCarretCompra() {
        return carretCompra;
    }

    public static List<Tiquet> tiquetsCompra = new ArrayList<>();

    public static List<Tiquet> getTiquetsCompra() {
        return tiquetsCompra;
    }

    public static void afegirProducte(float preuAlimentacio, String nomAlimentacio, String codiAlimentacio, LocalDate caducitatAlimentacio) {
        Alimentacio alimentacio = new Alimentacio(preuAlimentacio, nomAlimentacio, codiAlimentacio, caducitatAlimentacio);
        Model.afegirAlCarret(Model.carretCompra, alimentacio);
    }

    public static void afegirAlCarret(Map<Producte, Integer> carretCompra, Producte producte) {
        if (carretCompra.size() >= 3) {
            throw new LimitProductesException("S'ha superat el límit de 100 productes al carret.");
        }

        if (carretCompra.containsKey(producte)) {
            carretCompra.put(producte, carretCompra.get(producte) + 1);
        } else {
            carretCompra.put(producte, 1);
        }
    }

    public static boolean existeixCodiBarres(Map<Producte, Integer> carretCompra, String codiBarres) {
        return carretCompra.keySet().stream()
                .filter(p -> p instanceof Textil)
                .anyMatch(p -> p.getCodiBarres().equals(codiBarres));
    }
}