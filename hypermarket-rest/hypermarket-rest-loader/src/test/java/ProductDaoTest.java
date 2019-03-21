import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.ProductDAO;
import ro.sda.hypermarket.core.entity.Product;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ProductDaoTest {


    @Autowired
    private ProductDAO productDAO;

    @Test
    public void createProductTest() {

        Product product = new Product();
        product.setName("Milk");
        product.setSupplierPrice(23F);
        product.setStock(45L);
        product.setVendingPrice(87F);

        productDAO.createProduct(product);
        List<Product> products = productDAO.getAll();
        Assert.assertEquals("Milk", product.getName());
    }

    @Test
    public void getProductByIdTest() {
        Product product = new Product();
        Product product1 = productDAO.getProductById(1L);
        Assert.assertEquals("Milk", product1.getName());
    }

    @Test
    public void updateProductTest(){

        Product product = productDAO.getProductById(2L);
        product.setName("Juice");
        product.setSupplierPrice(45F);
        product.setStock(8L);
        product.setVendingPrice(21F);

        productDAO.updateProduct(product);
        List<Product> products = productDAO.getAll();
        Assert.assertEquals("Juice", products.get(1).getName());
    }

    @Test
    public void deleteProductTest(){
        Product product = productDAO.getProductById(3L);
        productDAO.deleteProduct(product);
        List<Product> products = productDAO.getAll();
        Assert.assertTrue(products.isEmpty());
    }

    @Test
    public void deleteProductTest1(){
        List<Product> products = productDAO.getAll();
        int size = products.size();
        Product product = productDAO.getProductById(2L);
        productDAO.deleteProduct(product);
        products = productDAO.getAll();
        Assert.assertEquals(size - 1, products.size());
    }

    @Test
    public void getAllProductsTest() {

        Product product = new Product();
        product.setName("Avocado");
        product.setSupplierPrice(45F);
        product.setStock(8L);
        product.setVendingPrice(21F);
        productDAO.createProduct(product);

        Product product1 = new Product();
        product1.setName("Apple");
        product1.setSupplierPrice(5F);
        product1.setStock(81L);
        product1.setVendingPrice(23F);
        productDAO.createProduct(product1);

        List<Product> products = productDAO.getAll();
        Assert.assertEquals(2, products.size());
    }
}
