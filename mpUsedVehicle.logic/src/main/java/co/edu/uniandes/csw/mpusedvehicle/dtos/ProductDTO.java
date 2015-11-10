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
     * Cantidad de votos realizados sobre un producto
     */
    private Integer ammountVotes;
    /**
     * Calificacion promedio del producto
     */
    private Float rating;
    /**
     * Lista de clientes que han comprado el producto
     */
    private List<ClientDTO> purchasedBy;

    
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
    /**
     * Metodo que obtiene la cantidad de votos de la calificacion.
     * @return cantidad de votos
     */
    public Integer getAmmountVotes() {
        return ammountVotes;
    }
    /**
     * Metodo que actualiza la cantida de votos.
     * @param ammountVotes. Nueva cantidad de votos.
     */
    public void setAmmountVotes(Integer ammountVotes) {
        this.ammountVotes = ammountVotes;
    }
    /**
     * Metodod que obtiene el valor de la calificacion del producto.
     * @return float con la calificacion del producto.
     */
    public Float getRating() {
        return rating;
    }
    /**
     * Metodo que actualiza la calificacion del producto.
     * @param rating. Calificacion del nuevo producto.
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }
    /**
     * Metodo que obtiene la lista de clientes que han comprado el producto
     * @return Lista de cleintes
     */
    public List<ClientDTO> getPurchasedBy() {
        return purchasedBy;
    }
    /**
     * Metodo que actualiza la lista de clientes que han comprado el producto
     * @param purchasedBy Lista. Nueva lista de clientes.
     */
    public void setPurchasedBy(List<ClientDTO> purchasedBy) {
        this.purchasedBy = purchasedBy;
    }
}