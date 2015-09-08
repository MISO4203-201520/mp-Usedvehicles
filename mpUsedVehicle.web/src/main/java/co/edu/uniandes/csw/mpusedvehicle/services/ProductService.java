package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mpusedvehicle.api.IProductLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IProviderLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
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
@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {

    @Inject
    private IProductLogic productLogic;
    @Inject
    private IProviderLogic providerLogic;
    @Context
    private HttpServletResponse response;
    @QueryParam("page")
    private Integer page;
    @QueryParam("maxRecords")
    private Integer maxRecords;
    @QueryParam("q")
    private String vehicleName;
    private ProviderDTO provider = (ProviderDTO) SecurityUtils.getSubject().getSession().getAttribute("Provider");

    /**
     * @generated
     */
    @POST
    @StatusCreated
    public ProductDTO createProduct(ProductDTO dto) {
        return productLogic.createProduct(dto);
    }

    /**
     * @generated
     */
    @GET
    public List<ProductDTO> getProducts() {
        if (provider != null) {
            return providerLogic.getProvider(provider.getId()).getProducts();
        } else {
            if (vehicleName != null) {
                return productLogic.getByVehicleName(vehicleName);
            } else {
                if (page != null && maxRecords != null) {
                    this.response.setIntHeader("X-Total-Count", productLogic.countProducts());
                }
                return productLogic.getProducts(page, maxRecords);
            }
        }
    }

    /**
     * @generated
     */
    @GET
    @Path("{id: \\d+}")
    public ProductDTO getProduct(@PathParam("id") Long id) {
        return productLogic.getProduct(id);
    }

    /**
     * @generated
     */
    @PUT
    @Path("{id: \\d+}")
    public ProductDTO updateProduct(@PathParam("id") Long id, ProductDTO dto) {
        dto.setId(id);
        return productLogic.updateProduct(dto);
    }

    /**
     * @generated
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteProduct(@PathParam("id") Long id) {
        productLogic.deleteProduct(id);
    }

    @GET
    @Path("/cheapest/{name}")
    public ProductDTO getCheaperProductByProvider(@PathParam("name") String nameProvider) {
        ProductDTO product = new ProductDTO();
        try {
            product = productLogic.getCheaperProductByProvider(nameProvider);
        } catch (Exception e) {
            System.out.println("Provider not Found");
        }
        return product;
    }

    @GET
    @Path("/cheapestbyvehicle/{name}")
    public ProductDTO getCheaperProviderbyVehicle(@PathParam("name") String nameVehicle) {
        ProductDTO product = new ProductDTO();
        try {
            product = productLogic.getCheaperProductByVehicle(nameVehicle);
        } catch (Exception e) {
            System.out.println("Vehicle not Found");
        }
        return product;
    }
}
