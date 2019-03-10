import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.SupplierDAO;
import ro.sda.hypermarket.core.dao.SupplierDAOImpl;
import ro.sda.hypermarket.core.entity.Supplier;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class SupplierDaoTest {

    @Autowired
    private SupplierDAO supplierDAO;
    Supplier supplier = new Supplier();

    @Test
    @Transactional
    @Rollback(true)
    public void createSupplierTest(){
        supplier.setName("Sorina");
        supplier.setCity("Iasi");
        supplier.setContactNo("0754386983");
        supplierDAO.createSupplier(supplier);
        List<Supplier> suppliers = supplierDAO.getAll();
        Assert.assertEquals(1, suppliers.size());
        Assert.assertEquals("Sorina", suppliers.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateSupplierTest() {
        Supplier supplier = supplierDAO.getSupplierById(8L);
        supplier.setName("Madalina");
        supplier.setCity("Iasi");
        supplier.setContactNo("00000000");
        supplierDAO.updateSupplier(supplier);
        List<Supplier> suppliers = supplierDAO.getAll();
        Assert.assertEquals("Madalina", suppliers.get(0).getName());
        Assert.assertEquals("Iasi", suppliers.get(0).getCity());
        Assert.assertEquals("00000000", suppliers.get(0).getContactNo());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getSupplierByIdTest(){
        supplierDAO.getSupplierById(8L);
        List<Supplier> suppliers = supplierDAO.getAll();
        Assert.assertEquals("Sorina", suppliers.get(0).getName());
        Assert.assertEquals("Iasi", suppliers.get(0).getCity());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteSupplierTest(){
        Supplier supplier1 = supplierDAO.getSupplierById(10L);
        supplierDAO.deleteSupplier(supplier1);
        List<Supplier> suppliers = supplierDAO.getAll();
        Assert.assertTrue ( suppliers.isEmpty());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteSupplierTest1(){
        List<Supplier> suppliers = supplierDAO.getAll();
        int size = suppliers.size();
        Supplier supplier1 = supplierDAO.getSupplierById(12L);
        supplierDAO.deleteSupplier(supplier1);
        suppliers = supplierDAO.getAll();
        Assert.assertEquals(size - 1, suppliers.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void getAllSuppliersTest(){
        Supplier supplier1 = new Supplier();
        supplier1.setName("Sorina");
        supplier1.setCity("Iasi");
        supplier1.setContactNo("122345");
        supplierDAO.createSupplier(supplier1);
        Supplier supplier2 = new Supplier();
        supplier2.setName("Ana");
        supplier2.setCity("Iasi");
        supplier2.setContactNo("000000");
        supplierDAO.createSupplier(supplier2);
        List<Supplier> suppliers = supplierDAO.getAll();
        Assert.assertEquals(2, suppliers.size());

    }



    }


