package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.Sale;

import java.util.List;

public interface SaleDAO {

    Sale createSale (Sale sale);

    Sale getSaleByNumber(Long number);

    Sale getSaleById(Long id);

    List<Sale> getAll();

    Sale updateSale (Sale sale);

    void deleteSale(Sale sale);
}
