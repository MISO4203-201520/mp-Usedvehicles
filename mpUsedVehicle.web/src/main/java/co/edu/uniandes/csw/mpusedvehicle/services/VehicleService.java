package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.IVehicleLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.VehicleDTO;
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
@Path("/vehicles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VehicleService {

    @Inject private IVehicleLogic vehicleLogic;
    @Context private HttpServletResponse response;
    @QueryParam("page") private Integer page;
    @QueryParam("maxRecords") private Integer maxRecords;

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public VehicleDTO createVehicle(VehicleDTO dto) {
        return vehicleLogic.createVehicle(dto);
    }

    /**
     * @generated
     */
    @GET
    public List<VehicleDTO> getVehicles() {
        if (page != null && maxRecords != null) {
            this.response.setIntHeader("X-Total-Count", vehicleLogic.countVehicles());
        }
        return vehicleLogic.getVehicles(page, maxRecords);
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public VehicleDTO getVehicle(@PathParam("id") Long id) {
        return vehicleLogic.getVehicle(id);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public VehicleDTO updateVehicle(@PathParam("id") Long id, VehicleDTO dto) {
        dto.setId(id);
        return vehicleLogic.updateVehicle(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteVehicle(@PathParam("id") Long id) {
        vehicleLogic.deleteVehicle(id);
    }
}
