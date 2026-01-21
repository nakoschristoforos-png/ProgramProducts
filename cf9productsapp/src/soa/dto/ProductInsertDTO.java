package soa.dto;

import java.math.BigDecimal;

public record ProductInsertDTO (
    String name,
    BigDecimal price,
    int stock
) {}
