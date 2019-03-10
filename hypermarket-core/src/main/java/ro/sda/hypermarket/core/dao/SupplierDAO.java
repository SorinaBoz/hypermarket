package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.Supplier;

import java.util.List;

public interface SupplierDAO {

    Supplier createSupplier(Supplier supplier);

    Supplier getSupplierById(Long id);

    List<Supplier> getAll();

    Supplier updateSupplier (Supplier supplier);

    void deleteSupplier(Supplier supplier);

}
