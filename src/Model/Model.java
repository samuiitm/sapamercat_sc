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
}