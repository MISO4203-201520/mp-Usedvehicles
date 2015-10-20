/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.serviceTest;

import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.mpusedvehicle.services.OrderService;
import javax.ws.rs.client.Client; 
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.ClientBuilder;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Clase de Pruebas sobre servicio REST de order
 * @author vp.salcedo93
 */
@RunWith(Arquillian.class) 
public class OrderTest {
    
    /**
     * Atributos estaticos para prueba REST
     */
    public static String URLRESOURCES = "src/main/webapp";
    public static String URLBASE = "http://localhost:8080/mpUsedVehicle.web/webresources";
    public static String PATHORDERS = "/orders";
    public static String PATHPROVIDER = "/provider";
    public static int Ok = 200;
    public static int Created = 201;
    public static int OkWithoutContent = 204;
    public static List<OrderDTO> oraculo = new ArrayList<>();
    
    /**
     * Metodo que contiene la construccion del empaquetado que arquillian
     * @return 
     */
    @Deployment
    public static Archive<?> createDeployment() {
       MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        WebArchive war = ShrinkWrap
                // Nombre del Proyecto "Bookbasico.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "mpUsedVehicle.web.war")
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.mpusedvehicle:mpUsedVehicle.logic:1.0")
                        .resolveAsFiles())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(OrderService.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos. 
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo shiro.ini. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo beans.xml es necesario para injeccion de dependencias. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
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
    
    @Test
    @RunAsClient
    public void t1GetOrderByProvider() {
//        Client cliente = ClientBuilder.newClient();
//        List<OrderDTO> orderTest = cliente.target(URLBASE + PATHORDERS + PATHPROVIDER).path("/" + 301)
//                .request().get(List.class);
//        Assert.assertEquals(2, orderTest.size());
    }
    
}
