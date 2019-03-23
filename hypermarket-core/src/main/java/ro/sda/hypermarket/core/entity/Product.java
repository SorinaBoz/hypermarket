package ro.sda.hypermarket.core.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products", schema = "hypermarket")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "supplier_price", nullable = false)
    private Float supplierPrice;

    @Column(name = "stock", nullable = false)
    private Long stock;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

    @Column(name = "vending_price", nullable = false)
    private Float vendingPrice;

    @OneToMany (mappedBy = "product")
    private Set<SaleProduct> saleProductSet;

    public Set<SaleProduct> getSaleProductSet() {
        return saleProductSet;
    }

    public void setSaleProductSet(Set<SaleProduct> saleProductSet) {
        this.saleProductSet = saleProductSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(Float supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Float getVendingPrice() {
        return vendingPrice;
    }

    public void setVendingPrice(Float vendingPrice) {
        this.vendingPrice = vendingPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId().equals(product.getId()) &&
                getName().equals(product.getName()) &&
                getSupplierPrice().equals(product.getSupplierPrice()) &&
                getStock().equals(product.getStock()) &&
                getVendingPrice().equals(product.getVendingPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSupplierPrice(), getStock(), getVendingPrice());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", supplierPrice=" + supplierPrice +
                ", stock=" + stock +
                ", supplier=" + supplier +
                ", productCategory=" + productCategory +
                ", vendingPrice=" + vendingPrice +
                ", saleProductSet=" + saleProductSet +
                '}';
    }
}