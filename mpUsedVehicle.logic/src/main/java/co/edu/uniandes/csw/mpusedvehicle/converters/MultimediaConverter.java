/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.MultimediaDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.MultimediaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author miguelolivares
 */
public class MultimediaConverter {
    
    
    private MultimediaConverter() {
    }
    
    
    
    public static MultimediaDTO refEntity2DTO(MultimediaEntity entity) {
        if (entity != null) {
            MultimediaDTO dto = new MultimediaDTO();
            dto.setId(entity.getId());
            dto.setVehiclename(entity.getVehiclename());
            dto.setImage(entity.getImage());  
            return dto;
        } else {
            return null;
        }
    }
    
    
    public static MultimediaEntity refDTO2Entity(MultimediaDTO dto) {
        if (dto != null) {
            MultimediaEntity entity = new MultimediaEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }
    
    
    
    private static MultimediaDTO  basicEntity2DTO(MultimediaEntity entity) {
        if (entity != null) {
            MultimediaDTO dto = new MultimediaDTO();
            dto.setId(entity.getId());
            dto.setVehiclename(entity.getVehiclename());
            dto.setImage(entity.getImage());
            return dto;
        } else {
            return null;
        }
    }
    
    
    private static MultimediaEntity basicDTO2Entity(MultimediaDTO dto) {
        if (dto != null) {
            MultimediaEntity entity = new MultimediaEntity();
            entity.setId(dto.getId());
            
            entity.setVehiclename(dto.getVehiclename());
            entity.setImage(dto.getImage());
            
            return entity;
        } else {
            return null;
        }
    }
    
    
    public static MultimediaDTO fullEntity2DTO(MultimediaEntity entity) {
        if (entity != null) {
            MultimediaDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @param dto
     * @return 
     * @generated
     */
    public static MultimediaEntity fullDTO2Entity(MultimediaDTO dto) {
        if (dto != null) {
            MultimediaEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @param entities
     * @return 
     * @generated
     */

    public static List<MultimediaDTO> listEntity2DTO(List<MultimediaEntity> entities) {
        List<MultimediaDTO> dtos = new ArrayList<MultimediaDTO>();
        if (entities != null) {
            for (MultimediaEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @param dtos
     * @return 
     * @generated
     */
    public static List<MultimediaEntity> listDTO2Entity(List<MultimediaDTO> dtos) {
        List<MultimediaEntity> entities = new ArrayList<MultimediaEntity>();
        if (dtos != null) {
            for (MultimediaDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
