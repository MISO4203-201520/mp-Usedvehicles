/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.AgreementDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.AgreementEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author die-agud@suniandes.edu.co
 */
public abstract class AgreementConverter {
    
    private AgreementConverter(){
    }
    
    /**
     * DTO
     * @param entity
     * @return 
     */
    public static AgreementDTO refEntity2DTO(AgreementEntity entity){
        if (entity != null) {
            AgreementDTO dto = new AgreementDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setLogo(entity.getLogo());
            dto.setTemplate(entity.getTemplate());

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
    public static AgreementEntity refDTO2Entity(AgreementDTO dto){
        if (dto != null) {
            
            AgreementEntity entity = new AgreementEntity();
            entity.setId(entity.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setLogo(dto.getLogo());
            entity.setTemplate(dto.getTemplate());
            
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
    public static List<AgreementDTO> listEntity2DTO(List<AgreementEntity> entities) {
        List<AgreementDTO> dtos = new ArrayList<AgreementDTO>();
        if (entities != null) {
            for (AgreementEntity entity : entities) {
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
    public static List<AgreementEntity> listDTO2Entity(List<AgreementDTO> dtos) {
        List<AgreementEntity> entities = new ArrayList<AgreementEntity>();
        if (dtos != null) {
            for (AgreementDTO dto : dtos) {
                entities.add(refDTO2Entity(dto));
            }
        }
        return entities;
    }
}
