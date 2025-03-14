package Model;

import java.time.LocalDate;
import java.util.Map;

public class Tiquet {
    private final LocalDate dataCompra;
    private final Map<Producte, Integer> productesComprats;
    private final float totalCompra;

    /**
     * Mètode constructor de la classe Tiquet
     * @param dataCompra Data de la compra
     * @param productesComprats Llista dels productes comprats
     * @param totalCompra Preu total de la compra
     */
    public Tiquet(LocalDate dataCompra, Map<Producte, Integer> productesComprats, float totalCompra) {
        this.dataCompra = dataCompra;
        this.productesComprats = (Map<Producte, Integer>) productesComprats;
        this.totalCompra = totalCompra;
    }

    // GETTERS
    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public Map<Producte, Integer> getProductesComprats() {
        return productesComprats;
    }

    public float getTotalCompra() {
        return totalCompra;
    }
}
