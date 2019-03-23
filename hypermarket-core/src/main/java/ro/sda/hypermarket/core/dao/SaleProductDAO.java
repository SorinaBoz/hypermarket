package ro.sda.hypermarket.core.dao;

import ro.sda.hypermarket.core.entity.SaleProduct;

import java.util.List;

public interface SaleProductDAO {

    SaleProduct createSaleProduct(SaleProduct saleProduct);
    SaleProduct getSaleProductById(Long id);
    List<SaleProduct> getAll();
    SaleProduct updateSaleProduct (SaleProduct saleProduct);
    void deleteSaleProduct(SaleProduct saleProduct);
}
