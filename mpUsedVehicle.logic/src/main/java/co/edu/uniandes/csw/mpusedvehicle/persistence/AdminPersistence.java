package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.AdminEntity;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author d.jimenez13
 */
public class AdminPersistence extends CrudPersistence<AdminEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPersistence.class);

    public AdminPersistence() {
        this.entityClass = AdminEntity.class;
    }

    public AdminEntity getAdminByUserId(String userId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_id", userId);
            return this.executeSingleNamedQuery("Admin.getByUserId", params);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

}
