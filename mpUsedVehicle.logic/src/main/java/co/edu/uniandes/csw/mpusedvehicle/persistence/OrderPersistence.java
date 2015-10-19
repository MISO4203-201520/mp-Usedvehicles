/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.converters.CartItemConverter;
import co.edu.uniandes.csw.mpusedvehicle.converters.OrderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CartItemEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.enums.OrderStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 *
 * @author estudiante
 */
@Stateless
public class OrderPersistence extends CrudPersistence<OrderEntity>{
    
    public OrderPersistence() {
        this.entityClass = OrderEntity.class;
    }
    
    public List<OrderDTO> getOrdersByStatus(Integer page, Integer maxRecords, OrderStatus status) {
        Query q = em.createQuery("select u from " + entityClass.getSimpleName() + " u where u.orderStatus = :status");
        if (page != null && maxRecords != null) {
            q.setFirstResult((page - 1) * maxRecords);
            q.setMaxResults(maxRecords);
        }
        return OrderConverter.listEntity2DTO(q.setParameter("status", status.name()).getResultList());
    }
    
    public int updateOrderStatus(Long id, OrderEntity entity) {
        Query q = em.createNativeQuery("update orderentity set orderStatus = '" + entity.getOrderStatus()+ "' where id = " + id);        
        return q.executeUpdate();
    }
    
    public int updateOrderInfo(Long id, OrderEntity entity) {
        Query q = em.createNativeQuery("update orderentity set orderStatus = '" + entity.getOrderStatus()+ "', amount = " + entity.getAmount() +", amountwithtaxes = " + entity.getAmountWithTaxes() + ", paymentmethod = '" + entity.getPaymentMethod() + "', taxamount = " + entity.getTaxAmount() + "  where id = " + id);        
        return q.executeUpdate();
    }
    /**
     * Metodo que retorna las ordenes activas que tiene un proveedor
     * @param idProvider. Identificador del provvedor.
     * @return Lista de las ordenes.
     */
    public List<OrderEntity> getOrdersByProvider(Long idProvider) {
        List<OrderEntity> result = new ArrayList<OrderEntity>();
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("provider_id", idProvider);
            List<OrderEntity> list = new ArrayList<OrderEntity>();
            list = executeListNamedQuery("OrderEntity.getOrdersByProvider", params);
            result = list;
        }catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
        System.out.println("Se obtuvo: "+result.size());
//        System.out.println("Order: "+result.get(1).getId());
        return result;
    }
    /**
     * Metodo que retorna las ordenes activas que tiene un cliente
     * @param idClient. Identificador del cliente.
     * @return Lista de las ordenes.
     */
    public List<OrderEntity> getOrdersByClient(Long idClient) {
        List<OrderEntity> result = new ArrayList<OrderEntity>();
        try{
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("client_id", idClient);
            List<OrderEntity> list = new ArrayList<OrderEntity>();
            list = executeListNamedQuery("OrderEntity.getOrdersByClient", params);
            result = list;
        }catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
        return result;
    }

}
