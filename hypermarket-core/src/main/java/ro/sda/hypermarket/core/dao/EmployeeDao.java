package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    Employee createEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    Employee getEmployeeByName(String name);

    List<Employee> getAll();

    Employee updateEmployee (Employee employee);

    void deleteEmployee(Employee employee);
}
