import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.ProductCategory;
import ro.sda.hypermarket.core.service.EmployeeService;
import ro.sda.hypermarket.core.service.ProductCategoryService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class ProductCategoryTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Rollback(false)
    @Transactional
    public void createProductCategoryTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName("Vegetables");
        productCategory.setManager(employeeService.getEmployeeById(32L, false));
        productCategoryService.create(productCategory, false);
        Assert.assertNotNull(productCategory);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void updateProductCategoryTest() {
        ProductCategory productCategory = productCategoryService.getProductCategoryById(7L, false);
        productCategory.setName("Fruits");
        productCategory.setManager(employeeService.getEmployeeById(32L, false));
        productCategoryService.update(productCategory, false);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getProductCategoryByIdTest(){
        ProductCategory expectedProductCategory = productCategoryService.getProductCategoryById(7L, false);
        List<ProductCategory> productCategoryList = productCategoryService.findAll(false);
        Assert.assertEquals(expectedProductCategory, productCategoryList.get(0));
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getProductCategoryByNameTest(){
        ProductCategory actualProductCategory = productCategoryService.getProductCategoryById(7L, false);
        ProductCategory expectedProductCategory = productCategoryService.getProductCategoryByName("Fruits", false);
        Assert.assertEquals(expectedProductCategory, actualProductCategory);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteProductCategoryTest(){
        List<ProductCategory> productCategoryList = productCategoryService.findAll(false);
        int size = productCategoryList.size();
        ProductCategory productCategory = productCategoryService.getProductCategoryById(10L, false);
        productCategoryService.delete(productCategory, false);
        productCategoryList = productCategoryService.findAll(false);
        Assert.assertEquals(size - 1, productCategoryList.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllProductCategoriesTest(){

        List<ProductCategory> actualProductCaterories = productCategoryService.findAll(false);
        Assert.assertEquals(4, actualProductCaterories.size());

    }
}
