package soa.service;

import soa.mapper.Mapper;
import soa.dao.IProductDAO;
import soa.dao.ProductDAOImpl;
import soa.dto.ProductInsertDTO;
import soa.dto.ProductReadOnlyDTO;
import soa.exceptions.InsufficientStockException;
import soa.exceptions.ProductNotFoundException;
import soa.model.Product;
import soa.validation.Validator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductServiceImpl implements IProductService {

    private final IProductDAO productDAO = new ProductDAOImpl();

    @Override
    public void addProduct(ProductInsertDTO dto) throws Exception {
        // 1. VALIDATION
        Map<String, String> errors = Validator.validateInsertDTO(dto);

        if (!errors.isEmpty()) {
            throw new Exception("Validation failed: " + errors);
        }

        // 2. MAP DTO → Entity
        Product product = Mapper.mapToEntity(dto);

        // 3. PERSIST via DAO
        productDAO.insert(product);
    }

    @Override
    public ProductReadOnlyDTO getProduct(Long id) throws Exception {
        // 1. Get from DAO
        Product product = productDAO.getById(id);

        // 2. BUSINESS RULE: Check if exists
        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        // 3. MAP Entity → DTO
        return Mapper.mapToReadOnlyDTO(product);
    }

    @Override
    public List<ProductReadOnlyDTO> getAllProducts() throws Exception {
        // 1. Get all from DAO
        List<Product> products = productDAO.getAll();

        // 2. MAP List<Entity> → List<DTO>
        return products.stream()
                .map(Mapper::mapToReadOnlyDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStock(Long id, int quantity) throws Exception {
        // 1. Get product
        Product product = productDAO.getById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        // 2. BUSINESS RULE: Stock cannot go negative
        int newStock = product.getStock() + quantity;

        if (newStock < 0) {
            throw new InsufficientStockException(
                    "Cannot reduce stock by " + Math.abs(quantity) +
                            ". Available: " + product.getStock()
            );
        }

        // 3. Update
        product.setStock(newStock);
        productDAO.update(product);
    }

    @Override
    public void deleteProduct(Long id) throws Exception {
        // 1. Check if exists
        Product product = productDAO.getById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product with ID " + id + " not found");
        }

        // 2. Delete via DAO
        productDAO.delete(id);
    }

}


