package ro.sda.hypermarket.core.entity;

import ro.sda.hypermarket.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sale_product", schema = "hypermarket")
public class SaleProduct extends BaseEntity {

    @ManyToOne
    @JoinColumn (name = "product_id")
    private Product product;

    @Column (name = "quantity", length = 20, nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn (name = "sale_id")
    private Sale sales;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Sale getSales() {
        return sales;
    }

    public void setSales(Sale sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleProduct)) return false;
        SaleProduct that = (SaleProduct) o;
        return super.getId() == (that.getId()) &&
                getProduct().equals(that.getProduct()) &&
                getQuantity().equals(that.getQuantity()) &&
                getSales().equals(that.getSales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduct(), getQuantity(), getSales());
    }

    @Override
    public String toString() {
        return "SaleProduct{" +
                "id=" + getId() +
                ", product=" + product +
                ", quantity=" + quantity +
                ", sales=" + sales +
                '}';
    }
}
