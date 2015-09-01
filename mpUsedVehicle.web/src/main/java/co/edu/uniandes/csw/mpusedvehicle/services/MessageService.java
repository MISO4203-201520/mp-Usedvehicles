package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.IMessageLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.MessageDTO;
import co.edu.uniandes.csw.mpusedvehicle.providers.StatusCreated;
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
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public MessageDTO createMessage(MessageDTO dto) {
        return messageLogic.createMessage(dto);
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
}
