package Model;

import java.util.Objects;

public abstract class Producte implements Calculable {
    float preu;
    String nom;
    String codiBarres;

    /**
     * Mètode contstructor de la classe producte
     * @param preu Preu del producte
     * @param nom Nom del producte
     * @param codiBarres Codi de barres del producte
     */
    public Producte(float preu, String nom, String codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
    }

    // GETTERS
    public float getPreu() {
        return preu;
    }

    public String getNom() {
        return nom;
    }

    public String getCodiBarres() {
        return codiBarres;
    }

    @Override
    /**
     * Mètode que ens permet saber si un objecte és el mateix que un altre
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producte producte = (Producte) o;
        return Objects.equals(codiBarres, producte.codiBarres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codiBarres);
    }
}
