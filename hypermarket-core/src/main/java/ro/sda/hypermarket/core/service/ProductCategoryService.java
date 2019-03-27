package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    ProductCategory create(ProductCategory productCategory, boolean useHibernate);

    ProductCategory update(ProductCategory productCategory, boolean useHibernate);

    ProductCategory getProductCategoryById(Long id, boolean useHibernate);

    ProductCategory getProductCategoryByName(String name, boolean useHibernate);

    List<ProductCategory> findAll(boolean useHibernate);

    void delete(ProductCategory productCategory, boolean useHibernate);
}
