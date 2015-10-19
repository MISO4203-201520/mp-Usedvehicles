package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.IProviderLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IVehicleLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.providers.StatusCreated;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Path("/providers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProviderService {

    @Inject private IVehicleLogic vehicleLogic;
    @Inject private IProviderLogic providerLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public ProviderDTO createProvider(ProviderDTO dto) {
        return providerLogic.createProvider(dto);
    }

    /**
     * @generated
     */
    @GET
    public List<ProviderDTO> getProviders() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", providerLogic.countProviders());
        }
        return providerLogic.getProviders(page, maxRecords);
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ProviderDTO getProvider(@PathParam("id") Long id) {
        return providerLogic.getProvider(id);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ProviderDTO updateProvider(@PathParam("id") Long id, ProviderDTO dto) {
        dto.setId(id);
        return providerLogic.updateProvider(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProvider(@PathParam("id") Long id) {
        providerLogic.deleteProvider(id);
    }
    
    @GET
    @Path("/providerbymodel/{model}")
    public ProviderDTO getProviderByModel(@PathParam("model") String model) {
        ProviderDTO provider = new ProviderDTO();
        try {
            provider = providerLogic.getProviderByModel(model);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
        return provider;
    }
    
    @GET
    @Path("/providerbybrand/{brand}")
    public ProviderDTO getProviderByBrand(@PathParam("brand") String brand) {
        ProviderDTO provider = new ProviderDTO();
        try {
            provider = providerLogic.getProviderByBrand(brand);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
        return provider;
    }
    
    @GET
    @Path("/providerbycity/{city}")
    public ProviderDTO getProviderByCity(@PathParam("city") String city) {
        ProviderDTO provider = new ProviderDTO();
        try {
            provider = providerLogic.getProviderByCity(city);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
        return provider;
    }
    
    @GET
    @Path("/providerbypricerange/{lowerupper}")
    public ProviderDTO getProviderByPriceRange(@PathParam("lowerupper") String lowerupper
    ){
        ProviderDTO provider = new ProviderDTO();
        try {
            String[] parts = lowerupper.split("-");
            String lower = parts[0]; 
            String upper = parts[1]; 
            provider = providerLogic.getProviderByPriceRange(Integer.valueOf(lower), Integer.valueOf(upper));
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
        return provider;
    }
 
    
}
