/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author dham
 */
@Entity
@NamedQueries(
{
        @NamedQuery(name = "AgreementEntity.getPaymentAgreement", 
                    query = "SELECT p FROM AgreementEntity p")  
})
public class AgreementEntity implements Serializable {
    
    @Id
    @GeneratedValue(generator = "PaymentAgreement")
    private Long id;

    private String name;
    
    private String description;
    
    private String template;
    
    private String logo;
    
    @ManyToOne
    private PaymentMethodEntity paymentMethod;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    
    public PaymentMethodEntity getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
