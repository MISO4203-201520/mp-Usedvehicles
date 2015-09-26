package co.edu.uniandes.csw.mpusedvehicle.dtos;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author d.jimenez13
 */
@XmlRootElement 
public class OfferDTO {
    
    private Long id;
    private String description;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
