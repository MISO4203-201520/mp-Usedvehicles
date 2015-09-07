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
import java.util.List;
import javax.ejb.Stateless;
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
        Query q = em.createNativeQuery("update orderentity set orderStatus = '" + entity.getOrderStatus()+ "', amount = '" + entity.getAmount() +"', amountwithtaxes = '" + entity.getAmountWithTaxes() + "', paymentmethod = '" + entity.getPaymentMethod() + "', taxamount = '" + entity.getTaxAmount() + "'  where id = " + id);        
        return q.executeUpdate();
    }

}
