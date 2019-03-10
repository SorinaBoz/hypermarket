package ro.sda.hypermarket.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ro.sda.hypermarket.core.entity.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class SupplierDAOImpl implements SupplierDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        getCurrentSession().save(supplier);
        return supplier;

    }

    @Override
    public Supplier getSupplierById(Long id) {
        return getCurrentSession().load(Supplier.class, id);

    }

    @Override
    public List<Supplier> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Supplier> criteriaQuery = session.getCriteriaBuilder().createQuery(Supplier.class);
        criteriaQuery.from(Supplier.class);

        List<Supplier> suppliers = session.createQuery(criteriaQuery).getResultList();
        session.close();

        return suppliers;

    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Supplier supplier1 = getSupplierById(supplier.getId());
        sessionFactory.getCurrentSession().merge(supplier1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return supplier;

    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Supplier supplier1 = getSupplierById(supplier.getId());
        sessionFactory.getCurrentSession().delete(supplier1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }



}
