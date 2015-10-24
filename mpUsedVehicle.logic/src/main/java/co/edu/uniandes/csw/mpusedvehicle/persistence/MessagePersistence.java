package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.MessageEntity;
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
public class MessagePersistence extends CrudPersistence<MessageEntity> {

    private static Logger LOGGER = LoggerFactory.getLogger(MessagePersistence.class);
    /**
     * @generated
     */
    public MessagePersistence() {
        this.entityClass = MessageEntity.class;
    }
    
    //Para Obtener la lista de mensajes para un proveedor desarrollado por Miguel Olivares
    public List<MessageEntity> getmessagesByProvider(Integer idProvider) {
    {
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idProvider",idProvider);
            return  executeListNamedQuery("Message.getmessagesByProvider", params);
            } catch(NoResultException e){
                LOGGER.error(e.getMessage(),e);
                return null;
            }
        }
    }
    
    //Para Obtener la lista de mensajes para un cliente desarrollado por Miguel Olivares
    public List<MessageEntity> getmessagesByClient(Integer idClient) {
    {
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idClient",idClient);
            return  executeListNamedQuery("Message.getmessagesByClient", params);
            } catch(NoResultException e){
                LOGGER.error(e.getMessage(),e);
                return null;
            }
        }
    }
}