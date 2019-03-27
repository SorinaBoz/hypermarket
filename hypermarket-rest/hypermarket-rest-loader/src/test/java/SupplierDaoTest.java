import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.service.SupplierService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class SupplierDaoTest {

    @Autowired
    private SupplierService supplierService;

    @Test
    @Rollback(false)
    @Transactional
    public void createSupplierTest(){

        Supplier supplier = new Supplier();
        supplier.setName("Loredana");
        supplier.setCity("Iasi");
        supplier.setContactNo("4455");
        supplierService.create(supplier, false);
        Assert.assertNotNull(supplier);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void updateSupplierTest() {
        Supplier supplier = supplierService.getSupplierById(35L,false);
        supplier.setName("Camelia");
        supplier.setCity("Focsani");
        supplier.setContactNo("4888555");
        supplierService.update(supplier, false);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getSupplierByIdTest() {
        Supplier expectedSupplier = supplierService.getSupplierById(8L, false);
        List<Supplier> suppliers = supplierService.findAll(false);
        Assert.assertEquals(expectedSupplier, suppliers.get(0));
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getSupplierByNameTest(){
        Supplier actualSupplier = supplierService.getSupplierById(8L, false);
        Supplier expectedSupplier = supplierService.getSupplierByName("Madalina", false);
        Assert.assertEquals(expectedSupplier, actualSupplier);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteSupplierTest(){
        List<Supplier> suppliers = supplierService.findAll(false);
        int size = suppliers.size();
        Supplier supplier = supplierService.getSupplierById(7L,false);
        supplierService.delete(supplier, false);
        suppliers = supplierService.findAll(false);
        Assert.assertEquals(size-1, suppliers.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllSuppliersTest(){

        List<Supplier> actualSuppliers = supplierService.findAll(false);
        Assert.assertEquals(22, actualSuppliers.size());
    }
}


