/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.PaymentMethodEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author die-agud@suniandes.edu.co
 */
public abstract class PaymentMethodConverter {
    
    private PaymentMethodConverter(){
    }
    
    /**
     * DTO
     * @param entity
     * @return 
     */
    public static PaymentMethodDTO refEntity2DTO(PaymentMethodEntity entity){
        if (entity != null) {
            PaymentMethodDTO dto = new PaymentMethodDTO();
            dto.setId(entity.getId());
            dto.setType(entity.getType());
            dto.setAgreements(AgreementConverter.listEntity2DTO(entity.getAgreements()));

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
    public static PaymentMethodEntity refDTO2Entity(PaymentMethodDTO dto){
        if (dto != null) {
            
            PaymentMethodEntity entity = new PaymentMethodEntity();
            entity.setId(dto.getId());
            entity.setType(dto.getType());
            entity.setAgreements(AgreementConverter.listDTO2Entity(dto.getAgreements()));
            
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
    public static List<PaymentMethodDTO> listEntity2DTO(List<PaymentMethodEntity> entities) {
        List<PaymentMethodDTO> dtos = new ArrayList<PaymentMethodDTO>();
        if (entities != null) {
            for (PaymentMethodEntity entity : entities) {
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
    public static List<PaymentMethodEntity> listDTO2Entity(List<PaymentMethodDTO> dtos) {
        List<PaymentMethodEntity> entities = new ArrayList<PaymentMethodEntity>();
        if (dtos != null) {
            for (PaymentMethodDTO dto : dtos) {
                entities.add(refDTO2Entity(dto));
            }
        }
        return entities;
    }
}
