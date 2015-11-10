package co.edu.uniandes.csw.mpusedvehicle.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * @generated
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Product.getByVehicleName", query = "select u from ProductEntity u WHERE UPPER(u.vehicle.name) like :name"),
    @NamedQuery(name = "Product.getVehiclesName", query = "select distinct u.vehicle.name from ProductEntity u"),
    @NamedQuery(name = "Product.getVehiclesBrand", query = "select distinct u.vehicle.brand from ProductEntity u"),
    @NamedQuery(name = "Product.getVehiclesCapacity", query = "select distinct u.vehicle.capacity from ProductEntity u"),
    @NamedQuery(name = "Product.getVehiclesColor", query = "select distinct u.vehicle.color from ProductEntity u"),
    @NamedQuery(name = "Product.getVehiclesModel", query = "select distinct u.vehicle.model from ProductEntity u"),
    @NamedQuery(name = "Product.getVehiclesPlate", query = "select distinct u.vehicle.plate from ProductEntity u"),
    @NamedQuery(name = "Product.getVehiclesLocation", query = "select distinct u.vehicle.location from ProductEntity u"),
    @NamedQuery(name = "Product.getCheaperProductByProvider", query = "select u from ProductEntity u WHERE UPPER(u.provider.name) like :nameProvider order by u.price"),
    @NamedQuery(name = "Product.getProductByProvider", query = "select u from ProductEntity u WHERE UPPER(u.provider.name) like :nameProvider order by u.price"),
    @NamedQuery(name = "Product.getCheaperProductByVehicle", query = "select u from ProductEntity u WHERE UPPER(u.vehicle.name) like :nameVehicle order by u.price")
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
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments;
    private Integer discount;   //Nuevo Atributo REQ06
    
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
    
    
    /**
     * REQ06 Nuevo atributo
     */
    public Integer getDiscount() {
        return discount;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments = comments;
    }

    /**
     * REQ06 Nuevo atributo
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
    
}
