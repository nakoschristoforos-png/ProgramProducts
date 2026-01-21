package soa.service;

import soa.dto.ProductInsertDTO;
import soa.dto.ProductReadOnlyDTO;

import java.util.List;

public interface IProductService {
    void addProduct(ProductInsertDTO dto) throws Exception;

    ProductReadOnlyDTO getProduct(Long id) throws Exception;

    List<ProductReadOnlyDTO> getAllProducts() throws Exception;

    void updateStock(Long id, int quantity) throws Exception;

    void deleteProduct(Long id) throws Exception;
}

