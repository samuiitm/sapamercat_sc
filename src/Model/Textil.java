package Model;

public class Textil extends Producte implements Comparable<Textil>{
    String composicioTextil;

    public Textil(float preu, String nom, String codiBarres, String composicioTextil) {
        super(preu, nom, codiBarres);
        this.composicioTextil = composicioTextil;
    }

    public String getComposicioTextil() {
        return composicioTextil;
    }

    @Override
    public int compareTo(Textil t) {
        return this.composicioTextil.compareTo(t.getComposicioTextil());
    }

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
