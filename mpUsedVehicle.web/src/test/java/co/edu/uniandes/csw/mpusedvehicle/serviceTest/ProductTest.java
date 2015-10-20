/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.serviceTest;
import co.edu.uniandes.csw.mpusedvehicle.configuration.ApiKeyEnvVariables;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.UserDTO;
import co.edu.uniandes.csw.mpusedvehicle.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.mpusedvehicle.services.CartItemService;
import co.edu.uniandes.csw.mpusedvehicle.services.OrderService;
import co.edu.uniandes.csw.mpusedvehicle.services.ProductService;
import co.edu.uniandes.csw.mpusedvehicle.services.UserService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import com.sun.jersey.server.impl.ejb.EJBExceptionMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Cookie;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.resteasy.plugins.providers.RegisterBuiltin;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author MrWBTA
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class ProductTest {
   
    public static String URLRESOURCES = "src/main/webapp";
    public static String URLBASE = "http://localhost:8181/mpUsedVehicle.web/webresources";
    public static String PATHPRODUCT = "/products";
    public static int Ok = 200;
    public static int Created = 201;
    public static int OkWithoutContent = 204;
    public static List<ProductDTO> oraculo = new ArrayList<>(); 
    
    public static String PATH_BRANDS = "/listVehiclesBrand";
    public static String PATH_CAPACITIES = "/listVehiclesCapacity";
    public static String PATH_COLORS = "/listVehiclesColor";
    public static String PATH_MODELS = "/listVehiclesModel";
    public static String PATH_PLATES = "/listVehiclesPlate";
    public static String PATH_LOCATIONS = "/listVehiclesLocation";    
    public static String PATH_ADVANCE_SEARCH = "/advancedsearch"; 
        
            
     @Deployment
    public static Archive<?> createDeployment() {

        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        WebArchive war = ShrinkWrap
                // Nombre del Proyecto "mpUserVehicle.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "mpUsedVehicle.web.war")
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.mpusedvehicle:mpUsedVehicle.logic:1.0")
                        .resolveAsFiles())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ProductService.class.getPackage())
                .addPackage(UserService.class.getPackage())
                .addPackage(CartItemService.class.getPackage())
                .addPackage(OrderService.class.getPackage())                
                .addPackage(EJBExceptionMapper.class.getPackage())
                .addPackage(ApiKeyEnvVariables.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos. 
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                 // El archivo shiro.ini. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/glassfish-resources.xml"))
                 // El archivo shiro.ini. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));

        return war;
    }
  @BeforeClass
    public static void setUp() {
        ResteasyProviderFactory providerFactory = ResteasyProviderFactory.getInstance();
        RegisterBuiltin.register(providerFactory);
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            ProductDTO product = factory.manufacturePojo(ProductDTO.class);
            oraculo.add(product);            
        }       
    }

      
    @Test
    @RunAsClient
    public void t1CreateProductService() throws IOException {
            
            ProductDTO product = oraculo.get(0);
            Client cliente = ClientBuilder.newClient();
            Response response = cliente.target(URLBASE + PATHPRODUCT)
                    .request()
                    .post(Entity.entity(product, MediaType.APPLICATION_JSON));
            ProductDTO productTest = (ProductDTO) response.readEntity(ProductDTO.class);        
            Assert.assertEquals(product.getName(), productTest.getName());
            Assert.assertEquals(product.getPrice(), productTest.getPrice());
            Assert.assertEquals(Created, response.getStatus());
    
    }    
    
    @Test
    @RunAsClient
    public void t21GetVehiclesBrand()  throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHPRODUCT + PATH_BRANDS)
                .request().get();
        String listProducts = response.readEntity(String.class);
        List<ProductDTO> listProductTest = new ObjectMapper().readValue(listProducts, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertNotNull(listProductTest);        
    }
    @Test
    @RunAsClient
    public void t22GetVehiclesCapacity()  throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHPRODUCT + PATH_CAPACITIES)
                .request().get();
        String listProducts = response.readEntity(String.class);
        List<ProductDTO> listProductTest = new ObjectMapper().readValue(listProducts, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertNotNull(listProductTest);        
    }
    @Test
    @RunAsClient
    public void t23GetVehiclesColor()  throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHPRODUCT + PATH_COLORS)
                .request().get();
        String listProducts = response.readEntity(String.class);
        List<ProductDTO> listProductTest = new ObjectMapper().readValue(listProducts, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertNotNull(listProductTest);        
    }
    @Test
    @RunAsClient
    public void t24GetVehiclesModel()  throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHPRODUCT + PATH_MODELS)
                .request().get();
        String listProducts = response.readEntity(String.class);
        List<ProductDTO> listProductTest = new ObjectMapper().readValue(listProducts, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertNotNull(listProductTest);        
    }
    @Test
    @RunAsClient
    public void t25GetVehiclesPlate()  throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHPRODUCT + PATH_PLATES)
                .request().get();
        String listProducts = response.readEntity(String.class);
        List<ProductDTO> listProductTest = new ObjectMapper().readValue(listProducts, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertNotNull(listProductTest);        
    }
    @Test
    @RunAsClient
    public void t26GetVehiclesLocation()  throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHPRODUCT + PATH_LOCATIONS)
                .request().get();
        String listProducts = response.readEntity(String.class);
        List<ProductDTO> listProductTest = new ObjectMapper().readValue(listProducts, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertNotNull(listProductTest);        
    }          
    @Test
    @RunAsClient
    public void t27GetProductByAdvanceSearch()  throws IOException {
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHPRODUCT + PATH_ADVANCE_SEARCH+"?"+"brandFilter=Jaguar")//.path("?"+"brandFilter=Jaguar")//+oraculo.get(0).getVehicle().getBrand())
                .request().get();
        String listProducts = response.readEntity(String.class);
        List<ProductDTO> listProductTest = new ObjectMapper().readValue(listProducts, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertNotNull(listProductTest);        
    }      
}
