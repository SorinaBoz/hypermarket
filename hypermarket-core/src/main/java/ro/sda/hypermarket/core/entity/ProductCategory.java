package ro.sda.hypermarket.core.entity;

import ro.sda.hypermarket.core.base.BaseEntity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products_categories", schema = "hypermarket")
public class ProductCategory extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name="manager_id")
    private Employee manager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductCategory)) return false;
        ProductCategory that = (ProductCategory) o;
        return super.getId() == (that.getId()) &&
                getName().equals(that.getName()) &&
                getManager().equals(that.getManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getManager());
    }

    @Override
    public String toString() {
        return "ProductCategoryDAO{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}
