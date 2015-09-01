package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IMessageLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.MessageConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.MessageDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.MessageEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.MessagePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class MessageLogic implements IMessageLogic {

    @Inject private MessagePersistence persistence;

    /**
     * @generated
     */
    public int countMessages() {
        return persistence.count();
    }

    /**
     * @generated
     */
    public List<MessageDTO> getMessages(Integer page, Integer maxRecords) {
        return MessageConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated
     */
    public MessageDTO getMessage(Long id) {
        return MessageConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    public MessageDTO createMessage(MessageDTO dto) {
        MessageEntity entity = MessageConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return MessageConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public MessageDTO updateMessage(MessageDTO dto) {
        MessageEntity entity = persistence.update(MessageConverter.fullDTO2Entity(dto));
        return MessageConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public void deleteMessage(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    public List<MessageDTO> findByName(String name) {
        return MessageConverter.listEntity2DTO(persistence.findByName(name));
    }
}
