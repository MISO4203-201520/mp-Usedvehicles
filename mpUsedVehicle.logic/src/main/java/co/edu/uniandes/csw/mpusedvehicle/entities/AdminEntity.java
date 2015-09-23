package co.edu.uniandes.csw.mpusedvehicle.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author d.jimenez13
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Admin.getByUserId", query = "select a from AdminEntity a WHERE a.userId = :user_id")
})
public class AdminEntity implements Serializable {
    
    @Id
    @GeneratedValue(generator = "Admin")
    private Long id;
    private String name;
    private String userId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
