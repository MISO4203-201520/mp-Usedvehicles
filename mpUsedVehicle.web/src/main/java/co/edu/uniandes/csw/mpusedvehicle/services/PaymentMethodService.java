package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.ICartItemLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IClientLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IOrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;

/**
 * @generated
 */
@Path("/paymentMethods")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentMethodService {

    @Inject private IPaymentMethodLogic paymentMethodLogic;
    
    /**
     * Cliente que ha iniciado sesion
     */
    private ClientDTO client = (ClientDTO)SecurityUtils.getSubject().getSession().getAttribute("Client");

    /**
     * Sercicio REST encargado de obtener la lista de ordenes de un cliente.
     * @param id. Devuelve el id del cliente.
     * @return List. Lista de ordenes.
     */
    @GET
    public List<PaymentMethodDTO> getPaymentMethods() {
//        if(client!= null)
//        {
            return paymentMethodLogic.finAll();
//        }
//        return null;
    }
}
