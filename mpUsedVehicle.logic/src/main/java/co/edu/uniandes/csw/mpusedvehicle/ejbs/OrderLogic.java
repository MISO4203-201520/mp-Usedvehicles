/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IOrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.OrderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CartItemEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ClientEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import co.edu.uniandes.csw.mpusedvehicle.persistence.OrderPersistence;
import co.edu.uniandes.csw.mpusedvehicle.persistence.ProductPersistence;
import co.edu.uniandes.csw.mpusedvehicle.util.MailManager;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class OrderLogic implements IOrderLogic {
    
    @Inject private OrderPersistence persistence;
    @Inject private ProductPersistence productPersistence;
    
    /**
     * Metodo que cuenta la cantidad de ordenes
     * @return  entero.
     */
    @Override
    public int countOrders() {
        return persistence.count();
    }
    
    /**
     * Metodo que retorna todas las ordenes actuales.
     * @return Lista de OrderDTO
     */
    @Override
    public List<OrderDTO> getOrders() {
        return OrderConverter.listEntity2DTO(persistence.findAll(null,null));
    }
    /**
     * Metodo que crea y almacena la orden
     * @param order. Nueva orden
     * @return Orden guardada.
     */
    @Override
    public OrderDTO submitOrder(OrderDTO order) {
        OrderEntity entity = OrderConverter.refDTO2Entity(order);        
        persistence.create(entity);
        return OrderConverter.refEntity2DTO(entity);
    }
    /**
     * Metodo que actualiza una orden.
     * @param id. Identificador de la orden que desea ser modificado.
     * @param order. Orden con los nuevos datos.
     * @return La orden almacenada.
     */
    @Override
    public OrderDTO updateOrder(Long id, OrderDTO order) {
        OrderEntity oldOrderDTO = persistence.find(id);
        OrderEntity entity = OrderConverter.refDTO2Entity(order);        
        if(!oldOrderDTO.getOrderStatus().equals(order.getOrderStatus()) && order.getOrderStatus().equalsIgnoreCase(OrderStatus.COMPLETED.getName())){
            List<CartItemEntity> list = entity.getCartItems();
            String emailBody = "";
            ClientEntity client =null;
            for(CartItemEntity cartItem : list) {
                productPersistence.updatePurchasedByClient(cartItem.getProduct().getId(), cartItem.getClient().getId());
                client = cartItem.getClient();
            }
                        
            // Send email         
            if(client != null)
            {
                emailBody ="<h2>Hello "+client.getName() +",</h2><br>"+
                        "will you please take a minute to share your experience? "+
                        "Help us evaluate your purchased items. Rate your products in http://localhost:8080/mpUsedVehicle.web/#/catalog";
                
                MailManager.generateAndSendEmail(emailBody, client.getName(), "Rate your pruducts purchased");
            }
         
            }
            persistence.updateOrderInfo(id, entity);
            return OrderConverter.refEntity2DTO(entity);
    }
    /**
     * Metodo que obtiene las odenes dado un estado
     * @param status. Estado a ser consultado.
     * @return Lista de las ordenes 
     */
    @Override
    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {
        return persistence.getOrdersByStatus(null, null, status);
    }
    /**
     * Metodo que retorna las ordenes activas que tiene un proveedor
     * @param idProvider. Identificador del provvedor.
     * @return Lista de las ordenes.
     */
    @Override
    public List<OrderDTO> getOrdersByProvider(Long idProvider){
        List<OrderDTO> list = OrderConverter.listEntity2DTO(persistence.getOrdersByProvider(idProvider));
        return list;
    }
    /**
     * Metodo que retorna las ordenes activas que tiene un cliente
     * @param idClient. Identificador del cliente.
     * @return Lista de las ordenes.
     */
    @Override
    public List<OrderDTO> getOrdersByClient(Long idClient){
        return OrderConverter.listEntity2DTO(persistence.getOrdersByClient(idClient));
    }

}