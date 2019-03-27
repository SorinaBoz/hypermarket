package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.DepartmentDAO;
import ro.sda.hypermarket.core.entity.Department;
import ro.sda.hypermarket.core.repository.DepartmentRepository;

import java.util.List;
@Service("departmentService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public Department create(Department department, boolean useHibernate) {

        if(useHibernate) {
            return departmentDAO.createDepartment(department);
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department, boolean useHibernate) {
        if(useHibernate) {
            return departmentDAO.updateDepartment(department);
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id, boolean useHibernate) {
        if(useHibernate){
            return departmentDAO.getDepartmentById(id);
        }
        return departmentRepository.findById(id);
    }

    @Override
    public Department getDepartmentByName(String name, boolean useHibernate){
        if(useHibernate) {
            return departmentDAO.getDepartmentByName(name);
        }
        return departmentRepository.findByName(name);
    }

    @Override
    public List<Department> findAll(boolean useHibernate) {
        if(useHibernate) {
            return departmentDAO.getAll();
        }
        return departmentRepository.findAll();
    }

    @Override
    public void delete(Department department, boolean useHibernate) {
        if(useHibernate) {
            departmentDAO.deleteDepartment(department);
        }
        departmentRepository.delete(department);
    }
}
