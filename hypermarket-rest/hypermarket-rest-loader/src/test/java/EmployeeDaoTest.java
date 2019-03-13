import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.EmployeeDao;
import ro.sda.hypermarket.core.entity.Employee;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-config/spring-root.xml")
@Transactional
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;
    Employee employee = new Employee();

    @Test
    public void createEmployeeTest(){
        employee.setFirstName("Madalina");
        employee.setLastName("Mihai");
        employee.setCity("Iasi");
        employee.setSalary(5200D);
        employee.setJobTitle("Manager");
        employeeDao.createEmployee(employee);
        List<Employee> employees = employeeDao.getAll();
//        Assert.assertEquals(1, suppliers.size());
        Assert.assertEquals("Madalina", employees.get(0).getFirstName());
        Assert.assertEquals("Mihai", employees.get(0).getLastName());
    }

    @Test
    public void updateEmployeeTest() {
        Employee employee = employeeDao.getEmployeeById(1L);
        employee.setFirstName("Sorina");
        employee.setLastName("Boz");
        employee.setCity("Botosani");
        employee.setSalary(2100D);
        employee.setJobTitle("Manager");
        employeeDao.updateEmployee(employee);
        List<Employee> employees = employeeDao.getAll();
        Assert.assertEquals("Sorina", employees.get(0).getFirstName());
        Assert.assertEquals("Boz", employees.get(0).getLastName());
        Assert.assertEquals("Botosani", employees.get(0).getCity());
//        Assert.assertEquals(2100, employees.get(0).getSalary());
        Assert.assertEquals("Manager", employees.get(0).getJobTitle());
    }

    @Test
    public void getEmployeeByIdTest(){
        Employee employee = new Employee();
        employeeDao.getEmployeeById(1L);

        List<Employee> employees = employeeDao.getAll();
        Assert.assertEquals("Madalina", employees.get(0).getFirstName());
        Assert.assertEquals("Mihai", employees.get(0).getLastName());
        Assert.assertEquals("Iasi", employees.get(0).getCity());
//        Assert.assertEquals(5200, employees.get(0).getSalary());
        Assert.assertEquals("Manager", employees.get(0).getJobTitle());
    }

    @Test
    public void deleteEmployeeTest(){
        Employee employee = employeeDao.getEmployeeById(3L);
        employeeDao.deleteEmployee(employee);
        List<Employee> employees = employeeDao.getAll();
        Assert.assertTrue (employees.isEmpty());
    }

    @Test
    public void deleteEmployeeTest1(){
        List<Employee> employees = employeeDao.getAll();
        int size = employees.size();
        Employee employee = employeeDao.getEmployeeById(6L);
        employeeDao.deleteEmployee(employee);
        employees = employeeDao.getAll();
        Assert.assertEquals(size - 1, employees.size());
    }

    @Test
    public void getAllEmployeesTest(){
        Employee employee = new Employee();
        employee.setFirstName("Cristiana");
        employee.setLastName("Turcanu");
        employee.setCity("Iasi");
        employee.setSalary(3200D);
        employee.setJobTitle("Cashier");
        employeeDao.createEmployee(employee);

        Employee employee1 = new Employee();
        employee1.setFirstName("Lili");
        employee1.setLastName("Popescu");
        employee1.setCity("Targu Neamt");
        employee1.setSalary(2100D);
        employee1.setJobTitle("Cashier");
        employeeDao.createEmployee(employee1);
        List<Employee> employees = employeeDao.getAll();
        Assert.assertEquals(2, employees.size());
    }

}
