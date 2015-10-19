/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.tests;
import co.edu.uniandes.csw.mpusedvehicle.api.IProductLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProductConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.ProductPersistence;
import static co.edu.uniandes.csw.mpusedvehicle.tests._TestUtil.generateRandom;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import uk.co.jemos.podam.api.PodamFactory; 
import uk.co.jemos.podam.api.PodamFactoryImpl; 

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
/**
 *
 * @author MrWBTA
 */
@RunWith(Arquillian.class)
public class ProductJUnit {
     public static final String DEPLOY = "Prueba";
    @Deployment
    public static WebArchive createDeployment()
    {
       
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(ProductPersistence.class.getPackage())
                .addPackage(ProductEntity.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    public ProductJUnit() {
    }
    
    @Inject 
    UserTransaction utx;
    
  /*  @Inject
    IProductLogic prodL;*/
    
    @Inject
    ProductPersistence productPersistence;
    
        
    @PersistenceContext
    private EntityManager em;
    
    @Before
    public void setUp() {
        try{
            utx.begin(); 
            clearData(); 
            insertData(); 
            utx.commit();
        }catch (Exception e) { 
            e.printStackTrace(); 
            try { 
                utx.rollback(); 
            } catch (Exception e1) { 
                e1.printStackTrace(); 
            } 
        } 
    }
    private void clearData() { 
         em.createQuery("delete from ProductEntity").executeUpdate();
    }
    private List data = new ArrayList(); 
    private void insertData() { 
                for (int i = 0; i < 3; i++) { 
            PodamFactory factory = new PodamFactoryImpl(); 
            ProductEntity entity = ProductConverter.fullDTO2Entity(factory.manufacturePojo(ProductDTO.class)); 
            em.persist(entity); 
            data.add(entity); 
        } 
    }    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void createProductTest() {
        //ProductEntity newEntity = new ProductEntity();
        //newEntity.setName(generateRandom(String.class));
        //newEntity.setPrice(generateRandom(Integer.class));
        PodamFactory factory = new PodamFactoryImpl();
        ProductEntity newEntity = ProductConverter.fullDTO2Entity(factory.manufacturePojo(ProductDTO.class)); 
        System.out.println("getId -- "+newEntity.getId());
        System.out.println("getPrice -- "+newEntity.getPrice());
       // System.out.println("getVehicle -- "+newEntity.getVehicle());
        ProductEntity result = productPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        ProductEntity entity = em.find(ProductEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getName(), entity.getName());
     }
     
}
