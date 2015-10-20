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
    @NamedQuery(name = "Message.findAll", query = "SELECT m FROM MessageEntity m"),
    @NamedQuery(name = "Message.messagesByProvider", query = "SELECT m FROM MessageEntity m where m.provider.id= :idProvider AND m.idTypeMessage= 1 ORDER by m.date DESC"),
    @NamedQuery(name = "Message.messagesByClient", query = "SELECT m FROM MessageEntity m where m.client.id= :idClient AND m.idTypeMessage= 1 ORDER by m.date DESC"),
    @NamedQuery(name = "Message.getmessagesByProvider", query = "SELECT m FROM MessageEntity m where m.providerreceiver = :idProvider ORDER by m.date DESC"),
    @NamedQuery(name = "Message.getmessagesByClient", query = "SELECT m FROM MessageEntity m where m.clientreceiver = :idClient ORDER by m.date DESC")})
    
public class MessageEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "Message")
    private Long id;
    
    @ManyToOne
    private ClientEntity client;
    
    
    
    @ManyToOne
    private ProviderEntity provider;
    


    public String getReceivertype() {
        return receivertype;
    }

    public Integer getClientreceiver() {
        return clientreceiver;
    }

    public void setClientreceiver(Integer clientreceiver) {
        this.clientreceiver = clientreceiver;
    }

    public Integer getProviderreceiver() {
        return providerreceiver;
    }

    public void setProviderreceiver(Integer providerreceiver) {
        this.providerreceiver = providerreceiver;
    }

    public void setReceivertype(String receivertype) {
        this.receivertype = receivertype;
    }
    
    private Integer clientreceiver;
    
    private Integer providerreceiver;

    //1 - user question to provider 
    //2 - message between users
    private Integer idTypeMessage;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @ManyToOne
    private ProductEntity product;
    
    private String question;
    
    private String answer;
    
    private String subject;
    
    private String receivertype;

    public MessageEntity() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MessageEntity(Long id) {
        this.id = id;
    }

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
