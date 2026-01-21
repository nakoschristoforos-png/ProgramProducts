package soa.dao;

import soa.model.Product;

import java.util.List;

public interface IProductDAO {
    void insert(Product product) throws Exception;

    Product getById(Long id) throws Exception;

    List<Product> getAll() throws Exception;

    void update(Product product) throws Exception;

    void delete(Long id) throws Exception;

}
