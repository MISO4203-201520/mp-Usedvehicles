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
 * Interface sobre la lógica de las ordenes.
 * @author estudiante
 */

public interface IOrderLogic {
    public int countOrders();
    public List<OrderDTO> getOrders();
    public OrderDTO submitOrder(OrderDTO order);
    public OrderDTO updateOrder(Long id, OrderDTO order);
    public List<OrderDTO> getOrdersByStatus(OrderStatus status);
    
    /**
     * Metodo que retorna las ordenes activas que tiene un proveedor
     * @param idProvider. Identificador del provvedor.
     * @return Lista de las ordenes.
     */
    public List<OrderDTO> getOrdersByProvider(Long idProvider);
    /**
     * Metodo que retorna las ordenes activas que tiene un cliente
     * @param idClient. Identificador del cliente.
     * @return Lista de las ordenes.
     */
    public List<OrderDTO> getOrdersByClient(Long idClient);
}
