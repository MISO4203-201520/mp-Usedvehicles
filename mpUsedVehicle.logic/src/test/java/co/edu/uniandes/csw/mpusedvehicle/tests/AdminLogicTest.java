/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.tests;

import co.edu.uniandes.csw.mpusedvehicle.api.IAdminLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.AdminConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.AdminDTO;
import co.edu.uniandes.csw.mpusedvehicle.ejbs.AdminLogic;
import co.edu.uniandes.csw.mpusedvehicle.entities.AdminEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.AdminPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author vp.salcedo93
 */
@RunWith(Arquillian.class)
public class AdminLogicTest {
    public static final String DEPLOY = "Prueba"; 
    
    @Deployment 
    public static WebArchive createDeployment() { 
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war") 
                .addPackage(AdminEntity.class.getPackage()) 
                .addPackage(AdminDTO.class.getPackage()) 
                .addPackage(AdminConverter.class.getPackage()) 
                .addPackage(AdminLogic.class.getPackage()) 
                .addPackage(IAdminLogic.class.getPackage()) 
                .addPackage(AdminPersistence.class.getPackage()) 
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml") 
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml"); 
    }
    @Inject 
    UserTransaction utx; 
    /**
     * @generated
     */
    @Inject
    private IAdminLogic adminLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    @Before 
    public void configTest() { 
        try { 
            utx.begin(); 
            clearData(); 
            insertData(); 
            utx.commit(); 
        } catch (Exception e) { 
            e.printStackTrace(); 
            try { 
                utx.rollback(); 
            } catch (Exception e1) { 
                e1.printStackTrace(); 
            } 
        } 
    }
    private void clearData() { 
        em.createQuery("delete from AdminEntity").executeUpdate(); 
    } 
    private List data = new ArrayList(); 
    
    private void insertData() { 
        for (int i = 0; i < 3; i++) { 
            PodamFactory factory = new PodamFactoryImpl(); 
            AdminEntity entity = AdminConverter.basicDTO2Entity(factory.manufacturePojo(AdminDTO.class)); 
            em.persist(entity); 
            data.add(entity); 
        } 
    }
    
    @Test
    public void createAdminTest() {
        PodamFactory factory = new PodamFactoryImpl(); 
        AdminDTO dto = factory.manufacturePojo(AdminDTO.class);
        AdminDTO result = adminLogic.createAdmin(dto);
        Assert.assertNotNull(result);

        AdminEntity entity = em.find(AdminEntity.class, result.getId());

        Assert.assertEquals(dto.getName(), entity.getName());
    }
    
    
}
