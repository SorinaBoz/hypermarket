package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.Product;

import java.util.List;

public interface ProductDAO {

    Product createProduct(Product product);

    Product getProductById(Long id);

    Product getProductByName(String name);

    List<Product> getAll();

    Product updateProduct(Product product);

    void deleteProduct(Product product);
}
