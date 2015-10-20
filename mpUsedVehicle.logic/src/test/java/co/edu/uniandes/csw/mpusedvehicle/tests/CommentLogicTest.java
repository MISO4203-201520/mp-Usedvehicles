package co.edu.uniandes.csw.mpusedvehicle.tests;

import co.edu.uniandes.csw.mpusedvehicle.ejbs.CommentLogic;
import co.edu.uniandes.csw.mpusedvehicle.api.ICommentLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.CommentConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CommentDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CommentEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.CommentPersistence;
import static co.edu.uniandes.csw.mpusedvehicle.tests._TestUtil.*;
import java.util.ArrayList;
import java.util.Date;
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
public class CommentLogicTest {
    public static final String DEPLOY = "Prueba";

    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(CommentEntity.class.getPackage())
                .addPackage(CommentDTO.class.getPackage())
                .addPackage(CommentConverter.class.getPackage())
                .addPackage(CommentLogic.class.getPackage())
                .addPackage(ICommentLogic.class.getPackage())
                .addPackage(CommentPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * @generated
     */
    @Inject
    private ICommentLogic vehicleLogic;

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
        em.createQuery("delete from CommentEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<CommentEntity> data = new ArrayList<CommentEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CommentEntity entity = new CommentEntity();
        	entity.setDate(generateRandom(Date.class));
        	entity.setDescription(generateRandom(String.class));
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * @generated
     */
    @Test
    public void createCommentTest() {
        CommentDTO dto = new CommentDTO();
        dto.setDate(generateRandom(Date.class));
        dto.setDescription(generateRandom(String.class));

        CommentDTO result = vehicleLogic.createComment(dto);

        Assert.assertNotNull(result);

        CommentEntity entity = em.find(CommentEntity.class, result.getId());

        Assert.assertEquals(dto.getDate(), entity.getDate());
        Assert.assertEquals(dto.getDescription(), entity.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getCommentsTest() {
        List<CommentDTO> list = vehicleLogic.getComments(null, null);
        Assert.assertEquals(data.size(), list.size());
        for (CommentDTO dto : list) {
            boolean found = false;
            for (CommentEntity entity : data) {
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
    public void getCommentTest() {
        CommentEntity entity = data.get(0);
        CommentDTO dto = vehicleLogic.getComment(entity.getId());
        Assert.assertNotNull(dto);
        Assert.assertEquals(entity.getDate(), dto.getDate());
        Assert.assertEquals(entity.getDescription(), dto.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void deleteCommentTest() {
        CommentEntity entity = data.get(0);
        vehicleLogic.deleteComment(entity.getId());
        CommentEntity deleted = em.find(CommentEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * @generated
     */
    @Test
    public void updateCommentTest() {
        CommentEntity entity = data.get(0);

        CommentDTO dto = new CommentDTO();

        dto.setId(entity.getId());
        dto.setDate(generateRandom(Date.class));
        dto.setDescription(generateRandom(String.class));

        vehicleLogic.updateComment(dto);

        CommentEntity resp = em.find(CommentEntity.class, entity.getId());

        Assert.assertEquals(dto.getDate(), resp.getDate());
        Assert.assertEquals(dto.getDescription(), resp.getDescription());
    }

    /**
     * @generated
     */
    @Test
    public void getCommentPaginationTest() {
        //Page 1
        List<CommentDTO> dto1 = vehicleLogic.getComments(1, 2);
        Assert.assertNotNull(dto1);
        Assert.assertEquals(2, dto1.size());
        //Page 2
        List<CommentDTO> dto2 = vehicleLogic.getComments(2, 2);
        Assert.assertNotNull(dto2);
        Assert.assertEquals(1, dto2.size());

        for (CommentDTO dto : dto1) {
            boolean found = false;
            for (CommentEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }

        for (CommentDTO dto : dto2) {
            boolean found = false;
            for (CommentEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

}
