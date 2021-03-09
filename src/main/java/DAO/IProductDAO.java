package DAO;

import model.Product;

import java.util.List;

public interface IProductDAO {
    List<Product> findAll();

    Product findByid(int id);

    void save(Product product);

    void delete(int id);

    void update(int id,Product product);
}
