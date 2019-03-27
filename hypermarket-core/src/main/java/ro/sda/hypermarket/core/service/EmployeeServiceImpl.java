package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.EmployeeDao;
import ro.sda.hypermarket.core.entity.Employee;
import ro.sda.hypermarket.core.repository.EmployeeRepository;

import java.util.List;

@Service("employeeService")
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee create(Employee employee, boolean useHibernate) {
        if(useHibernate) {
            return employeeDao.createEmployee(employee);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Employee employee, boolean useHibernate) {
        if(useHibernate) {
            return employeeDao.updateEmployee(employee);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id, boolean useHibernate) {
        if(useHibernate) {
            return employeeDao.getEmployeeById(id);
        }
        return employeeRepository.findById(id);
    }

    @Override
    public Employee getEmployeeByName(String name, boolean useHibernate){
        if(useHibernate) {
            return employeeDao.getEmployeeByName(name);
        }
        return employeeRepository.findByName(name);
    }

    @Override
    public List<Employee> findAll(boolean useHibernate) {
        if(useHibernate) {
            return employeeDao.getAll();
        }
        return employeeRepository.findAll();
    }

    @Override
    public void delete(Employee employee, boolean useHibernate) {
        if(useHibernate) {
            employeeDao.deleteEmployee(employee);
        }
        employeeRepository.delete(employee);
    }
}
