package Model;

import java.util.Map;

public class Model {
    public static void afegirAlCarret(Map<Producte, Integer> carretCompra, Producte producte) {
        if (carretCompra.containsKey(producte)) {
            carretCompra.put(producte, carretCompra.get(producte) + 1);
        } else {
            carretCompra.put(producte, 1);
        }
    }

    public static boolean existeixCodiBarres(Map<Producte, Integer> carretCompra, String codiBarres) {
        return carretCompra.keySet().stream().anyMatch(p -> p.getCodiBarres().equals(codiBarres));
    }
}