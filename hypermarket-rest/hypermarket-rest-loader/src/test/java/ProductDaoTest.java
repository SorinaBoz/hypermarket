import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Product;
import ro.sda.hypermarket.core.entity.ProductCategory;
import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.service.EmployeeService;
import ro.sda.hypermarket.core.service.ProductCategoryService;
import ro.sda.hypermarket.core.service.ProductService;
import ro.sda.hypermarket.core.service.SupplierService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ProductDaoTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Rollback(false)
    @Transactional
    public void createProductTest() {

        Product product = new Product();
        Supplier supplier = new Supplier();
        ProductCategory productCategory = new ProductCategory();

        product.setName("Avocado");
        product.setSupplierPrice(45F);
        product.setStock(43L);
        product.setVendingPrice(77F);

        supplier.setName("Andreea Antonescu");
        supplier.setCity("Bucuresti");
        supplier.setContactNo("554455");
        supplierService.create(supplier, false);
        product.setSupplier(supplier);

        productCategory.setName("Vegetables");
        productCategory.setManager(employeeService.getEmployeeById(32L, false));
        productCategoryService.create(productCategory, false);
        product.setProductCategory(productCategoryService.getProductCategoryById(1L, false));
        productService.create(product, false);
        Assert.assertNotNull(product);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getProductByIdTest() {
        Product expectedProduct = productService.getProductById(22L, false);
        List<Product> products = productService.findAll(false);
        Assert.assertEquals(expectedProduct, products.get(0));
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getProductByNameTest(){
        Product actualProduct = productService.getProductById(23L, false);
        Product expectedProduct = productService.getProductByName("Apples", false);
        Assert.assertEquals(expectedProduct, actualProduct);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void updateProductTest(){

        Product product = productService.getProductById(32L, false);
        product.setName("Orange Juice");
        product.setSupplierPrice(45F);
        product.setStock(8L);
        product.setVendingPrice(21F);

        Supplier supplier = new Supplier();
        supplier.setName("Madalina Mares");
        supplier.setCity("Botosani");
        supplier.setContactNo("5999666");
        supplierService.create(supplier, false);
        product.setSupplier(supplier);
        productService.create(product, false);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Juices");
        productCategory.setManager(employeeService.getEmployeeById(32L, false));
        productCategoryService.update(productCategory,false);
        product.setProductCategory(productCategoryService.getProductCategoryById(1L, false));
    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteProductTest(){
        List<Product> products = productService.findAll(false);
        int size = products.size();
        Product product = productService.getProductById(27L, false);
        productService.delete(product, false);
        products = productService.findAll(false);
        Assert.assertEquals(size - 1, products.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllProductsTest() {

        List<Product> actualProducts = productService.findAll(false);
        Assert.assertEquals(5, actualProducts.size());
    }
}
