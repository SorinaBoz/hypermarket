import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.ProductCategoryDAO;
import ro.sda.hypermarket.core.dao.ProductDAO;
import ro.sda.hypermarket.core.dao.SupplierDAO;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.entity.Supplier;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ProductDaoTest {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductCategoryDAO productCategoryDAO;

    @Autowired
    private SupplierDAO supplierDAO;

//    TODO: VERIFY - TEST FAILED
    @Test
    public void createProductTest() {

        Product product = new Product();
        Supplier supplier = new Supplier();
//        ProductCategory productCategory = new ProductCategory();
        Employee employee = new Employee();

        product.setName("Avocado");
        product.setSupplierPrice(45F);
        product.setStock(43L);
        product.setVendingPrice(77F);

        supplier.setName("Andreea Antonescu");
        supplier.setCity("Bucuresti");
        supplier.setContactNo("554455");
        supplierDAO.createSupplier(supplier);
        product.setSupplier(supplier);

//        productCategory.setName("Vegetables");
//        productCategory.setManager(employee);
//        productCategoryDAO.createProductCategory(productCategory);
//        product.setProductCategory(productCategory);
        productDAO.createProduct(product);

        Product actualProduct = productDAO.getProductByName("Avocado");
        Product expectedProduct = product;
        Assert.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void getProductByIdTest() {
        Product expectedProduct = productDAO.getProductById(1L);
        List<Product> products = productDAO.getAll();
        Assert.assertEquals(expectedProduct, products.get(0));
    }

    @Test
    public void getProductByNameTest(){
        Product actualProduct = productDAO.getProductById(22L);
        Product expectedProduct = productDAO.getProductByName("Spinach");
        Assert.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    public void updateProductTest(){

        Product product = productDAO.getProductById(24L);
        product.setName("Orange Juice");
        product.setSupplierPrice(45F);
        product.setStock(8L);
        product.setVendingPrice(21F);

        Supplier supplier = new Supplier();
        supplier.setName("Madalina Mares");
        supplier.setCity("Botosani");
        supplier.setContactNo("5999666");
        supplierDAO.createSupplier(supplier);
        product.setSupplier(supplier);
        productDAO.createProduct(product);

//        ProductCategory productCategory = new ProductCategory();
//        productCategory.setName("Juices");
//        Employee employee = new Employee();
//        productCategory.setManager(employee);
//        productCategoryDAO.updateProductCategory(productCategory);
//        product.setProductCategory(productCategory);

        Product actualProduct = productDAO.getProductByName("Orange Juice");
        Product expectedProduct = product;
        Assert.assertEquals(expectedProduct, actualProduct);
    }

//    @Test
//    public void deleteProductTest(){
//        Product product = productDAO.getProductById(3L);
//        productDAO.deleteProduct(product);
//        List<Product> products = productDAO.getAll();
//        Assert.assertTrue(products.isEmpty());
//    }

    @Test
    public void deleteProductTest1(){
        List<Product> products = productDAO.getAll();
        int size = products.size();
        Product product = productDAO.getProductById(25L);
        productDAO.deleteProduct(product);
        products = productDAO.getAll();
        Assert.assertEquals(size - 1, products.size());
    }

    @Test
    public void getAllProductsTest() {

//        Product product = new Product();
//        Supplier supplier = new Supplier();
//        ProductCategory productCategory = new ProductCategory();
//        Employee employee = new Employee();
//
//        product.setName("Avocado");
//        product.setSupplierPrice(45F);
//        product.setStock(8L);
//        product.setVendingPrice(21F);
//
//        supplier.setName("Alina Mocanu");
//        supplier.setCity("Iasi");
//        supplier.setContactNo("00000000");
//        supplierDAO.createSupplier(supplier);
//        product.setSupplier(supplier);
//
//        productCategory.setName("Fruits");
//        productCategory.setManager(employee);
//        productCategoryDAO.createProductCategory(productCategory);
//        product.setProductCategory(productCategory);
//        productDAO.createProduct(product);

        List<Product> actualProducts = productDAO.getAll();
        Assert.assertEquals(4, actualProducts.size());
    }
}
