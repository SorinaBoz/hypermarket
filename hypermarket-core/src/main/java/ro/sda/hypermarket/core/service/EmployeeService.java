package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee create(Employee employee, boolean useHibernate);

    Employee update(Employee employee, boolean useHibernate);

    Employee getEmployeeById(Long id, boolean useHibernate);

    Employee getEmployeeByName(String name, boolean useHibernate);

    List<Employee> findAll(boolean useHibernate);

    void delete(Employee employee, boolean useHibernate);
}
