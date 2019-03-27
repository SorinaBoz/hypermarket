package ro.sda.hypermarket.core.service;

import ro.sda.hypermarket.core.entity.Supplier;

import java.util.List;

public interface SupplierService {

    Supplier create(Supplier supplier, boolean useHibernate);

    Supplier update(Supplier supplier, boolean useHibernate);

    Supplier getSupplierById(Long id, boolean useHibernate);

    Supplier getSupplierByName(String name, boolean useHibernate);

    List<Supplier> findAll(boolean useHibernate);

    void delete(Supplier supplier, boolean useHibernate);
}
