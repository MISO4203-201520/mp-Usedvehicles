/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author dham
 */
@Entity
@NamedQueries(
{
        @NamedQuery(name = "PaymentMethodEntity.getPaymentMethods", 
                    query = "SELECT p FROM PaymentMethodEntity p")  
})
public class PaymentMethodEntity implements Serializable {
    
    @Id
    @GeneratedValue(generator = "PaymentMethod")
    private Long id;

    private String type;
    
    @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AgreementEntity> agreements;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AgreementEntity> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<AgreementEntity> agreements) {
        this.agreements = agreements;
    }
    
}
