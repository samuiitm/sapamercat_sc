package Excepcions;

import java.time.LocalDate;

public class DataCaducitatException extends RuntimeException {
    public DataCaducitatException(String message) {
        super(message);
    }

    public static void verificarFechaCaducidad(LocalDate fechaCaducidad) {
        if (fechaCaducidad.isBefore(LocalDate.now())) {
            throw new DataCaducitatException("La data de caducitat no pot ser anterior a l'actual.");
        }
    }
}
