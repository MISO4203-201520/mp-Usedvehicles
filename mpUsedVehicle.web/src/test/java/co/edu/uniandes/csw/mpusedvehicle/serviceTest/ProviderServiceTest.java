package co.edu.uniandes.csw.mpusedvehicle.serviceTest;

import co.edu.uniandes.csw.mpusedvehicle.dtos.*;
import co.edu.uniandes.csw.mpusedvehicle.providers.EJBExceptionMapper;
import co.edu.uniandes.csw.mpusedvehicle.services.ProviderService;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.filter.LoggingFilter;
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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;  

/**
 * @generated
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class ProviderServiceTest {

    public final static String URLRESOURCES = "src/main/webapp";
    public final static String URLBASE = "http://localhost:8181/mpUsedVehicle.web/webresources";
    public final static String PATH = "providers";
    public final static int Ok = 200;
    public final static int Created = 201;
    public final static int OkWithoutContent = 204;
    public final static List<ProviderDTO> data = new ArrayList<>();

    /**
     * @generated
     */
    @Deployment
    public static Archive<?> createDeployment() {
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        WebArchive war = ShrinkWrap
                // Nombre del Proyecto "AppMarketPlace.web" seguido de ".war". Debe ser el mismo nombre del proyecto web que contiene los javascript y los  servicios Rest
                .create(WebArchive.class, "mpUsedVehicle.web.war")
                // Se agrega la dependencia a la logica con el nombre groupid:artefactid:version (GAV)
                .addAsLibraries(resolver.artifact("co.edu.uniandes.csw.mpusedvehicle:mpUsedVehicle.logic:1.0")
                        .resolveAsFiles())
                // Se agregan los compilados de los paquetes de servicios
                .addPackage(ProviderService.class.getPackage())
                .addPackage(EJBExceptionMapper.class.getPackage())
                // El archivo que contiene la configuracion a la base de datos. 
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                // El archivo shiro.ini 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/shiro.ini"))
                // El archivo beans.xml es necesario para injeccion de dependencias. 
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                // El archivo web.xml es necesario para el despliegue de los servlets
                .setWebXML(new File("src/main/webapp/WEB-INF/web.xml"));
        return war;
    }

    /**
     * @generated
     */
    @BeforeClass
    public static void setUp() throws IOException {
        for (int i = 0; i < 5; i++) {
            PodamFactory factory = new PodamFactoryImpl();
            ProviderDTO dto = factory.manufacturePojo(ProviderDTO.class);
            data.add(dto);
        }

    }

    //Metodo para autenticarse de ser necesario, recuerde que esto depende de los permisos que se encuentran en el archivo shiro.ini
    private static Cookie login(String username, String password) {
        Client cliente = ClientBuilder.newClient();

        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);

        Response response = cliente.target(URLBASE)
                .path("users")
                .path("login")
                .request()
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));

        UserDTO foundUser = (UserDTO) response.readEntity(UserDTO.class);

        if (foundUser != null && response.getStatus() == Ok) {
            return response.getCookies().get("JSESSIONID");
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t1CreateProvider() {

        //TODO Realizar login en caso de ser necesario

        ProviderDTO dto = data.get(0);
        Client cliente = ClientBuilder.newClient();
        PodamFactory factory = new PodamFactoryImpl();
        Response response;

        response = cliente.target(URLBASE)
                .path(PATH)
                .request()
                .post(Entity.entity(dto, MediaType.APPLICATION_JSON));
        ProviderDTO result = (ProviderDTO) response.readEntity(ProviderDTO.class);
        Assert.assertEquals(dto.getId(), result.getId());
        Assert.assertEquals(Created, response.getStatus());
    }    

    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t2GetProviderById() {

        //TODO Realizar login en caso de ser necesario

        Client cliente = ClientBuilder.newClient();
        ProviderDTO providerTest = cliente.target(URLBASE)
                .path(PATH)
                .path(data.get(0).getId().toString())
                .request().get(ProviderDTO.class);
        Assert.assertEquals(providerTest.getId(), data.get(0).getId());
    }

    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t3GetProviders() throws IOException {

        //TODO Realizar login en caso de ser necesario

        Client cliente = ClientBuilder.newClient();
        Response response = cliente.target(URLBASE)
                .path(PATH)
                .request().get();
        String listProvider = response.readEntity(String.class);
        List<ProviderDTO> listProviderTest = new ObjectMapper().readValue(listProvider, List.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(1, listProviderTest.size());
    }

    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t4UpdateProvider() throws IOException {

        //TODO Realizar login en caso de ser necesario

        Client cliente = ClientBuilder.newClient();
        ProviderDTO provider = cliente.target(URLBASE)
                .path(PATH)
                .path(data.get(0).getId().toString())
                .request().get(ProviderDTO.class);
        PodamFactory factory = new PodamFactoryImpl();
        ProviderDTO providerChanged = factory.manufacturePojo(ProviderDTO.class);
        provider.setId(providerChanged.getId());
        provider.setName(providerChanged.getName());
        provider.setUserId(providerChanged.getUserId());

        Response response = cliente.target(URLBASE)
                .path(PATH)
                .path("/" + provider.getId())
                .request().put(Entity.entity(provider, MediaType.APPLICATION_JSON));
        ProviderDTO providerTest = (ProviderDTO) response.readEntity(ProviderDTO.class);
        Assert.assertEquals(Ok, response.getStatus());
        Assert.assertEquals(provider.getId(), providerTest.getId());
        Assert.assertEquals(provider.getName(), providerTest.getName());
        Assert.assertEquals(provider.getUserId(), providerTest.getUserId());
    }

    /**
     * @generated
     */
    @Test
    @RunAsClient
    public void t5DeleteProvider() {

        //TODO Realizar login en caso de ser necesario

        Client cliente = ClientBuilder.newClient();
        ProviderDTO provider = data.get(0);
        Response response = cliente.target(URLBASE)
                .path(PATH)
                .path(provider.getId().toString())
                .request().delete();
        Assert.assertEquals(OkWithoutContent, response.getStatus());
    }
}
