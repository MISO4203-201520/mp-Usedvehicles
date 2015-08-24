package co.edu.uniandes.csw.mpusedvehicle.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement 
public class ProductDTO {

    private Long id;
    private String name;
    private Integer price;
    private ProviderDTO provider;
    private VehicleDTO vehicle;
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

}
