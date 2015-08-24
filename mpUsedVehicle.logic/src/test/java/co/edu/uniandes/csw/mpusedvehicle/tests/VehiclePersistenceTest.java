package co.edu.uniandes.csw.mpusedvehicle.tests;

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
public class VehiclePersistenceTest {
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(VehicleEntity.class.getPackage())
                .addPackage(VehiclePersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private VehiclePersistence vehiclePersistence;

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
        VehicleEntity newEntity = new VehicleEntity();
        newEntity.setName(generateRandom(String.class));
        newEntity.setDescription(generateRandom(String.class));
        newEntity.setColor(generateRandom(String.class));
        newEntity.setModel(generateRandom(String.class));
        newEntity.setCapacity(generateRandom(Integer.class));
        newEntity.setBrand(generateRandom(String.class));
        newEntity.setImage(generateRandom(String.class));

        VehicleEntity result = vehiclePersistence.create(newEntity);

        Assert.assertNotNull(result);

        VehicleEntity entity = em.find(VehicleEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
        Assert.assertEquals(newEntity.getModel(), entity.getModel());
        Assert.assertEquals(newEntity.getCapacity(), entity.getCapacity());
        Assert.assertEquals(newEntity.getBrand(), entity.getBrand());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
    }

    /**
     * @generated
     */
    @Test
    public void getVehiclesTest() {
        List<VehicleEntity> list = vehiclePersistence.findAll(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (VehicleEntity ent : list) {
            boolean found = false;
            for (VehicleEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
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
        VehicleEntity newEntity = vehiclePersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
        Assert.assertEquals(entity.getColor(), newEntity.getColor());
        Assert.assertEquals(entity.getModel(), newEntity.getModel());
        Assert.assertEquals(entity.getCapacity(), newEntity.getCapacity());
        Assert.assertEquals(entity.getBrand(), newEntity.getBrand());
        Assert.assertEquals(entity.getImage(), newEntity.getImage());
    }

    /**
     * @generated
     */
    @Test
    public void deleteVehicleTest() {
        VehicleEntity entity = data.get(0);
        vehiclePersistence.delete(entity.getId());
        VehicleEntity deleted = em.find(VehicleEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateVehicleTest() {
        VehicleEntity entity = data.get(0);

        VehicleEntity newEntity = new VehicleEntity();

        newEntity.setId(entity.getId());
        newEntity.setName(generateRandom(String.class));
        newEntity.setDescription(generateRandom(String.class));
        newEntity.setColor(generateRandom(String.class));
        newEntity.setModel(generateRandom(String.class));
        newEntity.setCapacity(generateRandom(Integer.class));
        newEntity.setBrand(generateRandom(String.class));
        newEntity.setImage(generateRandom(String.class));

        vehiclePersistence.update(newEntity);

        VehicleEntity resp = em.find(VehicleEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(newEntity.getColor(), resp.getColor());
        Assert.assertEquals(newEntity.getModel(), resp.getModel());
        Assert.assertEquals(newEntity.getCapacity(), resp.getCapacity());
        Assert.assertEquals(newEntity.getBrand(), resp.getBrand());
        Assert.assertEquals(newEntity.getImage(), resp.getImage());
    }

    /**
     * @generated
     */
    @Test
    public void getVehiclePaginationTest() {
        //Page 1
        List<VehicleEntity> ent1 = vehiclePersistence.findAll(1, 2);
        Assert.assertNotNull(ent1);
        Assert.assertEquals(2, ent1.size());
        //Page 2
        List<VehicleEntity> ent2 = vehiclePersistence.findAll(2, 2);
        Assert.assertNotNull(ent2);
        Assert.assertEquals(1, ent2.size());

        for (VehicleEntity ent : ent1) {
            boolean found = false;
            for (VehicleEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (VehicleEntity ent : ent2) {
            boolean found = false;
            for (VehicleEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
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
        List<VehicleEntity> list = vehiclePersistence.findByName(name);

        for (VehicleEntity entity : data) {
            if (entity.getName().equals(name)) {
                cache.add(entity);
            }
        }
        Assert.assertEquals(list.size(), cache.size());
        for (VehicleEntity ent : list) {
            boolean found = false;
            for (VehicleEntity cacheEntity : cache) {
                if (cacheEntity.getName().equals(ent.getName())) {
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
