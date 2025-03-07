package Model;

public class Electronica extends Producte{
    int diesGarantia;

    public Electronica(float preu, String nom, String codiBarres, int diesGarantia) {
        super(preu, nom, codiBarres);
        this.diesGarantia = diesGarantia;
    }

    public int getDiesGarantia() {
        return diesGarantia;
    }

    @Override
    public float calcularPreu() {
        return preu + preu * (diesGarantia / 365f) * 0.1f;
    }

    @Override
    public String toString() {
        return String.format("ELECTRÒNICA | Nom: %s | Preu: %.2f€ | Codi Barres: %s | Dies de garantia: %s", getNom(), calcularPreu(), getCodiBarres(), getDiesGarantia());
    }
}
