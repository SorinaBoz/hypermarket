import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.DepartmentDAO;
import ro.sda.hypermarket.core.dao.EmployeeDao;
import ro.sda.hypermarket.core.entity.Department;
import ro.sda.hypermarket.core.entity.Employee;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class DepartmentDaoTest {


    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void createDepartmentTest() {

        Department department = new Department();
        Employee employee = new Employee();
        department.setName("Advertising");
        department.setManager(employeeDao.getEmployeeById(32L));
        departmentDAO.createDepartment(department);

        employee.setName("Maria Ionescu");
        employee.setCity("Cluj");
        employee.setSalary(45D);
        employee.setJobTitle("Saler");
        employeeDao.createEmployee(employee);

        Department actualDepartment = departmentDAO.getDepartmentByName("Advertising");
        Department expectedDepartment = department;
        Assert.assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    public void getDepartmentByIdTest() {
        Department expectedDepartment = departmentDAO.getDepartmentById(24L);
        List<Department> departments = departmentDAO.getAll();
        Assert.assertEquals(expectedDepartment, departments.get(0));
    }

    @Test
    public void getDepartmentByNameTest(){
        Department actualDepartment = departmentDAO.getDepartmentById(33L);
        Department expectedDepartment = departmentDAO.getDepartmentByName("Sales");
        Assert.assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    public void updateDepartmentTest(){

        Department department = departmentDAO.getDepartmentById(26L);
        department.setName("Purchases");
        department.setManager(employeeDao.getEmployeeById(32L));
        departmentDAO.updateDepartment(department);

        Department actualDepartment = departmentDAO.getDepartmentByName("Purchases");
        Department expectedDepartment = department;
        Assert.assertEquals(expectedDepartment, actualDepartment);
    }

//    @Test
//    public void deleteDepartmentTest(){
//        Department department = departmentDAO.getDepartmentById(4L);
//        departmentDAO.deleteDepartment(department);
//        List<Department> departments = departmentDAO.getAll();
//        Assert.assertTrue(departments.isEmpty());
//    }

    @Test
    public void deleteDepartmentTest1(){
        List<Department> departments = departmentDAO.getAll();
        int size = departments.size();
        Department department = departmentDAO.getDepartmentById(27L);
        departmentDAO.deleteDepartment(department);
        departments = departmentDAO.getAll();
        Assert.assertEquals(size - 1, departments.size());
    }

    @Test
    public void getAllDepartmentsTest() {

//        Department department = new Department();
//        department.setName("Marketing");
//        department.setManager(employeeDao.getEmployeeById(32L));
//        departmentDAO.createDepartment(department);
//
//        Department department1 = new Department();
//        department1.setName("Accounting");
//        department1.setManager(employeeDao.getEmployeeById(32L));
//        departmentDAO.createDepartment(department1);

        List<Department> actualDepartments = departmentDAO.getAll();
        Assert.assertEquals(13, actualDepartments.size());
    }
}
