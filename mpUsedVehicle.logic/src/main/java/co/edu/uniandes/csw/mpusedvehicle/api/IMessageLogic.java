package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.MessageDTO;
import java.util.List;

public interface IMessageLogic {
    public int countMessages();
    public List<MessageDTO> getMessages(Integer page, Integer maxRecords);
    public List<MessageDTO> getQuestionsByProvider(Long idProvider);
    public List<MessageDTO> getQuestionsByUser(Long idUser);
    public MessageDTO getMessage(Long id);
    public MessageDTO createMessage(MessageDTO dto);
    public MessageDTO createNewMessage(MessageDTO dto);
    public MessageDTO updateMessage(MessageDTO dto);
    public void deleteMessage(Long id);
    public List<MessageDTO> findByName(String name);
    public List<MessageDTO> getmessagesByProvider(Integer idProvider);
    public List<MessageDTO> getmessagesByClient(Integer idUser);
}
