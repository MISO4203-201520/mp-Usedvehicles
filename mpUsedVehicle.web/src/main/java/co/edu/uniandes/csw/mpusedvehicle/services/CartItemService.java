package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.ICartItemLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IClientLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IOrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.providers.StatusCreated;
import java.util.Collections;
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
import org.apache.shiro.SecurityUtils;

/**
 * @generated
 */
@Path("/cartItems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CartItemService {

    @Inject private ICartItemLogic cartItemLogic;
    @Context private HttpServletResponse response;
    @Inject private IClientLogic clientLogic;
    @Inject private IOrderLogic orderLogic;
    private ClientDTO client = (ClientDTO)SecurityUtils.getSubject().getSession().getAttribute("Client");
    /**
     * @generated
     */
    @POST
    @StatusCreated
    public CartItemDTO createCartItem(CartItemDTO dto) {
        CartItemDTO item = cartItemLogic.createCartItemByClient(dto, client.getId());
        return item;
    }

    /**
     * @generated
     */
    @GET
    public List<CartItemDTO> getCartItems() { 
       return cartItemLogic.getCartItems(null,null);
       //return cartItemLogic.getCartItemsByClient(null,null,client.getId());
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CartItemDTO getCartItem(@PathParam("id") Long id) {
        return cartItemLogic.getCartItemsByClientById(id, client.getId());
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CartItemDTO updateCartItem(@PathParam("id") Long id, CartItemDTO dto) {
        dto.setId(id);
        return cartItemLogic.updateCartItemByClient(client.getId(), dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCartItem(@PathParam("id") Long id) {
        cartItemLogic.deleteCartItemByClient(client.getId(), id);
    }
    
    /**
     * Sercicio REST encargado de obtener la lista de ordenes de un cliente.
     * @param id. Devuelve el id del cliente.
     * @return List. Lista de ordenes.
     */
    @GET
    @Path("client")
    public List<CartItemDTO> getHistoryByClient(@QueryParam("idClient") Long idClient) {
        if(idClient!= null)
        {
            return cartItemLogic.getHistoryByClient(null,null,idClient);
        }
        return Collections.emptyList();
    }    
}
