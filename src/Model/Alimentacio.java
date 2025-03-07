package Model;

import java.time.LocalDate;
import java.time.Period;

public class Alimentacio extends Producte {
    private final LocalDate dataCaducitat;

    public Alimentacio(float preu, String nom, String codiBarres, LocalDate dataCaducitat) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }

    @Override
    public float calcularPreu() {
        LocalDate avui = LocalDate.now();
        Period periode = Period.between(avui, dataCaducitat);
        int diesPerCaducar = periode.getDays();

        if (diesPerCaducar < 0) {
            return 0;
        }

        return preu - (preu * (1.0f / (diesPerCaducar + 1))) + (preu * 0.1f);
    }

    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }

    @Override
    public String toString() {
        return String.format("ALIMENTACIÓ | Nom: %s | Preu: %.2f€ | Codi Barres: %s | Data caducitat: %s", getNom(), getPreu(), getCodiBarres(), getDataCaducitat());
    }
}