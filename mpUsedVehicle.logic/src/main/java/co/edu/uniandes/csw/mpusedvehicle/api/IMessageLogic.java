package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.MessageDTO;
import java.util.List;

public interface IMessageLogic {
    public int countMessages();
    public List<MessageDTO> getMessages(Integer page, Integer maxRecords);
    public List<MessageDTO> getMessagesByProvider(Long idProvider);
    public List<MessageDTO> getMessagesByUser(Long idUser);
    public MessageDTO getMessage(Long id);
    public MessageDTO createMessage(MessageDTO dto);
    public MessageDTO updateMessage(MessageDTO dto);
    public void deleteMessage(Long id);
    public List<MessageDTO> findByName(String name);
}
