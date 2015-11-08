/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.OrderDTO;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.OrderEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author die-agud@suniandes.edu.co
 */
public abstract class OrderConverter {
    
    private OrderConverter(){
    }
    
    /**
     * DTO
     * @param entity
     * @return 
     */
    public static OrderDTO refEntity2DTO(OrderEntity entity){
        if (entity != null) {
            OrderDTO dto = new OrderDTO();
            dto.setId(entity.getId());
            dto.setTransactionId(entity.getTransactionId());
            dto.setAmount(entity.getAmount());
            dto.setTaxAmount(entity.getTaxAmount());
            dto.setAmountWithTaxes(entity.getAmountWithTaxes());
            dto.setPaymentMethod(entity.getPaymentMethod());
            dto.setOrderStatus(entity.getOrderStatus());
            dto.setCartItems(CartItemConverter.listEntity2DTO(entity.getCartItems()));

            return dto;
        } else {
            return null;
        }
    }
/**
     * DTO
     * @param entity
     * @return 
     */
    public static OrderDTO refEntity2DTOnotCartItem(OrderEntity entity){
        if (entity != null) {
            OrderDTO dto = new OrderDTO();
            dto.setId(entity.getId());
            dto.setTransactionId(entity.getTransactionId());
            dto.setAmount(entity.getAmount());
            dto.setTaxAmount(entity.getTaxAmount());
            dto.setAmountWithTaxes(entity.getAmountWithTaxes());
            dto.setPaymentMethod(entity.getPaymentMethod());
            dto.setOrderStatus(entity.getOrderStatus());
            return dto;
        } else {
            return null;
        }
    }    
    
    /**
     * Entidad 
     * @param dto
     * @return 
     */
    public static OrderEntity refDTO2Entity(OrderDTO dto){
        if (dto != null) {
            OrderEntity entity = new OrderEntity();
            entity.setId(dto.getId());
            entity.setTransactionId(dto.getTransactionId());
            entity.setAmount(dto.getAmount());
            entity.setTaxAmount(dto.getTaxAmount());
            entity.setAmountWithTaxes(dto.getAmountWithTaxes());
            entity.setPaymentMethod(dto.getPaymentMethod());
            entity.setOrderStatus(dto.getOrderStatus());
            entity.setCartItems(CartItemConverter.listDTO2Entity(dto.getCartItems()));
            
            return entity;
        } else {
            return null;
        }
    }
    
    /**
     * Lista de DTO�s
     * @param entities
     * @return 
     */
    public static List<OrderDTO> listEntity2DTO(List<OrderEntity> entities) {
        List<OrderDTO> dtos = new ArrayList<OrderDTO>();
        if (entities != null) {
            for (OrderEntity entity : entities) {
                dtos.add(refEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * Lista de entidades
     * @param dtos
     * @return 
     */
    public static List<OrderEntity> listDTO2Entity(List<OrderDTO> dtos) {
        List<OrderEntity> entities = new ArrayList<OrderEntity>();
        if (dtos != null) {
            for (OrderDTO dto : dtos) {
                entities.add(refDTO2Entity(dto));
            }
        }
        return entities;
    }
}
