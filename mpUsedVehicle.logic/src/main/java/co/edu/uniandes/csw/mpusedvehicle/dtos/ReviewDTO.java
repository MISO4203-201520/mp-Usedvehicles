package co.edu.uniandes.csw.mpusedvehicle.dtos;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author d.jimenez13
 */
@XmlRootElement 
public class ReviewDTO {
    
    private Long id;
    private String name;
    private String description;
    private String source;
    private VehicleDTO vehicle;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}
