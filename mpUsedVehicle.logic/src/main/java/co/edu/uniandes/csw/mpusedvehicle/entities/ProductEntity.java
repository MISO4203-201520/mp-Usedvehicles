package co.edu.uniandes.csw.mpusedvehicle.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 * @generated
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Product.getByVehicleName", query = "select u from ProductEntity u WHERE UPPER(u.vehicle.name) like :name"),
    @NamedQuery(name = "Product.getCheaperProductByProvider", query = "select u from ProductEntity u WHERE UPPER(u.provider.name) like :nameProvider order by u.price"),
    @NamedQuery(name = "Product.getCheaperProductByVehicle", query = "select u from ProductEntity u WHERE UPPER(u.vehicle.name) like :nameVehicle order by u.price"),
    @NamedQuery(name = "Product.getOfferedProductsByKeyword", 
            query = "select p from ProductEntity p where p.name like :keyword and :fechaActual between p.offer.startDate and p.offer.endDate order by p.price")
})
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Product")
    private Long id;

    private String name;

    private Integer price;
    
    private Boolean availability;

    @ManyToOne
    private ProviderEntity provider;
    @ManyToOne
    private VehicleEntity vehicle;
    
    @OneToOne
    private OfferEntity offer;
    
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
    public Integer getPrice(){
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price){
        this.price = price;
    }

    /**
     * @generated
     */
    public ProviderEntity getProvider() {
        return provider;
    }

    /**
     * @generated
     */
    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    /**
     * @generated
     */
    public VehicleEntity getVehicle() {
        return vehicle;
    }

    /**
     * @generated
     */
    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }
    
    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public OfferEntity getOffer() {
        return offer;
    }

    public void setOffer(OfferEntity offer) {
        this.offer = offer;
    }

}
