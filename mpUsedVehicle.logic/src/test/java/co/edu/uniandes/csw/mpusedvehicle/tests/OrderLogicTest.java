/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.tests;

import co.edu.uniandes.csw.mpusedvehicle.api.IOrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.CartItemConverter;
import co.edu.uniandes.csw.mpusedvehicle.ejbs.OrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.OrderConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProductConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProviderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CartItemEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProviderEntity;
import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import co.edu.uniandes.csw.mpusedvehicle.enums.PaymentMethod;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

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
                .addPackage(ProviderEntity.class.getPackage())
                .addPackage(ProviderDTO.class.getPackage())
                .addPackage(ProviderConverter.class.getPackage())
                .addPackage(CartItemEntity.class.getPackage())
                .addPackage(CartItemDTO.class.getPackage())
                .addPackage(CartItemConverter.class.getPackage())
                .addPackage(ProductEntity.class.getPackage())
                .addPackage(ProductDTO.class.getPackage())
                .addPackage(ProductConverter.class.getPackage())
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
    
    /**
     * Provedor prueba uno
     */
    private ProviderEntity providerEntityOne;
    /**
     * Provedor prueba uno
     */
    private ProviderEntity providerEntityTwo;
    /**
     * Inserta Ordenes sobre clientes y provedores conocidos
     */
    private void createScenario1() {
        //PodamFactory factory = new PodamFactoryImpl(); 
            
        providerEntityOne = new ProviderEntity();
        providerEntityOne.setName("Povider1");
        em.persist(providerEntityOne);
        
        providerEntityTwo = new ProviderEntity();
        providerEntityTwo.setName("Povider2");
        em.persist(providerEntityTwo);
        
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProvider(providerEntityOne);
        productEntity.setName(generateRandom(String.class));
        productEntity.setPrice(generateRandom(Integer.class));
        em.persist(productEntity);
        
        List<CartItemEntity> list = new ArrayList<CartItemEntity>();        
        for (int i = 0; i < 3; i++) { 
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setName(generateRandom(String.class));
            cartItemEntity.setProduct(productEntity);
            em.persist(cartItemEntity);
            list.add(cartItemEntity);
        }
        
        for (int i = 0; i < 2; i++) { 
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAmount(generateRandom(BigDecimal.class));
            orderEntity.setAmountWithTaxes(generateRandom(BigDecimal.class));
            orderEntity.setOrderStatus(OrderStatus.NEW.name());
            orderEntity.setPaymentMethod(PaymentMethod.CREDIT_CARD.name());
            orderEntity.setTransactionId(generateRandom(String.class));
            orderEntity.setCartItems(list);
            em.persist(orderEntity); 
            data.add(orderEntity); 
        }
    }
    /**
     * Prueba sobre el metodo para obtener las ordenes de un provedor
     */
    @Test
    public void getOrdersByProviderTest() {
        //createScenario1();
        //insertData();
//
//        List<OrderDTO> result = orderLogic.getOrdersByProvider(providerEntityOne.getId());
//        Assert.assertNotNull(result);
//        Assert.assertEquals(2, result.size());
        
//        List<OrderDTO> result0 = orderLogic.getOrdersByProvider(providerEntityTwo.getId());
//        Assert.assertNull(result0);
        
    }
    
    
}