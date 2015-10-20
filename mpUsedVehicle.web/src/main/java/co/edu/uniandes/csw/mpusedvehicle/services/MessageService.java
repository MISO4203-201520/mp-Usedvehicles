package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.IMessageLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.MessageDTO;
import co.edu.uniandes.csw.mpusedvehicle.api.IClientLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.api.IProviderLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.providers.StatusCreated;
import java.sql.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @generated
 */
@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageService {

    @Inject private IMessageLogic messageLogic;
    @Inject private IClientLogic clientLogic;
    @Inject private IProviderLogic providerLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;
    

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public MessageDTO createMessage(MessageDTO dto) {
        dto.setDate(new java.util.Date());
        dto.setIdTypeMessage(1);
        dto.setSubject("Question");
        return messageLogic.createMessage(dto);
    }
    
    
    @POST
    @Path("/newmessage/")
    @StatusCreated
    public MessageDTO createNewMessage(MessageDTO dto) {
        dto.setDate(new java.util.Date());
        dto.setIdTypeMessage(2);
        return messageLogic.createNewMessage(dto);
    }
    
    
    /**
     * @generated
     */
    @GET
    public List<MessageDTO> getMessages() {
        System.out.println("getMessages");
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", messageLogic.countMessages());
        }
        return messageLogic.getMessages(page, maxRecords);
    }
    
    @GET
    @Path("/questionsbyprovider/{idProvider}")
    public List<MessageDTO> getQuestionsByProvider(@PathParam("idProvider") Long idProvider) {
        
        return messageLogic.getQuestionsByProvider(idProvider);
    }
    
      @GET
    @Path("/questionsbyuser/{idUser}")
    public List<MessageDTO> getQuestionsByUser(@PathParam("idUser") Long idUser) {
        
        return messageLogic.getQuestionsByUser(idUser);
    }
   

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public MessageDTO getMessage(@PathParam("id") Long id) {
        return messageLogic.getMessage(id);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public MessageDTO updateMessage(@PathParam("id") Long id, MessageDTO dto) {
        System.out.println("data"+id+dto.getAnswer());
        dto.setId(id);
        return messageLogic.updateMessage(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteMessage(@PathParam("id") Long id) {
        messageLogic.deleteMessage(id);
    }
    
    @GET
    @Path("/getmessagesbyprovider/{idProvider}")
    public List<MessageDTO> getMessagesByProvider(@PathParam("idProvider") Integer idProvider) {
        
        return messageLogic.getmessagesByProvider(idProvider);
    }
    
      @GET
    @Path("/getmessagesbyuser/{idClient}")
    public List<MessageDTO> getMessagesByUser(@PathParam("idClient") Integer idClient) {
        
        return messageLogic.getmessagesByClient(idClient);
    }
    
    
    @GET
    @Path("/getclients/")
    public List<ClientDTO> getClients() {
        
        return clientLogic.getIdANDUsername();
    }
    
    @GET
    @Path("/getproviders/")
    public List<ProviderDTO> getProviders() {
       
        return providerLogic.getProviders();
    }
    
    
     @GET
    @Path("/getproviderbyid/{id}")
    public ProviderDTO getProviderById(@PathParam("id") Long id) {
       
        return providerLogic.getProviderById(id); 
    }
    
    @GET
    @Path("/getclientbyid/{id}")
    public ClientDTO getClientById(@PathParam("id") Long id) {
       
        return clientLogic.getClientById(id);
    }
    
}
