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
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDAO departmentDAO;

    @Test
    public void createEmployeeTest(){
        Employee employee = new Employee();
        Department department = new Department();
        department.setName("Sales");
        departmentDAO.createDepartment(department);
        employee.setName("Roxana Preda");
        employee.setSalary(5200D);
        employee.setJobTitle("Consultant");
        employee.setCity("Botosani");
        employee.setDepartment(department);
        employee.setManager(employeeDao.getEmployeeById(32L));
        employeeDao.createEmployee(employee);
        Employee actualEmployee = employeeDao.getEmployeeByName("Roxana Preda");
        Employee expectedEmployee = employee;
//        Department actualFromDB = employeeDao.getEmployeeById().getDepartment();
//        Department expected = employee.getDepartment();
        Assert.assertEquals(expectedEmployee, actualEmployee);
//        Assert.assertEquals(expected, actualFromDB);
    }

    @Test
    public void updateEmployeeTest() {
        Employee employee = employeeDao.getEmployeeById(33L);
        employee.setName("Roxana Preda");
        employee.setSalary(240D);
        employee.setJobTitle("Consultant");
        employee.setCity("Iasi");
        employee.setManager(employeeDao.getEmployeeById(32L));
        employeeDao.updateEmployee(employee);
        Department department = new Department();
        employee.setDepartment(department);
        department.setName("Sales");
        departmentDAO.createDepartment(department);

        Employee actualEmployee = employeeDao.getEmployeeByName("Roxana Preda");
        Employee expectedEmployee = employee;
        Assert.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void getEmployeeByIdTest(){
        Employee expectedEmployee = employeeDao.getEmployeeById(32L);
        List<Employee> employees = employeeDao.getAll();
        Assert.assertEquals(expectedEmployee, employees.get(0));
    }

    @Test
    public void getEmployeeByNameTest(){
        Employee actualEmployee = employeeDao.getEmployeeById(32L);
        Employee expectedEmployee = employeeDao.getEmployeeByName("Sorina Boz");
        Assert.assertEquals(expectedEmployee, actualEmployee);
    }

//    @Test
//    public void deleteEmployeeTest(){
//        Employee employee = employeeDao.getEmployeeById(28L);
//        employeeDao.deleteEmployee(employee);
//        List<Employee> employees = employeeDao.getAll();
//        Assert.assertTrue (employees.isEmpty());
//    }

    @Test
    public void deleteEmployeeTest1(){
        List<Employee> employees = employeeDao.getAll();
        int size = employees.size();
        Employee employee = employeeDao.getEmployeeById(29L);
        employeeDao.deleteEmployee(employee);
        employees = employeeDao.getAll();
        Assert.assertEquals(size - 1, employees.size());
    }

    @Test
    public void getAllEmployeesTest(){

        Employee employee = new Employee();
        employee.setName("Cristiana Turcanu");
        employee.setSalary(3200D);
        employee.setJobTitle("Cashier");
        employee.setCity("Cluj");
        employee.setManager(employeeDao.getEmployeeById(32L));
        employeeDao.createEmployee(employee);

        Department department = new Department();
        employee.setDepartment(department);
        department.setName("Marketing");
        departmentDAO.createDepartment(department);

        Employee employee1 = new Employee();
        employee1.setName("Lili Andronescu");
        employee1.setSalary(2100D);
        employee1.setJobTitle("Cashier");
        employee1.setCity("Oradea");
        employee1.setManager(employeeDao.getEmployeeById(32L));
        employeeDao.createEmployee(employee1);

        Department department1 = new Department();
        employee1.setDepartment(department1);
        department1.setName("Marketing");
        departmentDAO.createDepartment(department1);

        List<Employee> actualEmployees = employeeDao.getAll();
        Assert.assertEquals(4, actualEmployees.size());

    }
}
