import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.DepartmentDAO;
import ro.sda.hypermarket.core.entity.Department;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class DepartmentDaoTest {


    @Autowired
    private DepartmentDAO departmentDAO;

    @Test
    public void createClientTest() {

        Department department = new Department();
        department.setName("Sales");
        departmentDAO.createDepartment(department);
        List<Department> departments = departmentDAO.getAll();
        Assert.assertEquals("Sales", department.getName());
    }

    @Test
    public void getDepartmentByIdTest() {
        Department department = new Department();
        Department department1 = departmentDAO.getDepartmentById(1L);
        Assert.assertEquals("Sales", department1.getName());
    }

    @Test
    public void updateDepartmentTest(){

        Department department = departmentDAO.getDepartmentById(2L);
        department.setName("HR");
        departmentDAO.updateDepartment(department);
        List<Department> departments = departmentDAO.getAll();
        Assert.assertEquals("HR", departments.get(1).getName());
    }

    @Test
    public void deleteDepartmentTest(){
        Department department = departmentDAO.getDepartmentById(4L);
        departmentDAO.deleteDepartment(department);
        List<Department> departments = departmentDAO.getAll();
        Assert.assertTrue(departments.isEmpty());
    }

    @Test
    public void deleteDepartmentTest1(){
        List<Department> departments = departmentDAO.getAll();
        int size = departments.size();
        Department department = departmentDAO.getDepartmentById(4L);
        departmentDAO.deleteDepartment(department);
        departments = departmentDAO.getAll();
        Assert.assertEquals(size - 1, departments.size());
    }

    @Test
    public void getAllDepartmentsTest() {

        Department department = new Department();
        department.setName("Marketing");
        departmentDAO.createDepartment(department);

        Department department1 = new Department();
        department1.setName("Accounting");
        departmentDAO.createDepartment(department1);

        List<Department> departments = departmentDAO.getAll();
        Assert.assertEquals(2, departments.size());
    }

}
