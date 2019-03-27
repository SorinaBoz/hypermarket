package ro.sda.hypermarket.core.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.entity.ProductCategory;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
@Transactional
public class ProductCategoryImpl implements ProductCategoryDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public ProductCategory createProductCategory(ProductCategory productCategory) {
        sessionFactory.getCurrentSession().beginTransaction();
        getCurrentSession().save(productCategory);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        return productCategory;
    }

    @Override
    public ProductCategory getProductCategoryByName(String name) {
        Query query= sessionFactory.getCurrentSession().
                createQuery("from ProductCategory where name=:name");
        query.setParameter("name", name);
        ProductCategory productCategory = (ProductCategory) query.uniqueResult();
        return productCategory;
    }

    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return getCurrentSession().load(ProductCategory.class, id);
    }

    @Override
    public List<ProductCategory> getAll() {
        Session session = sessionFactory.openSession();
        CriteriaQuery<ProductCategory> criteriaQuery = session.getCriteriaBuilder().createQuery(ProductCategory.class);
        criteriaQuery.from(ProductCategory.class);

        List<ProductCategory> productCategories = session.createQuery(criteriaQuery).getResultList();
        session.close();

        return productCategories;
    }

    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        ProductCategory productCategory1 = getProductCategoryById(productCategory.getId());
        sessionFactory.getCurrentSession().merge(productCategory1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
        return productCategory;
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {
        Transaction tr = sessionFactory.getCurrentSession().beginTransaction();
        ProductCategory productCategory1 = getProductCategoryById(productCategory.getId());
        sessionFactory.getCurrentSession().delete(productCategory1);
        sessionFactory.getCurrentSession().flush();
        tr.commit();
    }
}
