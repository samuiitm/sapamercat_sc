package Model;

public class Textil extends Producte implements Comparable<Textil>{
    String composicioTextil;

    /**
     * Mètode constructor de la classe tèxtil
     * @param preu Preu del producte
     * @param nom Nom del producte
     * @param codiBarres Codi de barres del producte
     * @param composicioTextil Composició tèxtil del producte
     */
    public Textil(float preu, String nom, String codiBarres, String composicioTextil) {
        super(preu, nom, codiBarres);
        this.composicioTextil = composicioTextil;
    }

    // GETTERS
    public String getComposicioTextil() {
        return composicioTextil;
    }

    /**
     * Mètode comparador dels productes tèxtils
     * @param t L'objecte a comparar
     * @return Un negatiu, 0 o positiu depenent de la comparació
     */
    @Override
    public int compareTo(Textil t) {
        return this.composicioTextil.compareTo(t.getComposicioTextil());
    }

    /**
     * Mètode toString() per a mostrar un producte en un format concret
     * @return String amb el format definit
     */
    @Override
    public String toString() {
        return String.format("%-15s | %-12s | %-15s | %-15s",
                "Nom: " + getNom(),
                "Preu: " + getPreu() + "€",
                "Codi Barres: " + getCodiBarres(),
                "Composició: " + getComposicioTextil()
        );
    }

    @Override
    public float calcularPreu() {
        return getPreu();
    }
}
