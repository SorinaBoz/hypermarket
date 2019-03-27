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
public class EmployeeDaoTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Test
    @Rollback(false)
    @Transactional
    public void createEmployeeTest(){
        Employee employee = new Employee();
        Department department = new Department();
        department.setName("Sales");
        departmentService.create(department, false);
        employee.setName("Roxana Preda");
        employee.setSalary(5200D);
        employee.setJobTitle("Consultant");
        employee.setCity("Botosani");
        employee.setDepartment(department);
        employee.setManager(employeeService.getEmployeeById(32L, false));
        employeeService.create(employee, false);
        Assert.assertNotNull(employee);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void updateEmployeeTest() {
        Employee employee = employeeService.getEmployeeById(36L, false);
        employee.setName("Adriadna Lungu");
        employee.setSalary(240D);
        employee.setJobTitle("Manager");
        employee.setCity("Iasi");
        employee.setManager(employeeService.getEmployeeById(32L, false));
        employeeService.update(employee, false);
        Department department = new Department();
        employee.setDepartment(department);
        department.setName("Sales");
        departmentService.create(department, false);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getEmployeeByIdTest(){
        Employee expectedEmployee = employeeService.getEmployeeById(32L, false);
        List<Employee> employees = employeeService.findAll(false);
        Assert.assertEquals(expectedEmployee, employees.get(0));
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getEmployeeByNameTest(){
        Employee actualEmployee = employeeService.getEmployeeById(36L, false);
        Employee expectedEmployee = employeeService.getEmployeeByName("Adriadna Lungu", false);
        Assert.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    @Rollback(false)
    @Transactional
    public void deleteEmployeeTest(){
        List<Employee> employees = employeeService.findAll(false);
        int size = employees.size();
        Employee employee = employeeService.getEmployeeById(37L, false);
        employeeService.delete(employee, false);
        employees = employeeService.findAll(false);
        Assert.assertEquals(size - 1, employees.size());
    }

    @Test
    @Rollback(false)
    @Transactional
    public void getAllEmployeesTest(){
        List<Employee> actualEmployees = employeeService.findAll(false);
        Assert.assertEquals(6, actualEmployees.size());

    }
}
