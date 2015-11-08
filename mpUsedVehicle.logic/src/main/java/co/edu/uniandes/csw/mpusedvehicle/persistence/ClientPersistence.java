package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.ClientEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @generated
 */
@Stateless
public class ClientPersistence extends CrudPersistence<ClientEntity> {

    private static Logger LOGGER = LoggerFactory.getLogger(ClientPersistence.class);

    /**
     * @generated
     */
    public ClientPersistence() {
        this.entityClass = ClientEntity.class;
    }

    public ClientEntity getClientByUserId(String userId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_id", userId);
            return this.executeSingleNamedQuery("Client.getByUserId", params);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<ClientEntity> getIdANDUserName() {
        try {
            return executeListNamedQuery("Client.getIdANDUserName");
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ClientEntity getClientById(Long Id) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", Id);
            return this.executeSingleNamedQuery("Client.getById", params);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
