package ro.sda.hypermarket.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Department;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Transactional
public class DepartmentDAOImpl implements DepartmentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Department createDepartment(Department department) {
        sessionFactory.getCurrentSession().beginTransaction();
        getCurrentSession().save(department);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        return department;
    }

    @Override
    public Department getDepartmentById(Long id) {
        return getCurrentSession().load(Department.class, id);
    }

    @Override
    public List<Department> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Department> criteriaQuery = session.getCriteriaBuilder().createQuery(Department.class);
        criteriaQuery.from(Department.class);
        List<Department> departments = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return departments;
    }

    @Override
    public Department updateDepartment(Department department) {

        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Department department1 = getDepartmentById(department.getId());
        sessionFactory.getCurrentSession().merge(department1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return department;
    }

    @Override
    public void deleteDepartment(Department department) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Department department1 = getDepartmentById(department.getId());
        sessionFactory.getCurrentSession().delete(department1);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
