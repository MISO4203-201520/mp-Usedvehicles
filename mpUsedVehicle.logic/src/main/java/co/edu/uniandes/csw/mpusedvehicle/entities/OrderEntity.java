/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.entities;

import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad de orden de compra
 * @author estudiante
 */
@Entity
@NamedQueries(
{
        @NamedQuery(name = "OrderEntity.getOrdersByProvider", 
                    query = "SELECT distinct c.order FROM CartItemEntity c LEFT JOIN fetch c.order LEFT JOIN fetch c.product WHERE c.product.provider.id =(:provider_id) and c.order.id is not null"),
        @NamedQuery(name = "OrderEntity.getOrdersByClient", 
                    query = "SELECT c.order FROM CartItemEntity c LEFT JOIN fetch c.order WHERE c.client.id =(:client_id)")  
})
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
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
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
