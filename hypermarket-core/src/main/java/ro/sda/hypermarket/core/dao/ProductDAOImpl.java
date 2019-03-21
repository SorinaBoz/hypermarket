package ro.sda.hypermarket.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.Product;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Product createProduct(Product product) {
        sessionFactory.getCurrentSession().beginTransaction();
        getCurrentSession().save(product);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return getCurrentSession().load(Product.class, id);
    }

    @Override
    public List<Product> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<Product> criteriaQuery = session.getCriteriaBuilder().createQuery(Product.class);
        criteriaQuery.from(Product.class);
        List<Product> products = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return products;
    }

    @Override
    public Product updateProduct(Product product) {

        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Product product1 = getProductById(product.getId());
        sessionFactory.getCurrentSession().merge(product1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return product;
    }

    @Override
    public void deleteProduct(Product product) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        Product product1 = getProductById(product.getId());
        sessionFactory.getCurrentSession().delete(product1);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
