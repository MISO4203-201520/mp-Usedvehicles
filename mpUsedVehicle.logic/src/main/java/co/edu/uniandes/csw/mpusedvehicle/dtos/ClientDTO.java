package co.edu.uniandes.csw.mpusedvehicle.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @generated
 */
@XmlRootElement 
public class ClientDTO {

    public Long id;
    public String name;
    public String userId;
    private List<CartItemDTO> shoppingCart;
    private String email;
    
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
    public String getUserId() {
        return userId;
    }

    /**
     * @generated
     */
    public void setUserId(String userid) {
        this.userId = userid;
    }

    /**
     * @generated
     */
    public List<CartItemDTO> getShoppingCart() {
        return shoppingCart;
    }

    /**
     * @generated
     */
    public void setShoppingCart(List<CartItemDTO> shoppingcart) {
        this.shoppingCart = shoppingcart;
    }
    /**
     * Obtener email
     * @return email del cliente
     */
    public String getEmail() {
        return email;
    }
    /**
     * Modificar email
     * @param email . Nuevo email del cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
