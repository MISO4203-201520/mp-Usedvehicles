package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.ReviewDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.ReviewEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.VehicleEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.jimenez13
 */
public abstract class ReviewConverter {
    
    public ReviewConverter() {
    }
    
    public static ReviewDTO refEntity2DTO(ReviewEntity entity) {
        if (entity != null) {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setSource(entity.getSource());
            
            return dto;
        } else {
            return null;
        }
    }
    
    public static ReviewEntity refDTO2Entity(ReviewDTO dto) {
        if (dto != null) {
            ReviewEntity entity = new ReviewEntity();
            entity.setId(dto.getId());
            
            return entity;
        } else {
            return null;
        }
    }
    
    public static ReviewDTO basicEntity2DTO(ReviewEntity entity) {
        if (entity != null) {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setSource(entity.getSource());
            dto.setVehicle(VehicleConverter.refEntity2DTO(entity.getVehicle()));
            
            return dto;
        } else {
            return null;
        }
    }
    
    public static ReviewEntity basicDTO2Entity(ReviewDTO dto) {
        if (dto != null) {
            ReviewEntity entity = new ReviewEntity();
            entity.setId(dto.getId());
            entity.setVehicle(VehicleConverter.refDTO2Entity(dto.getVehicle()));
            
            return entity;
        } else {
            return null;
        }
    }
    
    public static ReviewDTO fullEntity2DTO(ReviewEntity entity) {
        if (entity != null) {
            ReviewDTO dto = basicEntity2DTO(entity);
            
            return dto;
        } else {
            return null;
        }
    }
    
    public static ReviewEntity fullDTO2Entity(ReviewDTO dto) {
        if (dto != null) {
            ReviewEntity entity = basicDTO2Entity(dto);
            
            return entity;
        } else {
            return null;
        }
    }
    
    public static List<ReviewDTO> listEntity2DTO(List<ReviewEntity> entities) {
        List<ReviewDTO> dtos = new ArrayList<ReviewDTO>();
        
        if (entities != null) {
            for (ReviewEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }
    
    public static List<ReviewEntity> listDTO2Entity(List<ReviewDTO> dtos) {
        List<ReviewEntity> entities = new ArrayList<ReviewEntity>();
        
        if (dtos != null) {
            for (ReviewDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
    public static ReviewEntity childDTO2Entity(ReviewDTO dto, VehicleEntity parent) {
        ReviewEntity entity = basicDTO2Entity(dto);
        entity.setVehicle(parent);
        
        return entity;
    }
    
    public static List<ReviewEntity> childListDTO2Entity(List<ReviewDTO> dtos, VehicleEntity parent) {
        List<ReviewEntity> entities = new ArrayList<ReviewEntity>();
        
        if (dtos != null) {
            for (ReviewDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
