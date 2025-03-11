package Excepcions;

import Model.Producte;

import java.util.Map;

public class LimitProductesException extends RuntimeException {
    public LimitProductesException(String message) {
        super(message);
    }
}
