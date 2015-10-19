/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.serviceTest;

import co.edu.uniandes.csw.mpusedvehicle.converters.CartItemConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ClientConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.OrderConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProductConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProviderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.UserDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CartItemEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import co.edu.uniandes.csw.mpusedvehicle.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.mpusedvehicle.services.CartItemService;
import co.edu.uniandes.csw.mpusedvehicle.services.OrderService;
import co.edu.uniandes.csw.mpusedvehicle.services.UserService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 * Clase de Pruebas sobre servicio REST de order
 * @author vp.salcedo93
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class) 
public class OrderTest {
    
    /**
     * Atributos estaticos para prueba REST
     */
    public static final String URLRESOURCES = "src/main/webapp";
    public static final String URLBASE = "http://localhost:8181/mpUsedVehicle.web/webresources";
    public static final String PATHORDERS = "/orders";
    private static final String PATH_LOGIN = "/users/login";
    public static final String PATHPROVIDER = "/provider";
    public static final int Ok = 200;
    public static final int Created = 201;
    public static final int OkWithoutContent = 204;
    public static List<OrderDTO> oraculo = new ArrayList<>();
    public static ProviderDTO providerOne;
    
    /**
     * Metodo que contiene la construccion del empaquetado que arquillian
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
            OrderDTO order = factory.manufacturePojo(OrderDTO.class); 
            oraculo.add(order); 
        } 
        
    }
    
    @AfterClass
    public static void clearData() {
        oraculo.clear();
    }
    /**
     * Prueba null sobre la orden de los proveedores
     */
    @Test
    @RunAsClient
    public void t1GetOrderByProvider() {
        Client cliente = ClientBuilder.newClient();
        List<OrderDTO> orderTest = cliente.target(URLBASE + PATHORDERS + PATHPROVIDER).path("/" + 301)
                .request().get(List.class);
        if(orderTest != null){
            Assert.assertNotNull(orderTest);
            Assert.assertEquals(0, orderTest.size());
        }else
            Assert.assertNull(orderTest);
    }
    /**
     * Prueba orden de los proveedores
     */
    @Test
    @RunAsClient
    public void t2GetOrderByProvider() {
        Client cliente = ClientBuilder.newClient();
        UserDTO user = new UserDTO();
        user.setRole("user");
        user.setUserName("USER1");
        user.setPassword("Pepitoperez123");
        Response login = cliente.target(URLBASE + PATH_LOGIN).request().post(Entity.entity(user, MediaType.APPLICATION_JSON));
        cliente.close();
        cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE + PATHORDERS + PATHPROVIDER +"/301")
               .request().get();
        Assert.assertNotNull(response);
       String listProducts = response.readEntity(String.class);
       Assert.assertNotNull(listProducts);
//        System.out.println("response: "+listProducts);
//        try {
//            List<OrderDTO> orderTest = new ObjectMapper().readValue(listProducts, List.class);
//            Assert.assertEquals(Ok, response.getStatus());
//        } catch (IOException ex) {
//            Logger.getLogger(OrderTest.class.getName()).log(Level.SEVERE, null, ex);
//            Assert.fail();
//        }
       
        
    }
}
