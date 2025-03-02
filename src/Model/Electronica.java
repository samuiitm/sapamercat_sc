package Model;

public class Electronica extends Producte{
    int diesGarantia;

    public Electronica(float preu, String nom, int codiBarres, int diesGarantia) {
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
}
