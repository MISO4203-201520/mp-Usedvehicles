package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.ClientEntity;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class ClientPersistence extends CrudPersistence<ClientEntity> {

    /**
     * @generated
     */
    public ClientPersistence() {
        this.entityClass = ClientEntity.class;
    }
    
    public ClientEntity getClientByUserId(String userId){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_id", userId);
            return this.executeSingleNamedQuery("Client.getByUserId", params);
        } catch (NoResultException e) {
            LOGGER.severe(e.getMessage());
            return null;
        }
    }
}
