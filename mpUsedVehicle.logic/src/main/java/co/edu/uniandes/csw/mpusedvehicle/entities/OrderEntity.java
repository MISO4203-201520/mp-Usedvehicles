/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.entities;

import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entidad de orden de compra
 * @author estudiante
 */
@Entity
@NamedQueries(
{
        @NamedQuery(name = "OrderEntity.getOrdersByProvider", 
                    query = "SELECT c.order FROM CartItemEntity c LEFT JOIN fetch c.order LEFT JOIN fetch c.product WHERE c.product.provider=(:provider_id)"),
        @NamedQuery(name = "OrderEntity.getOrdersByClient", 
                    query = "SELECT c.order FROM CartItemEntity c LEFT JOIN fetch c.order WHERE c.client.id=(:client_id)")  
})
//@NamedQueries(
//{
//        @NamedQuery(name = "OrderEntity.getOrdersByProvider", 
//                    query = "SELECT o FROM OrderEntity o INNER JOIN ( SELECT c.product.id, c.order.id, p.provider.id FROM ProductEntity p INNER JOIN CartItemEntity c ON p.id=c.product.id) a ON o.id=a.order.id WHERE a.provider.id=(:id)"),
//        @NamedQuery(name = "OrderEntity.getOrdersByClient", 
//                    query = "SELECT o FROM OrderEntity o INNER JOIN CartItemEntity c ON o.id=c.order.id WHERE c.client.id=(:id)")  
//})
public class OrderEntity implements Serializable {
    
    @Id
    @GeneratedValue(generator = "OrderEntity")
    private Long id;
    
    private String transactionId;
    
    private BigDecimal amount;
    
    private BigDecimal taxAmount;
    
    private BigDecimal amountWithTaxes;

    private String paymentMethod;
    
    private String orderStatus;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItemEntity> cartItems;
    
    public OrderEntity() {
        this.orderStatus = OrderStatus.NEW.name();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getAmountWithTaxes() {
        return amountWithTaxes;
    }

    public void setAmountWithTaxes(BigDecimal amountWithTaxes) {
        this.amountWithTaxes = amountWithTaxes;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }   
    
}
