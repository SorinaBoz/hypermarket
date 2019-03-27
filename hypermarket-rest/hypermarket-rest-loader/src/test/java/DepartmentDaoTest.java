import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Department;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.service.DepartmentService;
import ro.sda.hypermarket.core.service.EmployeeService;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class DepartmentDaoTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Test
    @Rollback(false)
    @Transactional
    public void createDepartmentTest() {

        Department department = new Department();
        Employee employee = new Employee();
        department.setName("Management");
        department.setManager(employeeService.getEmployeeById(32L, false));
        departmentService.create(department, false);

        employee.setName("Maria Ionescu");
        employee.setCity("Cluj");
        employee.setSalary(45D);
        employee.setJobTitle("Saler");
        employeeService.create(employee, false);
        Assert.assertNotNull(department);
    }

    @Test
    public void updateDepartmentTest(){

        Department department = departmentService.getDepartmentById(38L, false);
        department.setName("Purchases");
        department.setManager(employeeService.getEmployeeById(32L, false));
        departmentService.update(department, false);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getDepartmentByIdTest() {
        Department expectedDepartment = departmentService.getDepartmentById(24L, false);
        List<Department> departments = departmentService.findAll(false);
        Assert.assertEquals(expectedDepartment, departments.get(0));
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getDepartmentByNameTest(){
        Department actualDepartment = departmentService.getDepartmentById(42L, false);
        Department expectedDepartment = departmentService.getDepartmentByName("Publicity", false);
        Assert.assertEquals(expectedDepartment, actualDepartment);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteDepartmentTest(){
        List<Department> departments = departmentService.findAll(false);
        int size = departments.size();
        Department department = departmentService.getDepartmentById(28L, false);
        departmentService.delete(department, false);
        departments = departmentService.findAll(false);
        Assert.assertEquals(size - 1, departments.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllDepartmentsTest() {

        List<Department> actualDepartments = departmentService.findAll(false);
        Assert.assertEquals(16, actualDepartments.size());
    }
}
