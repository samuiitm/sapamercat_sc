package Model;

public class Electronica extends Producte implements Calculable{
    int diesGarantia;

    /**
     * Mètode constructor de la classe electrònica
     * @param preu Preu del producte
     * @param nom Nom del producte
     * @param codiBarres Codi de barres del producte
     * @param diesGarantia Dies de garantia del producte
     */
    public Electronica(float preu, String nom, String codiBarres, int diesGarantia) {
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    // GETTERS
    public int getDiesGarantia() {
        return diesGarantia;
    }

    /**
     * Mètode per a calcular el preu depenent del temps de garantia
     * @return Float del preu final
     */
    @Override
    public float calcularPreu() {
        return preu + preu * (diesGarantia / 365f) * 0.1f;
    }

    /**
     * Mètode toString() per a mostrar un producte en un format concret
     * @return String amb el format definit
     */
    @Override
    public String toString() {
        return String.format("ELECTRÒNICA | Nom: %s | Preu: %.2f€ | Codi Barres: %s | Dies de garantia: %s", getNom(), calcularPreu(), getCodiBarres(), getDiesGarantia());
    }
}
