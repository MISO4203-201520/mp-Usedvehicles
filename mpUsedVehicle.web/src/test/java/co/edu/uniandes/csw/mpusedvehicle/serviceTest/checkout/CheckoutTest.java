package co.edu.uniandes.csw.mpusedvehicle.serviceTest.checkout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.csw.mpusedvehicle.api.IAdminLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IClientLogic;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.UserDTO;
import co.edu.uniandes.csw.mpusedvehicle.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.mpusedvehicle.services.CartItemService;
import co.edu.uniandes.csw.mpusedvehicle.services.OrderService;
import co.edu.uniandes.csw.mpusedvehicle.services.UserService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author dham
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class CheckoutTest {
    
    private static final String URLRESOURCES = "src/main/webapp";
    private static final String URLBASE = "http://localhost:8181/mpUsedVehicle.web/webresources";
    private static final String PATH_LOGIN = "/users/login";
    private static final String PATH_ORDERS = "/orders";
    private static final String PATH_CART_ITEMS = "/cartItems";
    private static final int Ok = 200;
    private static final int Created = 201;
    private static final int OkWithoutContent = 204;
    private static List<CartItemDTO> cartItems = new ArrayList<>();
    private static OrderDTO order;
    
//    http://localhost:8080/mpUsedVehicle.web/webresources/users/login
//    {"role":"user","userName":"USER2","password":"Pepitoperez123"}
    /**
     *
     * @return
     */
    @Deployment
    public static Archive<?> createDeployment() {

         MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
         WebArchive war = ShrinkWrap
                // Nombre del Proyecto "mpUsedVehicle.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "mpUsedVehicle.web.war")
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.mpusedvehicle:mpUsedVehicle.logic:1.0")
                        .resolveAsFiles())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(UserService.class.getPackage())
                .addPackage(CartItemService.class.getPackage())
                .addPackage(OrderService.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
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
        for (int i = 0; i < 5; i++) { 
            PodamFactory factory = new PodamFactoryImpl();
            cartItems.add(factory.manufacturePojo(CartItemDTO.class)); 
            cartItems.add(factory.manufacturePojo(CartItemDTO.class)); 
            order = factory.manufacturePojo(OrderDTO.class);            
        } 
    }
    
    @Test  
    @RunAsClient
    public void t1AddItemsToCartService() throws IOException {
        CartItemDTO cartItem = cartItems.get(0);
        Client cliente = ClientBuilder.newClient();
        UserDTO user = new UserDTO();
        user.setRole("user");
        user.setUserName("USER2");
        user.setPassword("Pepitoperez123");
        Response login = cliente.target(URLBASE + PATH_LOGIN).request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
        cliente.close();
        cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATH_CART_ITEMS)
                .request()
                .post(Entity.entity(cartItem, MediaType.APPLICATION_JSON));
        CartItemDTO cartItemTest = (CartItemDTO) response.getEntity();
//        Assert.assertEquals(book.getName(), bookTest.getName());
//        Assert.assertEquals(book.getIsbn(), bookTest.getIsbn());
//        Assert.assertEquals(book.getId(), bookTest.getId());
//        Assert.assertEquals(Created, response.getStatus());
    }
}
