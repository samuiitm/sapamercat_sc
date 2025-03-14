package Model;

import java.time.LocalDate;
import java.time.Period;

public class Alimentacio extends Producte implements Calculable, Comparable<Alimentacio> {
    private final LocalDate dataCaducitat;

    // Constructor
    public Alimentacio(float preu, String nom, String codiBarres, LocalDate dataCaducitat) {
        super(preu, nom, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }

    /**
     * Mètode per a calcular el preu final del producte segons la data de caducitat
     * @return Float del preu final
     */
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

    /**
     * Mètode per a comparar els productes de tipus alimentació
     * @param o L'objecte a comparar
     * @return Un negatiu, 0 o positiu depenent de la comparació
     */
    @Override
    public int compareTo(Alimentacio o) {
        return this.dataCaducitat.compareTo(o.getDataCaducitat());
    }

    /**
     * Mètode toString() per a mostrar un producte en un format concret
     * @return String amb el format definit
     */
    @Override
    public String toString() {
        return String.format("%-15s | %-11s | %-15s | %-15s",
                "Nom: " + getNom(),
                "Preu: " + getPreu() + "€",
                "Codi Barres: " + getCodiBarres(),
                "Caducitat: " + getDataCaducitat()
        );
    }
}