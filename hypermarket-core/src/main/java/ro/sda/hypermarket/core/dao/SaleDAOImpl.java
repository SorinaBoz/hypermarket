package ro.sda.hypermarket.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Sale;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Transactional
public class SaleDAOImpl implements SaleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Sale createSale(Sale sale) {
        getCurrentSession().save(sale);
        return sale;
    }

    @Override
    public Sale getSaleByNumber(Long number) {
        Query query= sessionFactory.getCurrentSession().
                createQuery("from Sale where number=:number");
        query.setParameter("number", number);
        Sale sale = (Sale) query.uniqueResult();
        return sale;
    }

    @Override
    public Sale getSaleById(Long id) {
        return getCurrentSession().load(Sale.class, id);
    }

    @Override
    public List<Sale> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Sale> criteriaQuery = session.getCriteriaBuilder().createQuery(Sale.class);
        criteriaQuery.from(Sale.class);

        List<Sale> sales = session.createQuery(criteriaQuery).getResultList();
        session.close();

        return sales;
    }

    @Override
    public Sale updateSale(Sale sale) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Sale sale1 = getSaleById(sale.getId());
        sessionFactory.getCurrentSession().merge(sale1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return sale;
    }

    @Override
    public void deleteSale(Sale sale) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Sale sale1 = getSaleById(sale.getId());
        sessionFactory.getCurrentSession().delete(sale1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }
}
