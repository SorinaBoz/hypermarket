package ro.sda.hypermarket.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.sda.hypermarket.core.dao.SupplierDAO;
import ro.sda.hypermarket.core.entity.Supplier;
import ro.sda.hypermarket.core.repository.SupplierRepository;

import java.util.List;

@Service("supplierService") // use @Service in order to be able to make @autowired for supplierService
@Transactional(readOnly = true, rollbackFor = Exception.class) // tell for what to make rollback - for each exception
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierDAO supplierDAO;

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    @Transactional
    public Supplier create(Supplier supplier, boolean useHibernate) {
        if(useHibernate) {
            return supplierDAO.createSupplier(supplier);
        }
        return supplierRepository.save(supplier); // have all CRUD methods by extending JpaRepository
    }

    @Override
    public Supplier update(Supplier supplier, boolean useHibernate) {
        if(useHibernate) {
            return supplierDAO.updateSupplier(supplier);
        }
        return supplierRepository.save(supplier); // have all CRUD methods by extending JpaRepository
    }

    @Override
    public Supplier getSupplierById(Long id, boolean useHibernate) {
        if(useHibernate) {
            return supplierDAO.getSupplierById(id);
        }
        return  supplierRepository.findById(id);
    }

    @Override
    public Supplier getSupplierByName(String name, boolean useHibernate){
        if(useHibernate) {
            return supplierDAO.getSupplierByName(name);
        }
        return supplierRepository.findByName(name);
    }

    @Override
    public List<Supplier> findAll(boolean useHibernate) {

        if(useHibernate) {
            return supplierDAO.getAll();
        }

        return supplierRepository.findAll();
    }

    @Override
    public void delete(Supplier supplier, boolean useHibernate) {

        if(useHibernate) {
            supplierDAO.deleteSupplier(supplier);
        }

        supplierRepository.delete(supplier);
    }
}
