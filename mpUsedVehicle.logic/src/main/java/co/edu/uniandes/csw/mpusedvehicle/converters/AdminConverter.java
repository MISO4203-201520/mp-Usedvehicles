package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.AdminDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.AdminEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author d.jimenez13
 */
public abstract class AdminConverter {
    
    private AdminConverter() {
    }
    
    /**
     * @param entity
     * @return dto
     */
    public static AdminDTO refEntity2DTO(AdminEntity entity) {
        if (entity != null) {
            AdminDTO dto = new AdminDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUserId(entity.getUserId());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @param dto
     * @return entity
     */
    public static AdminEntity refDTO2Entity(AdminDTO dto) {
        if (dto != null) {
            AdminEntity entity = new AdminEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    public static AdminDTO basicEntity2DTO(AdminEntity entity) {
        if (entity != null) {
            AdminDTO dto = new AdminDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setUserId(entity.getUserId());

            return dto;
        } else {
            return null;
        }
    }

    public static AdminEntity basicDTO2Entity(AdminDTO dto) {
        if (dto != null) {
            AdminEntity entity = new AdminEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setUserId(dto.getUserId());

            return entity;
        } else {
            return null;
        }
    }

    public static AdminDTO fullEntity2DTO(AdminEntity entity) {
        if (entity != null) {
            AdminDTO dto = basicEntity2DTO(entity);
            
            return dto;
        } else {
            return null;
        }
    }

    public static AdminEntity fullDTO2Entity(AdminDTO dto) {
        if (dto != null) {
            AdminEntity entity = basicDTO2Entity(dto);
            
            return entity;
        } else {
            return null;
        }
    }

    public static List<AdminDTO> listEntity2DTO(List<AdminEntity> entities) {
        List<AdminDTO> dtos = new ArrayList<AdminDTO>();
        if (entities != null) {
            for (AdminEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<AdminEntity> listDTO2Entity(List<AdminDTO> dtos) {
        List<AdminEntity> entities = new ArrayList<AdminEntity>();
        if (dtos != null) {
            for (AdminDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
