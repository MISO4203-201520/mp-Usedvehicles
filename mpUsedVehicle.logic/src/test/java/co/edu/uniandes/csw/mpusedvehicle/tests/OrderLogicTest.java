/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.tests;

import co.edu.uniandes.csw.mpusedvehicle.api.IOrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.ejbs.OrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.OrderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CartItemEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import co.edu.uniandes.csw.mpusedvehicle.enums.PaymentMethod;
import co.edu.uniandes.csw.mpusedvehicle.persistence.CartItemPersistence;
import co.edu.uniandes.csw.mpusedvehicle.persistence.OrderPersistence;
import static co.edu.uniandes.csw.mpusedvehicle.tests._TestUtil.generateRandom;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class OrderLogicTest {
    public static final String DEPLOY = "Prueba";
    
    /**
     * @generated
     */
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, DEPLOY + ".war")
                .addPackage(OrderEntity.class.getPackage())
                .addPackage(OrderDTO.class.getPackage())
                .addPackage(OrderConverter.class.getPackage())
                .addPackage(OrderLogic.class.getPackage())
                .addPackage(IOrderLogic.class.getPackage())
                .addPackage(OrderPersistence.class.getPackage())
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private IOrderLogic orderLogic;
    
    @PersistenceContext
    private EntityManager em;
    
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
        em.createQuery("delete from OrderEntity").executeUpdate();
    }

    /**
     * @generated
     */
    private List<OrderEntity> data = new ArrayList<OrderEntity>();

    /**
     * @generated
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            OrderEntity entity = new OrderEntity();
        	entity.setAmount(generateRandom(BigDecimal.class));
        	entity.setAmountWithTaxes(generateRandom(BigDecimal.class));
        	entity.setOrderStatus(OrderStatus.NEW.name());
        	entity.setPaymentMethod(PaymentMethod.CREDIT_CARD.name());
        	entity.setTransactionId(generateRandom(String.class));
                
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * @generated
     */
    @Test
    public void createCartItemTest() {
        OrderDTO dto = new OrderDTO();
        dto.setAmount(generateRandom(BigDecimal.class));
        dto.setAmountWithTaxes(generateRandom(BigDecimal.class));
        dto.setOrderStatus(OrderStatus.NEW.name());
        dto.setPaymentMethod(PaymentMethod.CREDIT_CARD.name());
        dto.setTransactionId(generateRandom(String.class));

        OrderDTO result = orderLogic.submitOrder(dto);

        Assert.assertNotNull(result);

        OrderEntity entity = em.find(OrderEntity.class, result.getId());

        Assert.assertEquals(dto.getAmount(), entity.getAmount());
    }
    
    
}