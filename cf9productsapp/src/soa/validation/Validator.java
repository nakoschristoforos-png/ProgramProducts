package soa.validation;

import soa.dto.ProductInsertDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Validator {

    private Validator() {}

    public static Map<String, String> validateInsertDTO(ProductInsertDTO dto) {
        Map<String, String> errors = new HashMap<>();

        // Έλεγχος 1: Το name δεν είναι κενό
        if (dto.name() == null || dto.name().trim().isEmpty()) {
            errors.put("name", "Product name is required");
        }

        // Έλεγχος 2: Το price είναι θετικό
        if (dto.price() == null || dto.price().compareTo(BigDecimal.ZERO) <= 0) {
            errors.put("price", "Price must be greater than zero");
        }

        // Έλεγχος 3: Το stock δεν είναι αρνητικό
        if (dto.stock() < 0) {
            errors.put("stock", "Stock cannot be negative");
        }

        return errors;
    }
}
