package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author estudiante
 */

public interface IOrderLogic {
    public int countOrders();
    public List<OrderDTO> getOrders();
    public OrderDTO submitOrder(OrderDTO order);
    public OrderDTO updateOrder(Long id, OrderDTO order);
    public List<OrderDTO> getOrdersByStatus(OrderStatus status);
}
