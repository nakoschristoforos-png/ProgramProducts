package soa.dao;

import soa.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAOImpl implements IProductDAO {
    private Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public void insert(Product product) throws Exception {
        product.setId(nextId++);
        products.put(product.getId(), product);
    }

    @Override
    public Product getById(Long id) throws Exception {
        return products.get(id);
    }

    @Override
    public List<Product> getAll() throws Exception {
        return new ArrayList<>(products.values());
    }

    @Override
    public void update(Product product) throws Exception {
        if (!products.containsKey(product.getId())) {
            throw new Exception("Product not found for update");
        }
        products.put(product.getId(), product);
    }


    @Override
    public void delete(Long id) throws Exception {
        if (!products.containsKey(id)) {
            throw new Exception("Product not found for deletion");
        }
        products.remove(id);

    }
}
