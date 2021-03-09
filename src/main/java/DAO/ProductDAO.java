package DAO;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO{
    private static List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1,"dien thoai","100","new",""));
        products.add(new Product(2,"dien thoai1","100","new",""));
        products.add(new Product(3,"dien thoai2","100","new",""));
        products.add(new Product(4,"dien thoai3","100","new",""));
        products.add(new Product(5,"dien thoai4","100","new",""));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findByid(int id) {
        Product product = null;
        for (int i = 0; i < products.size(); i++) {
            if(id == products.get(i).getId()){
                product = products.get(i);
            }
        }
        return product;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void delete(int id) {
        products.remove(id-1);
    }

    @Override
    public void update(int id,Product product) {
        products.set(id-1,product);
    }
}
