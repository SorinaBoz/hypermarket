package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.ProductCategoryDAO;
import ro.sda.hypermarket.core.entity.ProductCategory;
import ro.sda.hypermarket.core.repository.ProductCategoryRepository;

import java.util.List;

@Service("productCategoryService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductCategoryServiceImpl implements ProductCategoryService{

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public ProductCategory create(ProductCategory productCategory, boolean useHibernate) {
        if(useHibernate) {
            return productCategoryDAO.createProductCategory(productCategory);
        }
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory update(ProductCategory productCategory, boolean useHibernate) {
        if(useHibernate) {
            return productCategoryDAO.updateProductCategory(productCategory);
        }
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public ProductCategory getProductCategoryById(Long id, boolean useHibernate) {
        if(useHibernate) {
            return productCategoryDAO.getProductCategoryById(id);
        }
        return productCategoryRepository.findById(id);
    }

    @Override
    public ProductCategory getProductCategoryByName(String name, boolean useHibernate){
        if(useHibernate) {
            return productCategoryDAO.getProductCategoryByName(name);
        }
        return productCategoryRepository.findByName(name);
    }

    @Override
    public List<ProductCategory> findAll(boolean useHibernate) {
        if(useHibernate) {
            return productCategoryDAO.getAll();
        }
        return productCategoryRepository.findAll();
    }

    @Override
    public void delete(ProductCategory productCategory, boolean useHibernate) {
        if(useHibernate) {
            productCategoryDAO.deleteProductCategory(productCategory);
        }
        productCategoryRepository.delete(productCategory);
    }
}
