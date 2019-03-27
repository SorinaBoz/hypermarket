package ro.sda.hypermarket.core.entity;

import ro.sda.hypermarket.core.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "suppliers", schema = "hypermarket")
public class Supplier extends BaseEntity {

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column (name = "contact_no", length = 20, nullable = false)
    private String contactNo;

    @Column(name = "city", length = 40, nullable = false)
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Supplier)) return false;
        Supplier supplier = (Supplier) o;
        return
                super.getId() == supplier.getId() &&
                getName().equals(supplier.getName()) &&
                getContactNo().equals(supplier.getContactNo()) &&
                getCity().equals(supplier.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getContactNo(), getCity(), getId());
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
