<<<<<<< HEAD
package co.edu.uniandes.csw.mpusedvehicle.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement 
public class ProductDTO {

    private Long id;
    private String name;
    private Integer price;
    private Boolean availability;
    @PodamExclude
    private ProviderDTO provider;
    @PodamExclude
    private VehicleDTO vehicle;
    @PodamExclude
    private List<CommentDTO> comments;
    private Integer discount;   //Nuevo Atributo REQ06
    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @generated
     */
    public ProviderDTO getProvider() {
        return provider;
    }

    /**
     * @generated
     */
    public void setProvider(ProviderDTO provider) {
        this.provider = provider;
    }

    /**
     * @generated
     */
    public VehicleDTO getVehicle() {
        return vehicle;
    }

    /**
     * @generated
     */
    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
    
    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    
    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    /**
     * REQ06 Nuevo atributo
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * REQ06 Nuevo atributo
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

}
=======
package co.edu.uniandes.csw.mpusedvehicle.dtos;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement 
public class ProductDTO {

    private Long id;
    private String name;
    private Integer price;
    private Boolean availability;
    @PodamExclude
    private ProviderDTO provider;
    @PodamExclude
    private VehicleDTO vehicle;
    @PodamExclude
    private List<CommentDTO> comments;
    private Integer discount;   //Nuevo Atributo REQ06
    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @generated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @generated
     */
    public ProviderDTO getProvider() {
        return provider;
    }

    /**
     * @generated
     */
    public void setProvider(ProviderDTO provider) {
        this.provider = provider;
    }

    /**
     * @generated
     */
    public VehicleDTO getVehicle() {
        return vehicle;
    }

    /**
     * @generated
     */
    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
    
    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
    
    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    /**
     * REQ06 Nuevo atributo
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * REQ06 Nuevo atributo
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

}
>>>>>>> refs/remotes/origin/master
