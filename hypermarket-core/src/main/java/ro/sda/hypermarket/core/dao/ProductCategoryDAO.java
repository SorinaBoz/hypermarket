package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryDAO {

    ProductCategory createProductCategory (ProductCategory productCategory);

    ProductCategory getProductCategoryByName(String name);

    ProductCategory getProductCategoryById(Long id);

    List<ProductCategory> getAll();

    ProductCategory updateProductCategory (ProductCategory productCategory);

    void deleteProductCategory(ProductCategory productCategory);
}
