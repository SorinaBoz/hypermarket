package ro.sda.hypermarket.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import ro.sda.hypermarket.core.entity.SaleProduct;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class SaleProductDAOImp implements SaleProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
    @Override
    public SaleProduct createSaleProduct(SaleProduct saleProduct) {
        getCurrentSession().save(saleProduct);
        return saleProduct;
    }

    @Override
    public SaleProduct getSaleProductById(Long id) {
        return getCurrentSession().load(SaleProduct.class, id);
    }

    @Override
    public List<SaleProduct> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<SaleProduct> criteriaQuery = session.getCriteriaBuilder().createQuery(SaleProduct.class);
        criteriaQuery.from(SaleProduct.class);

        List<SaleProduct> saleProducts = session.createQuery(criteriaQuery).getResultList();
        session.close();

        return saleProducts;
    }

    @Override
    public SaleProduct updateSaleProduct(SaleProduct saleProduct) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        SaleProduct saleProduct1 = getSaleProductById(saleProduct.getId());
        sessionFactory.getCurrentSession().merge(saleProduct1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return saleProduct;
    }

    @Override
    public void deleteSaleProduct(SaleProduct saleProduct) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        SaleProduct saleProduct1  = getSaleProductById(saleProduct.getId());
        sessionFactory.getCurrentSession().delete(saleProduct1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }

}
