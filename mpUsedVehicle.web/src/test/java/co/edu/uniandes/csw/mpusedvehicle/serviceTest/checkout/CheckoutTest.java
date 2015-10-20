package co.edu.uniandes.csw.mpusedvehicle.serviceTest.checkout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import co.edu.uniandes.csw.mpusedvehicle.api.IAdminLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IClientLogic;
import co.edu.uniandes.csw.mpusedvehicle.configuration.ApiKeyEnvVariables;
import co.edu.uniandes.csw.mpusedvehicle.converters.ClientConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.UserDTO;
import co.edu.uniandes.csw.mpusedvehicle.ejbs.ClientLogic;
import co.edu.uniandes.csw.mpusedvehicle.entities.ClientEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.ClientPersistence;
import co.edu.uniandes.csw.mpusedvehicle.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.mpusedvehicle.samples.Samples;
import co.edu.uniandes.csw.mpusedvehicle.services.CartItemService;
import co.edu.uniandes.csw.mpusedvehicle.services.OrderService;
import co.edu.uniandes.csw.mpusedvehicle.services.UserService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
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
import org.junit.Assert;
import org.junit.Before;
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

    private static List<CartItemDTO> cartItems = new ArrayList<>();
    private static OrderDTO order;
    private static ClientEntity client;
    private static Cookie cookieSessionId;

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
                .addPackage(ApiKeyEnvVariables.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos. 
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo beans.xml es necesario para injeccion de dependencias. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo shiro.ini. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));

        return war;
    }
    
    @BeforeClass
    public static void setUp() {

        insertData();

    }

    private static void insertData() {
        Samples.createSampleClient();
        cookieSessionId = Samples.login("test", "Pepitoperez123");
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            cartItems.add(factory.manufacturePojo(CartItemDTO.class));
            cartItems.add(factory.manufacturePojo(CartItemDTO.class));
            order = factory.manufacturePojo(OrderDTO.class);
        }
    }

    /**
     * El carrito debe empezar vacío
     * @throws IOException 
     */
    @Test
    @RunAsClient
    public void t1AddItemsToCartService() throws IOException {
        cookieSessionId = Samples.login("test", "Pepitoperez123");
        CartItemDTO cartItem = cartItems.get(0);
        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(Samples.URLBASE + Samples.PATH_CART_ITEMS)
                .request()
                .cookie(cookieSessionId)
                .post(Entity.entity(cartItem, MediaType.APPLICATION_JSON));
        CartItemDTO cartItemTest = (CartItemDTO) response.getEntity();
        Assert.assertNull(cartItemTest);
    }

}
