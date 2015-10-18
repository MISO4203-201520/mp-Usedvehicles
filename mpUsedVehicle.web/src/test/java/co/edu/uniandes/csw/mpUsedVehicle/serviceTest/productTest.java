package co.edu.uniandes.csw.mpUsedVehicle.serviceTest; 

import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO; 
import co.edu.uniandes.csw.mpusedvehicle.providers.EJBExceptionMapper; 
import co.edu.uniandes.csw.mpusedvehicle.services.ProductService; 
import java.io.File; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
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
import org.junit.Assert; 
import org.junit.BeforeClass; 
import org.junit.Test; 
import org.junit.runner.RunWith; 
import uk.co.jemos.podam.api.PodamFactory; 
import uk.co.jemos.podam.api.PodamFactoryImpl; 
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.common.PodamExclude;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class) 
public class productTest { 

    public static final String DEPLOY = "Prueba";  
    public static String URLRESOURCES = "src/main/webapp"; 
    public static String URLBASE = "http://localhost:8181/mpUsedVehicle.web/webresources"; 
    public static String PATHPRODUCT = "/products";
    public static int Ok = 200;
    public static int Created = 201;
    public static int OkWithoutContent = 204;
    public static List<ProductDTO> oraculo = new ArrayList<>();
      
    @Deployment  
    public static Archive<?> createDeployment() { 

        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        WebArchive war = ShrinkWrap 
                // Nombre del Proyecto "ProductBasico.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "ProductBasico.web.war") 
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV) 
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.mpUsedVehicle:mpUsedVehicle.logic:1.0") 
                        .resolveAsFiles()) 
                // Se agregan los compilados de los paquetes de servicios 
                .addPackage(ProductService.class.getPackage()) 
                .addPackage(EJBExceptionMapper.class.getPackage()) 
                // El archivo que contiene la configuracion a la base de datos.  
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml") 
                // El archivo beans.xml es necesario para injeccion de dependencias.  
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml")) 
                // El archivo shiro.ini   
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini")) 
                // El archivo web.xml es necesario para el despliegue de los servlets 
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml")); 

        return war; 
    } 

    @BeforeClass 
    public static void setUp() { 
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
        Assert.assertEquals(product.getId(), productTest.getId()); 
        Assert.assertEquals(Created, response.getStatus()); 
    
    }
    
    @Test
    @RunAsClient
    public void t2GetProductById() {
        Client cliente = ClientBuilder.newClient();
        ProductDTO productTest = cliente.target(URLBASE + PATHPRODUCT).path("/" + oraculo.get(0).getId())
                .request().get(ProductDTO.class);
        Assert.assertEquals(productTest.getName(), oraculo.get(0).getName());
    }
    
    
}