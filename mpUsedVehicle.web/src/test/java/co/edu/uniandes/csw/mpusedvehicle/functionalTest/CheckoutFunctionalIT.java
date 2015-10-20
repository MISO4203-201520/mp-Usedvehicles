/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.functionalTest;

import co.edu.uniandes.csw.mpusedvehicle.configuration.ApiKeyEnvVariables;
import co.edu.uniandes.csw.mpusedvehicle.dtos.UserDTO;
import co.edu.uniandes.csw.mpusedvehicle.services.OrderService;
import java.io.File;
import java.net.URL;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author dham
 */
@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CheckoutFunctionalIT {
    
    private static String URLRESOURCES = "src/main/webapp";
    private static String URLBASE = "http://localhost:8181/mpUsedVehicle.web/webresources";
    private static String PATHBOOK = "/books";
    private static WebDriver driver;
    private static int Ok = 200;
    private static int Created = 201;
    private static int OkWithoutContent = 204;
    private static String URLIMAGE = "http://www.seleniumhq.org/images/big-logo.png";
    
    // Mediante la anotacion @ArquillianResource se obtiene la URL de despliegue de la aplicacion
    @ArquillianResource
    URL deploymentURL;
    
    @Deployment 
    public static Archive<?> createDeployment() { 
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");

        WebArchive war = ShrinkWrap 
                // Nombre del Proyecto "BookBasico.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "mpUsedVehicle.web.war") 
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV) 
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.mpusedvehicle:mpUsedVehicle.logic:1.0") 
                        .resolveAsFiles()) 
                // Se agregan los compilados de los paquetes que se van a probar 
                .addPackage(OrderService.class.getPackage()) 
                .addPackage(ApiKeyEnvVariables.class.getPackage())
                // Se agrega contenido estatico: html y modulos de javascript.  
                .addAsWebResource(new File(URLRESOURCES, "index.html")) 
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory(URLRESOURCES + "/src/").as(GenericArchive.class), "/src/", Filters.includeAll())
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
       // Crea una instancia del driver de firefox sobre el que se ejecutara la aplicacion. 
       driver = new FirefoxDriver();
    }
    
    @Before
    public void setUpTest() {        
//        insertData();
        // El browser  va a la url de despliegue. Se ejecuta al inicar cada uno de los metodos de prueba indicados con @Test
        driver.get(deploymentURL.toString());
        
    }
    
    @AfterClass
    public static void tearDown() throws Exception {
        //Se ejecuta al terminar todos los metodos de prueba indicados con @Test Cierra el browser
        driver.quit();
    }
    
    public static Cookie login(String username, String password) {
        Client cliente = ClientBuilder.newClient();
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        Response response = cliente.target(URLBASE).path("/users/login").request().
                post(Entity.entity(user, MediaType.APPLICATION_JSON));       
        UserDTO foundUser = (UserDTO) response.readEntity(UserDTO.class);
        
        if (foundUser != null && response.getStatus() == Ok) {
            return response.getCookies().get("JSESSIONID");
        } else {
            return null;
        }
    }

    @Test
    @RunAsClient
    public void t1createBook() throws InterruptedException {
        boolean success = false;
        Thread.sleep(2500);
        driver.findElement(By.id("0-addToCart-btn")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Cien anos de Soledad");
        driver.findElement(By.id("description")).clear();
        driver.findElement(By.id("description")).sendKeys("Realismo magico");
        driver.findElement(By.id("isbn")).clear();
        driver.findElement(By.id("isbn")).sendKeys("1025789845-13");
        driver.findElement(By.id("imageurl")).clear();
        driver.findElement(By.id("imageurl")).sendKeys("http://image.casadellibro.com/a/l/t0/08/9788497592208.jpg");
        driver.findElement(By.id("save-book")).click();
        Thread.sleep(2000);
//        List<WebElement> books = driver.findElements(By.xpath("//div[contains(@ng-repeat,'record in records')]"));
//        for (WebElement book : books) {
//            List<WebElement> captions = book.findElements(By.xpath("div[contains(@class, 'col-md-4')]/div[contains(@class, 'caption')]/p"));
//            if (captions.get(0).getText().contains("Cien anos de Soledad") && captions.get(1).getText().contains("Realismo magico")
//                    && captions.get(2).getText().contains("1025789845-13")) {
//                success = true;
//            }
//        }
//        assertTrue(success);
        Thread.sleep(1000);
    }
    
}
