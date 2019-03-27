package ro.sda.hypermarket.core.repository;
import ro.sda.hypermarket.core.base.EntityRepository;
import ro.sda.hypermarket.core.entity.Supplier;

public interface SupplierRepository extends EntityRepository <Supplier> {

    public Supplier findByName(String name);

//    @Query (value = "aici e query-ul meu HQL") - te folosesti de numele campurilor din clase (HQL)
//    public List<Supplier> unNumeDeMetoda(String param1, int param2);

//    @Query (value = "aici e query-ul meu SQL", native = true)!!!
//    public List<Supplier> unNumeDeMetoda(String param1, int param2);

}
