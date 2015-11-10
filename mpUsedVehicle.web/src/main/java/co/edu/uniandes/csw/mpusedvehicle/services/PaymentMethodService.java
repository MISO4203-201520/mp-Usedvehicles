package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.PaymentMethodDTO;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.shiro.SecurityUtils;

/**
 * @generated
 */
@Path("/paymentMethods")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentMethodService {

    @Inject
    private IPaymentMethodLogic paymentMethodLogic;

    /**
     * Sercicio REST encargado de obtener la lista de ordenes de un cliente.
     *
     * @param id. Devuelve el id del cliente.
     * @return List. Lista de ordenes.
     */
    @GET
    public List<PaymentMethodDTO> getPaymentMethods() {
        return paymentMethodLogic.finAll();
    }
}
