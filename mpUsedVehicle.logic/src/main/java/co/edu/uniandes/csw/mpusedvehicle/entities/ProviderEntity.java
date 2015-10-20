package co.edu.uniandes.csw.mpusedvehicle.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @generated
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Provider.getByUserId", query = "select u from ProviderEntity u WHERE u.userId = :user_id"),
    @NamedQuery(name = "Provider.getProviderByModel", query = "select pv from ProviderEntity pv, ProductEntity u WHERE  u.provider.id =  pv.id  AND UPPER(u.vehicle.model) like :model order by u.price"),
    @NamedQuery(name = "Provider.getProviderByBrand", query = "select pv from ProviderEntity pv, ProductEntity u WHERE u.provider.id =  pv.id  AND UPPER(u.vehicle.brand) like :brand order by u.price"),
    @NamedQuery(name = "Provider.getProviderByCity", query = "select pv from ProviderEntity pv WHERE UPPER(pv.city) like :city "),
    @NamedQuery(name = "Provider.getProviderByPriceRange", query = "select pv from ProviderEntity pv, ProductEntity u WHERE u.provider.id =  pv.id  AND u.price > :lower and  u.price < :upper "),
    @NamedQuery(name = "Provider.getProviders", query = "select u from ProviderEntity u"),
    @NamedQuery(name = "Provider.getById", query = "select u from ProviderEntity u WHERE u.id = :id")
})
public class ProviderEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Provider")
    private Long id;

    private String name;

    private String userId;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> products;
    
    private String email;
    
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    /**
     * @generated
     */
    public Long getId(){
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName(){
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @generated
     */
    public String getUserId(){
        return userId;
    }

    /**
     * @generated
     */
    public void setUserId(String userId){
        this.userId = userId;
    }

    /**
     * @generated
     */
    public List<ProductEntity> getProducts() {
        return products;
    }

    /**
     * @generated
     */
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
