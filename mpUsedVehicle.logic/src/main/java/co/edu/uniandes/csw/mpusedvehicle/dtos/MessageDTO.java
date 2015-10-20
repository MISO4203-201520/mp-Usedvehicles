package co.edu.uniandes.csw.mpusedvehicle.dtos;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement 
public class MessageDTO {

    private Long id;
    private ClientDTO client;
    private Integer clientreceiver;
    private ProviderDTO provider;
    private Integer providerreceiver;
    private Integer idTypeMessage;
    private Date date;
    private ProductDTO product;
    public String receivertype;
    public String question;
    private String answer;
    public String subject;

    public String getReceivertype() {
        return receivertype;
    }

    public void setReceivertype(String receivertype) {
        this.receivertype = receivertype;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public ProviderDTO getProvider() {
        return provider;
    }

    public void setProvider(ProviderDTO provider) {
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
}
