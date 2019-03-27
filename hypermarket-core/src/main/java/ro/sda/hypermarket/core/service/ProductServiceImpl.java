package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.ProductDAO;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.repository.ProductRepository;

import java.util.List;
@Service("productService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public Product create(Product product, boolean useHibernate) {
        if(useHibernate) {
            return productDAO.createProduct(product);
        }
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product, boolean useHibernate) {
        if(useHibernate) {
            return productDAO.updateProduct(product);
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id, boolean useHibernate) {
        if(useHibernate){
            return productDAO.getProductById(id);
        }
        return productRepository.findById(id);
    }

    @Override
    public Product getProductByName(String name, boolean useHibernate){
        if(useHibernate) {
            return productDAO.getProductByName(name);
        }
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> findAll(boolean useHibernate) {
        if(useHibernate) {
            return productDAO.getAll();
        }
        return productRepository.findAll();
    }

    @Override
    public void delete(Product product, boolean useHibernate) {
        if(useHibernate) {
            productDAO.deleteProduct(product);
        }
        productRepository.delete(product);
    }
}
