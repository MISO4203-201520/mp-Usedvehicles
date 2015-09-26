package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.OfferDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.OfferEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.jimenez13
 */
public abstract class OfferConverter {
    
    private OfferConverter() {
    }
    
    /**
     * @param entity
     * @return dto
     */
    public static OfferDTO refEntity2DTO(OfferEntity entity) {
        if (entity != null) {
            OfferDTO dto = new OfferDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @param dto
     * @return entity
     */
    public static OfferEntity refDTO2Entity(OfferDTO dto) {
        if (dto != null) {
            OfferEntity entity = new OfferEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    private static OfferDTO basicEntity2DTO(OfferEntity entity) {
        if (entity != null) {
            OfferDTO dto = new OfferDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());

            return dto;
        } else {
            return null;
        }
    }

    private static OfferEntity basicDTO2Entity(OfferDTO dto) {
        if (dto != null) {
            OfferEntity entity = new OfferEntity();
            entity.setId(dto.getId());
            entity.setDescription(dto.getDescription());
            entity.setStartDate(dto.getStartDate());
            entity.setEndDate(dto.getEndDate());

            return entity;
        } else {
            return null;
        }
    }

    public static OfferDTO fullEntity2DTO(OfferEntity entity) {
        if (entity != null) {
            OfferDTO dto = basicEntity2DTO(entity);
            
            return dto;
        } else {
            return null;
        }
    }

    public static OfferEntity fullDTO2Entity(OfferDTO dto) {
        if (dto != null) {
            OfferEntity entity = basicDTO2Entity(dto);
            
            return entity;
        } else {
            return null;
        }
    }

    public static List<OfferDTO> listEntity2DTO(List<OfferEntity> entities) {
        List<OfferDTO> dtos = new ArrayList<OfferDTO>();
        if (entities != null) {
            for (OfferEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<OfferEntity> listDTO2Entity(List<OfferDTO> dtos) {
        List<OfferEntity> entities = new ArrayList<OfferEntity>();
        if (dtos != null) {
            for (OfferDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
