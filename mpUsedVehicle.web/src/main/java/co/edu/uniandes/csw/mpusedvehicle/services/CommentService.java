package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.ICommentLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IProductLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CommentDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
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
import org.apache.shiro.SecurityUtils;

/**
 * @generated
 */
@Path("/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentService {

    @Inject
    private ICommentLogic commentLogic;
    @Inject
    private IProductLogic productLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @QueryParam("q")
    private String vehicleName;
    private ProductDTO product = (ProductDTO) SecurityUtils.getSubject().getSession().getAttribute("Product");

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public CommentDTO createComment(CommentDTO dto) {
        return commentLogic.createComment(dto);
    }

    /**
     * @generated
     */
    @GET
    public List<CommentDTO> getComments() {
        if (product != null) {
            return commentLogic.getByProduct(product.getId());
        } else {

                if (page != null && maxRecords != null) {
                    this.response.setIntHeader("X-Total-Count", commentLogic.countComments());
                }
                return commentLogic.getComments(page, maxRecords);
            }
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public CommentDTO getComment(@PathParam("id") Long id) {
        return commentLogic.getComment(id);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public CommentDTO updateComment(@PathParam("id") Long id, CommentDTO dto) {
        dto.setId(id);
        return commentLogic.updateComment(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteComment(@PathParam("id") Long id) {
        commentLogic.deleteComment(id);
    }
}
