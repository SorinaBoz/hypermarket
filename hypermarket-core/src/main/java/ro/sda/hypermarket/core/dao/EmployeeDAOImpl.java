package ro.sda.hypermarket.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.Employee;

import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDao{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        sessionFactory.getCurrentSession().beginTransaction();
        getCurrentSession().save(employee);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        return employee;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return getCurrentSession().load(Employee.class, id);
    }

    @Override
    public Employee getEmployeeByName(String name) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("FROM Employee WHERE name=:name");
        query.setParameter("name", name);
        Employee employee = (Employee) query.uniqueResult();
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Employee> criteriaQuery = session.getCriteriaBuilder().createQuery(Employee.class);
        criteriaQuery.from(Employee.class);

        List<Employee> employees = session.createQuery(criteriaQuery).getResultList();
        session.close();

        return employees;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Employee employee1 = getEmployeeById(employee.getId());
        sessionFactory.getCurrentSession().merge(employee1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return employee;
    }

    @Override
    public void deleteEmployee(Employee employee) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Employee employee1 = getEmployeeById(employee.getId());
        sessionFactory.getCurrentSession().delete(employee1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }
}
