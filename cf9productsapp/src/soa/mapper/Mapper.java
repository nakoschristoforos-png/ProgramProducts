package soa.mapper;

import soa.dto.ProductInsertDTO;
import soa.dto.ProductReadOnlyDTO;
import soa.model.Product;

public class Mapper {
    private Mapper() {}

    public static Product mapToEntity(ProductInsertDTO dto) {
        Product product = new Product();
                product.setName(dto.name());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        return product;
    }

    public static ProductReadOnlyDTO mapToReadOnlyDTO(Product product) {
        return new ProductReadOnlyDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock()
        );
    }



}
