/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.tests;

import co.edu.uniandes.csw.mpusedvehicle.api.IOrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.CartItemConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ClientConverter;
import co.edu.uniandes.csw.mpusedvehicle.ejbs.OrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.AgreementConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProductConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProviderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CartItemEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ClientEntity;
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
                .addPackage(AgreementConverter.class.getPackage())
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
                .addPackage(ClientEntity.class.getPackage())
                .addPackage(ClientDTO.class.getPackage())
                .addPackage(ClientConverter.class.getPackage())
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
        createScenario1();
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
     * Cliente prueba uno
     */
    private ClientEntity clientEntityTwo;
    /**
     * Inserta Ordenes sobre clientes y provedores conocidos
     */
    private void createScenario1() {
        //PodamFactory factory = new PodamFactoryImpl(); 
            
        providerEntityOne = new ProviderEntity();
        providerEntityOne.setName("Povider1");
        providerEntityOne.setUserId(generateRandom(String.class));
        providerEntityOne.setId(new Long(1));
        em.persist(providerEntityOne);
        
        providerEntityTwo = new ProviderEntity();
        providerEntityTwo.setName("Povider2");
        providerEntityTwo.setUserId(generateRandom(String.class));
        providerEntityTwo.setId(new Long(2));
        em.persist(providerEntityTwo);
        
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProvider(providerEntityOne);
        productEntity.setName(generateRandom(String.class));
        productEntity.setPrice(generateRandom(Integer.class));
        productEntity.setId(new Long(3));
        ArrayList<ProductEntity> products = new ArrayList<ProductEntity>();
        products.add(productEntity);
        providerEntityOne.setProducts(products);
        em.persist(productEntity);
        
        clientEntityTwo = new ClientEntity();
        clientEntityTwo.setName("Client1");
        clientEntityTwo.setUserId(generateRandom(String.class));
        clientEntityTwo.setId(new Long(4));
        em.persist(clientEntityTwo);
        
        List<CartItemEntity> list = new ArrayList<CartItemEntity>();        
        for (int i = 0; i < 1; i++) { 
            CartItemEntity cartItemEntity = new CartItemEntity();
            cartItemEntity.setName(generateRandom(String.class));
            cartItemEntity.setProduct(productEntity);
            cartItemEntity.setId(new Long(1+i));
            cartItemEntity.setClient(clientEntityTwo);
            em.persist(cartItemEntity);
            list.add(cartItemEntity);
        }
        clientEntityTwo.setShoppingCart(list);
        em.persist(clientEntityTwo);
        
        for (int i = 0; i < 5; i++) { 
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setAmount(generateRandom(BigDecimal.class));
            orderEntity.setAmountWithTaxes(generateRandom(BigDecimal.class));
            orderEntity.setOrderStatus(OrderStatus.NEW.name());
            orderEntity.setPaymentMethod(PaymentMethod.CREDIT_CARD.name());
            orderEntity.setTransactionId(generateRandom(String.class));
            orderEntity.setCartItems(list);
            orderEntity.setId(new Long(1+i));
            em.persist(orderEntity); 
            data.add(orderEntity); 
        }
    }
    /**
     * Prueba sobre el metodo para obtener las ordenes de un provedor
     */
    @Test
    public void getOrdersByProviderTest() {
        
        ProductEntity provider = em.find(ProductEntity.class, providerEntityOne.getId());
        System.err.println("provider " + provider + " id "+providerEntityOne.getId());
        List<OrderDTO> result = orderLogic.getOrdersByProvider(providerEntityOne.getId());
        Assert.assertNotNull(result);
        
        Assert.assertEquals(0, result.size());
      
        List<OrderDTO> result0 = orderLogic.getOrdersByProvider(providerEntityTwo.getId());
        Assert.assertNotNull(result0);
        Assert.assertEquals(0, result.size());
        
    }
    
    /**
     * Prueba sobre el metodo para obtener las ordenes de un cliente
     */
    @Test
    public void getOrdersByClientTest() {
        
        List<OrderDTO> result = orderLogic.getOrdersByClient(clientEntityTwo.getId());
        Assert.assertNotNull(result);
        System.out.println("data size "+data.size());
        
        Assert.assertEquals(0, result.size());
      
//        List<OrderDTO> result0 = orderLogic.getOrdersByClient(providerEntityTwo.getId());
//        Assert.assertNotNull(result0);
//        Assert.assertEquals(0, result.size());
        
    }
    
    
}
