/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IOrderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.OrderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import co.edu.uniandes.csw.mpusedvehicle.persistence.OrderPersistence;
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

    public int countOrders() {
        return persistence.count();
    }

    public List<OrderDTO> getOrders() {
        return OrderConverter.listEntity2DTO(persistence.findAll(null,null));
    }

    public OrderDTO submitOrder(OrderDTO order) {
        OrderEntity entity = OrderConverter.refDTO2Entity(order);        
        persistence.create(entity);
        return OrderConverter.refEntity2DTO(entity);
    }

    public OrderDTO updateOrder(Long id, OrderDTO order) {
        OrderEntity entity = OrderConverter.refDTO2Entity(order);        
        persistence.updateOrderInfo(id, entity);
        return OrderConverter.refEntity2DTO(entity);
    }

    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {
        return persistence.getOrdersByStatus(null, null, status);
    }

}