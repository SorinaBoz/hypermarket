package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.Department;

import java.util.List;

public interface DepartmentDAO {

    Department createDepartment(Department department);

    Department getDepartmentById(Long id);

    List<Department> getAll();

    Department updateDepartment(Department department);

    void deleteDepartment(Department department);
}
