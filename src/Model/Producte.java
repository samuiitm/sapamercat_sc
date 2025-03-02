package Model;

public abstract class Producte {
    float preu;
    String nom;
    int codiBarres;

    public Producte(float preu, String nom, int codiBarres) {
        this.preu = preu;
        this.nom = nom;
        this.codiBarres = codiBarres;
    }

    public float getPreu() {
        return preu;
    }

    public String getNom() {
        return nom;
    }


    public int getCodiBarres() {
        return codiBarres;
    }

    public abstract float calcularPreu();
}
