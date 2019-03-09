package ro.sda.hypermarket.core.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="sales", schema = "hypermaket")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="number", length = 15, nullable = false)
    private String number;

    @Column (name = "sale_date", length = 20, nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client clientId;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employeeId;

    @OneToMany (mappedBy = "sales")
    private Set<SaleProduct> salesSet;

    public Set<SaleProduct> getSalesSet() {
        return salesSet;
    }

    public void setSalesSet(Set<SaleProduct> salesSet) {
        this.salesSet = salesSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equals(getId(), sale.getId()) &&
                Objects.equals(getNumber(), sale.getNumber()) &&
                Objects.equals(getDate(), sale.getDate()) &&
                Objects.equals(getClientId(), sale.getClientId()) &&
                Objects.equals(getEmployeeId(), sale.getEmployeeId()) &&
                Objects.equals(getSalesSet(), sale.getSalesSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getDate(), getClientId(), getEmployeeId(), getSalesSet());
    }

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", date=" + date +
                ", clientId=" + clientId +
                ", employeeId=" + employeeId +
                ", salesSet=" + salesSet +
                '}';
    }
}
