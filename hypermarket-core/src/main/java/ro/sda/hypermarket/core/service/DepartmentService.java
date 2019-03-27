package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department create(Department department, boolean useHibernate);

    Department update(Department department, boolean useHibernate);

    Department getDepartmentById(Long id, boolean useHibernate);

    Department getDepartmentByName(String name, boolean useHibernate);

    List<Department> findAll(boolean useHibernate);

    void delete(Department department, boolean useHibernate);
}
