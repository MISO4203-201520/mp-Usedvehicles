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
import co.edu.uniandes.csw.mpusedvehicle.converters.OrderConverter;
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
import com.sun.enterprise.admin.cli.CLIUtil;
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
    public void submitOrderTest() {
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
        PodamFactory factory = new PodamFactoryImpl(); 
            
        ProviderDTO providerDTO = factory.manufacturePojo(ProviderDTO.class);
        //providerDTO.setId(new Long(1));
        providerEntityOne = ProviderConverter.fullDTO2Entity(providerDTO);
        em.persist(providerEntityOne);
        
        providerDTO = factory.manufacturePojo(ProviderDTO.class);
        //providerDTO.setId(new Long(2));
        providerEntityTwo = ProviderConverter.fullDTO2Entity(providerDTO);
        em.persist(providerEntityTwo);
        
        ProductDTO productDTO= new ProductDTO();
        ProductEntity productEntity = ProductConverter.fullDTO2Entity(productDTO);
        productEntity.setProvider(providerEntityOne);
        productEntity.setName(generateRandom(String.class));
        productEntity.setPrice(generateRandom(Integer.class));
//        productEntity.setId(new Long(3));
        ArrayList<ProductEntity> products = new ArrayList<ProductEntity>();
        products.add(productEntity);
        providerEntityOne.setProducts(products);
        em.persist(productEntity);
        
        ClientDTO clientDTOOne = factory.manufacturePojo(ClientDTO.class);
        clientEntityTwo = ClientConverter.fullDTO2Entity(clientDTOOne);
//        clientEntityTwo.setId(new Long(4));
        em.persist(clientEntityTwo);
        
        List<CartItemEntity> list = new ArrayList<CartItemEntity>();        
        for (int i = 0; i < 1; i++) { 
            CartItemDTO cartItemDTO = factory.manufacturePojo(CartItemDTO.class);
            CartItemEntity cartItemEntity = CartItemConverter.fullDTO2Entity(cartItemDTO);
//            cartItemEntity.setProduct(productEntity);
//            cartItemEntity.setId(new Long(1+i));
//            cartItemEntity.setClient(clientEntityTwo);
            em.persist(cartItemEntity);
//            list.add(cartItemEntity);
        }
//        clientEntityTwo.setShoppingCart(list);
//        em.persist(clientEntityTwo);
        
        for (int i = 0; i < 5; i++) { 
            OrderDTO orderDTO = factory.manufacturePojo(OrderDTO.class);
            orderDTO.setCartItems(CartItemConverter.listEntity2DTO(list));
//            orderDTO.setId(new Long(1+i));
            OrderEntity orderEntity = OrderConverter.refDTO2Entity(orderDTO);
            em.persist(orderEntity); 
            data.add(orderEntity); 
        }
    }
    /**
     * Prueba sobre el metodo para obtener las ordenes de un provedor
     */
    @Test
    public void getOrdersByProviderTest() {
        
        ProviderEntity provider = em.find(ProviderEntity.class, providerEntityOne.getId());
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
        
        Assert.assertEquals(5, result.size());
      
        List<OrderDTO> result0 = orderLogic.getOrdersByClient(providerEntityTwo.getId());
        Assert.assertNotNull(result0);
        Assert.assertEquals(0, result0.size());
        
    }
    /**
     * Metodo que prueba la obtencion de todas las ordenes
     */
    @Test
    public void getOrdersTest() {
        List<OrderDTO> list = orderLogic.getOrders();
        Assert.assertEquals(data.size(), list.size());
        for (OrderDTO dto : list) {
            boolean found = false;
            for (OrderEntity entity : data) {
                if (dto.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Metodo que prueba la cantidad de ordenes 
     */
    @Test
    public void countOrdersTest() {
        
        Assert.assertEquals(data.size(), orderLogic.countOrders());
       
    }
    /**
     * Prueba sobre la actualizacion de una orden.
     * En especial el estado de esta.
     */
    @Test
    public void updateOrderTest() {
        OrderEntity entity = data.get(0);
        
        PodamFactory factory = new PodamFactoryImpl();
        OrderDTO dto = factory.manufacturePojo(OrderDTO.class);
 
        orderLogic.updateOrder(entity.getId(), dto);

        OrderEntity resp = em.find(OrderEntity.class, entity.getId());

        Assert.assertEquals(dto.getOrderStatus(), resp.getOrderStatus());
    }
    
    /**
     * Prueba para obtener las ordenes segun un estado..
     */
    @Test
    public void getOrdersByStatusTest() {
        OrderEntity entity = data.get(0);
        
        PodamFactory factory = new PodamFactoryImpl();
        OrderDTO dto = factory.manufacturePojo(OrderDTO.class);
        dto.setOrderStatus(OrderStatus.NEW.name());
 
        orderLogic.updateOrder(entity.getId(), dto);

        OrderEntity resp = em.find(OrderEntity.class, entity.getId());
        
        List<OrderDTO> list = orderLogic.getOrdersByStatus(OrderStatus.NEW);
        Assert.assertNotNull(list);
        for(OrderDTO order:list)
        {
            Assert.assertEquals(order.getOrderStatus(), resp.getOrderStatus());
        }
        
    }
}
