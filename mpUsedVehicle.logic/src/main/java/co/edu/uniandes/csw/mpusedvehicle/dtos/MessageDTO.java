package co.edu.uniandes.csw.mpusedvehicle.dtos;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @generated
 */
@XmlRootElement 
public class MessageDTO {

    private Long id;
    private Integer idUserSource;
    private Integer idUserTarget;
    private Integer idTypeMessage;
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIdUserSource() {
        return idUserSource;
    }

    public void setIdUserSource(Integer idUserSource) {
        this.idUserSource = idUserSource;
    }

    public Integer getIdUserTarget() {
        return idUserTarget;
    }

    public void setIdUserTarget(Integer idUserTarget) {
        this.idUserTarget = idUserTarget;
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

    

}
