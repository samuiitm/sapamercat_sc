package Model;

public class Textil extends Producte{
    String composicioTextil;

    public Textil(float preu, String nom, String codiBarres, String composicioTextil) {
        super(preu, nom, codiBarres);
        this.composicioTextil = composicioTextil;
    }

    @Override
    public float calcularPreu() {
        return preu;
    }

    public String getComposicioTextil() {
        return composicioTextil;
    }

    @Override
    public String toString() {
        return String.format("TÈXTIL | Nom: %s | Preu: %.2f€ | Codi Barres: %s | Composició: %s", getNom(), getPreu(), getCodiBarres(), getComposicioTextil());
    }
}
