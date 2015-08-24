package co.edu.uniandes.csw.mpusedvehicle.tests;

import co.edu.uniandes.csw.mpusedvehicle.ejbs.VehicleLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.IVehicleLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.VehicleConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.VehicleDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.VehicleEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.VehiclePersistence;
import static co.edu.uniandes.csw.mpusedvehicle.tests._TestUtil.*;
import java.util.ArrayList;
import java.util.List;

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
 * @generated
 */
@RunWith(Arquillian.class)
public class VehicleLogicTest {
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(VehicleEntity.class.getPackage())
                .addPackage(VehicleDTO.class.getPackage())
                .addPackage(VehicleConverter.class.getPackage())
                .addPackage(VehicleLogic.class.getPackage())
                .addPackage(IVehicleLogic.class.getPackage())
                .addPackage(VehiclePersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private IVehicleLogic vehicleLogic;

    /**
     * @generated
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @generated
     */
    @Inject
    UserTransaction utx;

    /**
     * @generated
     */
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

    /**
     * @generated
     */
    private void clearData() {
        em.createQuery("delete from VehicleEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<VehicleEntity> data = new ArrayList<VehicleEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            VehicleEntity entity = new VehicleEntity();
        	entity.setName(generateRandom(String.class));
        	entity.setDescription(generateRandom(String.class));
        	entity.setColor(generateRandom(String.class));
        	entity.setModel(generateRandom(String.class));
        	entity.setCapacity(generateRandom(Integer.class));
        	entity.setBrand(generateRandom(String.class));
        	entity.setImage(generateRandom(String.class));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createVehicleTest() {
        VehicleDTO dto = new VehicleDTO();
        dto.setName(generateRandom(String.class));
        dto.setDescription(generateRandom(String.class));
        dto.setColor(generateRandom(String.class));
        dto.setModel(generateRandom(String.class));
        dto.setCapacity(generateRandom(Integer.class));
        dto.setBrand(generateRandom(String.class));
        dto.setImage(generateRandom(String.class));

        VehicleDTO result = vehicleLogic.createVehicle(dto);

        Assert.assertNotNull(result);

        VehicleEntity entity = em.find(VehicleEntity.class, result.getId());

        Assert.assertEquals(dto.getName(), entity.getName());
        Assert.assertEquals(dto.getDescription(), entity.getDescription());
        Assert.assertEquals(dto.getColor(), entity.getColor());
        Assert.assertEquals(dto.getModel(), entity.getModel());
        Assert.assertEquals(dto.getCapacity(), entity.getCapacity());
        Assert.assertEquals(dto.getBrand(), entity.getBrand());
        Assert.assertEquals(dto.getImage(), entity.getImage());
    }

    /**
     * @generated
     */
    @Test
    public void getVehiclesTest() {
        List<VehicleDTO> list = vehicleLogic.getVehicles(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (VehicleDTO dto : list) {
            boolean found = false;
            for (VehicleEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void getVehicleTest() {
        VehicleEntity entity = data.get(0);
        VehicleDTO dto = vehicleLogic.getVehicle(entity.getId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getName(), dto.getName());
        Assert.assertEquals(entity.getDescription(), dto.getDescription());
        Assert.assertEquals(entity.getColor(), dto.getColor());
        Assert.assertEquals(entity.getModel(), dto.getModel());
        Assert.assertEquals(entity.getCapacity(), dto.getCapacity());
        Assert.assertEquals(entity.getBrand(), dto.getBrand());
        Assert.assertEquals(entity.getImage(), dto.getImage());
    }

    /**
     * @generated
     */
    @Test
    public void deleteVehicleTest() {
        VehicleEntity entity = data.get(0);
        vehicleLogic.deleteVehicle(entity.getId());
        VehicleEntity deleted = em.find(VehicleEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateVehicleTest() {
        VehicleEntity entity = data.get(0);

        VehicleDTO dto = new VehicleDTO();

        dto.setId(entity.getId());
        dto.setName(generateRandom(String.class));
        dto.setDescription(generateRandom(String.class));
        dto.setColor(generateRandom(String.class));
        dto.setModel(generateRandom(String.class));
        dto.setCapacity(generateRandom(Integer.class));
        dto.setBrand(generateRandom(String.class));
        dto.setImage(generateRandom(String.class));

        vehicleLogic.updateVehicle(dto);

        VehicleEntity resp = em.find(VehicleEntity.class, entity.getId());

        Assert.assertEquals(dto.getName(), resp.getName());
        Assert.assertEquals(dto.getDescription(), resp.getDescription());
        Assert.assertEquals(dto.getColor(), resp.getColor());
        Assert.assertEquals(dto.getModel(), resp.getModel());
        Assert.assertEquals(dto.getCapacity(), resp.getCapacity());
        Assert.assertEquals(dto.getBrand(), resp.getBrand());
        Assert.assertEquals(dto.getImage(), resp.getImage());
    }

    /**
     * @generated
     */
    @Test
    public void getVehiclePaginationTest() {
        //Page 1
        List<VehicleDTO> dto1 = vehicleLogic.getVehicles(1, 2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(2, dto1.size());
        //Page 2
        List<VehicleDTO> dto2 = vehicleLogic.getVehicles(2, 2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(1, dto2.size());

        for (VehicleDTO dto : dto1) {
            boolean found = false;
            for (VehicleEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (VehicleDTO dto : dto2) {
            boolean found = false;
            for (VehicleEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * @generated
     */
    @Test
    public void findByName() {
        String name = data.get(0).getName();
        List<VehicleEntity> cache = new ArrayList<VehicleEntity>();
        List<VehicleDTO> list = vehicleLogic.findByName(name);

        for (VehicleEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(cache.size(), list.size());
        for (VehicleDTO dto : list) {
            boolean found = false;
            for (VehicleEntity cacheEntity : cache) {
                if (cacheEntity.getName().equals(dto.getName())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                Assert.fail();
            }
        }
    }
}
