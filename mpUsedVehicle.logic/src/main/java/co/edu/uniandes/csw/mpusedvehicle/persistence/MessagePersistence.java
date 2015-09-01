package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.MessageEntity;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class MessagePersistence extends CrudPersistence<MessageEntity> {

    /**
     * @generated
     */
    public MessagePersistence() {
        this.entityClass = MessageEntity.class;
    }
}
