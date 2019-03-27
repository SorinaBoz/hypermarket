package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Product;

import java.util.List;

public interface ProductService {

    Product create(Product product, boolean useHibernate);

    Product update(Product product, boolean useHibernate);

    Product getProductById(Long id, boolean useHibernate);

    Product getProductByName(String name, boolean useHibernate);

    List<Product> findAll(boolean useHibernate);

    void delete(Product product, boolean useHibernate);
}
