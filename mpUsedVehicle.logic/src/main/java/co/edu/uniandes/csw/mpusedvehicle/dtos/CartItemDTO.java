package co.edu.uniandes.csw.mpusedvehicle.dtos;

import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement 
public class CartItemDTO {

    private Long id;
    private String name;
    private Integer quantity;
    private Integer installments;
    @PodamExclude
    private ClientDTO client;
    @PodamExclude
    private ProductDTO product;
    @PodamExclude
    private OrderDTO order;
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
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @generated
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    /**
     * @generated
     */
    public Integer getInstallments() {
        return installments;
    }

    /**
     * @generated
     */
    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
    
    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

}
