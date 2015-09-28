package co.edu.uniandes.csw.mpusedvehicle.dtos;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement 
public class CommentDTO {

    private Long id;
    private String description;
    private Date date;
    private ClientDTO client;
    private ProductDTO product;
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
    public String getDescription() {
        return description;
    }

    /**
     * @generated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @generated
     */
    public Date getDate() {
        return date;
    }

    /**
     * @generated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @generated
     */
    public ClientDTO getClient() {
        return client;
    }

    /**
     * @generated
     */
    public void setClient(ClientDTO client) {
        this.client = client;
    }

    /**
     * @generated
     */
    public ProductDTO getProduct() {
        return product;
    }

    /**
     * @generated
     */
    public void setProduct(ProductDTO product) {
        this.product = product;
    }

}
