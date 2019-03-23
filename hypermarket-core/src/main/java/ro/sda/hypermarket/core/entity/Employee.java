package ro.sda.hypermarket.core.entity;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "employees", schema = "hypermarket")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "city", nullable = true)
    private String city;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
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
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId().equals(employee.getId()) &&
                getName().equals(employee.getName()) &&
                getSalary().equals(employee.getSalary()) &&
                getJobTitle().equals(employee.getJobTitle()) &&
                getCity().equals(employee.getCity()) &&
                Objects.equals(getManager(), employee.getManager());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSalary(), getJobTitle(), getCity(), getManager());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", jobTitle='" + jobTitle + '\'' +
                ", department=" + department +
                ", manager=" + manager +
                ", city='" + city + '\'' +
                '}';
    }
}