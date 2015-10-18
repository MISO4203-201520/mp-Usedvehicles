/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.dtos;

import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@XmlRootElement
public class OrderDTO {
    
    private Long id;
    
    private String transactionId;
    
    private BigDecimal amount;
    
    private BigDecimal taxAmount;
    
    private BigDecimal amountWithTaxes;

    private String paymentMethod;
    
    private String orderStatus;
    
    @PodamExclude
    private List<CartItemDTO> cartItems;

    public OrderDTO() {
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

    public List<CartItemDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDTO> cartItems) {
        this.cartItems = cartItems;
    }
    
}
