package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IMessageLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.MessageConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.MessageDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.MessageEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.MessagePersistence;
import co.edu.uniandes.csw.mpusedvehicle.util.MailManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class MessageLogic implements IMessageLogic {

    @Inject
    private MessagePersistence persistence;

    /**
     * @generated @return
     */
    @Override
    public int countMessages() {
        return persistence.count();
    }

    /**
     * @generated @return
     */
    @Override
    public List<MessageDTO> getMessages(Integer page, Integer maxRecords) {
        return MessageConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     *
     * @param idProvider
     * @return
     */
    @Override
    public List<MessageDTO> getQuestionsByProvider(Long idProvider) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idProvider", idProvider);
        List<MessageEntity> list = persistence.executeListNamedQuery("Message.messagesByProvider", map);
        return MessageConverter.listEntity2DTO(list);
    }

    /**
     *
     * @param idUser
     * @return
     */
    @Override
    public List<MessageDTO> getQuestionsByUser(Long idUser) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idClient", idUser);
        List<MessageEntity> list = persistence.executeListNamedQuery("Message.messagesByClient", map);
        return MessageConverter.listEntity2DTO(list);
    }

    /**
     * @generated @return
     */
    @Override
    public MessageDTO getMessage(Long id) {
        return MessageConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated @return
     */
    @Override
    public MessageDTO createMessage(MessageDTO dto) {
        MessageEntity entity = MessageConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        //Send email
        String emailBody = "<h2>Hola " + dto.getProvider().getName() + ",</h2><br>"
                + "You have a new question about your " + entity.getProduct().getName() + " product<br>"
                + "Question: " + entity.getQuestion() + "<br>"
                + "Client: " + dto.getClient().getName();

        MailManager.generateAndSendEmail(emailBody, dto.getProvider().getEmail(), "You have a new question");
        return MessageConverter.fullEntity2DTO(entity);
    }

    /**
     * @param dto
     * @return
     * @return
     */
    @Override
    public MessageDTO createNewMessage(MessageDTO dto) {
        MessageEntity entity = MessageConverter.fullDTO2Entity(dto);
        persistence.create(entity);

        return MessageConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated @return
     */
    @Override
    public MessageDTO updateMessage(MessageDTO dto) {
        MessageEntity entity = persistence.update(MessageConverter.fullDTO2Entity(dto));
        return MessageConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated @return
     */
    @Override
    public void deleteMessage(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated @return
     */
    @Override
    public List<MessageDTO> findByName(String name) {
        return MessageConverter.listEntity2DTO(persistence.findByName(name));
    }

    /**
     *
     * @param idProvider
     * @return
     */
    @Override
    public List<MessageDTO> getmessagesByProvider(Integer idProvider) {
        return MessageConverter.listEntity2DTO(persistence.getmessagesByProvider(idProvider));
    }

    /**
     *
     * @param idClient
     * @return
     */
    @Override
    public List<MessageDTO> getmessagesByClient(Integer idClient) {
        return MessageConverter.listEntity2DTO(persistence.getmessagesByClient(idClient));
    }
}
