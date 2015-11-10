package co.edu.uniandes.csw.mpusedvehicle.services;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpusedvehicle.api.IProductLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IProviderLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IMultimediaLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.MultimediaDTO;
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
    @Inject
    private IMultimediaLogic multimediaLogic;
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
    
    
    @GET
    @Path("/getbyprovidername/{name}")
    public List<ProductDTO> getProductsbyprovidername(@PathParam("name") String nameProvider) {
         List<ProductDTO> products = new ArrayList<ProductDTO>();
        try {
            products = productLogic.getProductByProvider(nameProvider);
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
        return products;
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
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
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
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
        return product;
    }
    @GET
    @Path("listVehiclesName")
    public List<ProductDTO> getVehiclesNames()
    {
        return productLogic.getVehiclesName();
    }
    @GET
    @Path("listVehiclesBrand")
    public List<ProductDTO> getVehiclesBrand()
    {
        return productLogic.getVehiclesBrand();
    }
    @GET
    @Path("listVehiclesCapacity")
    public List<ProductDTO> getVehiclesCapacity()
    {
        return productLogic.getVehiclesCapacity();
    }
    @GET
    @Path("listVehiclesColor")
    public List<ProductDTO> getVehiclesColor()
    {
        return productLogic.getVehiclesColor();
    }
    @GET
    @Path("listVehiclesModel")
    public List<ProductDTO> getVehiclesModel()
    {
        return productLogic.getVehiclesModel();
    }
    
    @GET
    @Path("listVehiclesPlate")
    public List<ProductDTO> getVehiclesPlate()
    {
        return productLogic.getVehiclesPlate();
    }
    
    @GET
    @Path("listVehiclesLocation")
    public List<ProductDTO> getVehiclesLocation()
    {
        return productLogic.getVehiclesLocation();
    }    
    
    
    @GET
    @Path("/getimagesbyvehiclename/{name}")
    public List<MultimediaDTO> getImagesbyvehiclename(@PathParam("name") String name) {
        return multimediaLogic.getImagesByVehicle(name);
        
    }
            
    @GET
    @Path("advancedsearch")
    @MPLoCAnn(tier="Services", reqId="R15")
    public List<ProductDTO> getProductsByAdvancedSearch(
            @QueryParam("brandFilter") String brandFilter, 
            @QueryParam("modelFilter") String modelFilter, 
            @QueryParam("capacityFilter") String capacityFilter, 
            @QueryParam("priceFilter") String priceFilter,
            @QueryParam("plateFilter") String plateFilter,
            @QueryParam("locationFilter") String locationFilter,
            @QueryParam("colorFilter") String colorFilter) {
        
        List<ProductDTO> products = new ArrayList<ProductDTO>();
                
        Integer iCapacity = null;
        try {
            iCapacity = Integer.valueOf(capacityFilter);
        } catch (NumberFormatException nfe) {
            Logger.getGlobal().log(Level.WARNING, 
                    "Capacity cannot be parsed to an Integer value. It will be used null as a value", nfe);
        }
        
        Integer iPrice = null;
        try {
            iPrice = Integer.valueOf(priceFilter);
        } catch (NumberFormatException nfe) {
            Logger.getGlobal().log(Level.WARNING, 
                    "Price cannot be parsed to an Integer value. It will be used null as a value", nfe);
        }
        
        try {
            
            if ((brandFilter == null || "".equalsIgnoreCase(brandFilter)) && 
                    (modelFilter == null || "".equalsIgnoreCase(modelFilter)) && 
                    (capacityFilter == null || "".equalsIgnoreCase(capacityFilter)) && 
                    (priceFilter == null || "".equalsIgnoreCase(priceFilter)) && 
                    (colorFilter == null || "".equalsIgnoreCase(colorFilter)) && 
                    (plateFilter == null || "".equalsIgnoreCase(plateFilter)) && 
                    (locationFilter == null || "".equalsIgnoreCase(locationFilter))
                    
                    ) {
                products = getProducts();
            } else {
                products = productLogic.getProductsByAdvancedSearch(brandFilter, modelFilter, iCapacity, iPrice, colorFilter, plateFilter, locationFilter);
            }
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
        
        
        return products;
    }
    
    

}
