package soa.dto;

import java.math.BigDecimal;

public record ProductReadOnlyDTO (
        Long id,
        String name,
        BigDecimal price,
        int stock
) {}

