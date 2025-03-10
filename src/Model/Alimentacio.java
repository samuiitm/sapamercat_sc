package Model;

import java.time.LocalDate;
import java.time.Period;

public class Alimentacio extends Producte implements Calculable {
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

        float preuBase = getPreu();
        return preuBase - (preuBase * (1.0f / (diesPerCaducar + 1))) + (preuBase * 0.1f);
    }

    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }
}