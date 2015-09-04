/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author hl.murcia222
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM MessageEntity m")})
public class MessageEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "Message")
    private Long id;
    
    @ManyToOne
    private ClientEntity client;
    
    @ManyToOne
    private ProviderEntity provider;

    //1 - user question to provider
    private Integer idTypeMessage;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @ManyToOne
    private ProductEntity product;
    
    private String question;

    public MessageEntity() {
    }

    public MessageEntity(Long id) {
        this.id = id;
    }

//    public MessageEntity(Long id, ClientEntity client, ProviderEntity provider, Integer idTypeMessage, Date date) {
//        this.id = id;
//        this.client = client;
//        this.provider = provider;
//        this.idTypeMessage = idTypeMessage;
//        this.date = date;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public ProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(ProviderEntity provider) {
        this.provider = provider;
    }

    public Integer getIdTypeMessage() {
        return idTypeMessage;
    }

    public void setIdTypeMessage(Integer idTypeMessage) {
        this.idTypeMessage = idTypeMessage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEntity)) {
            return false;
        }
        MessageEntity other = (MessageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.edu.uniandes.csw.mpusedvehicle.entities.Message[ id=" + id + " ]";
    }
    
}
