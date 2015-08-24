package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.VehicleDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.VehicleEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class VehicleConverter {

    /**
     * @generated
     */
    private VehicleConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static VehicleDTO refEntity2DTO(VehicleEntity entity) {
        if (entity != null) {
            VehicleDTO dto = new VehicleDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setColor(entity.getColor());
            dto.setModel(entity.getModel());
            dto.setCapacity(entity.getCapacity());
            dto.setBrand(entity.getBrand());
            dto.setImage(entity.getImage());

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
    public static VehicleEntity refDTO2Entity(VehicleDTO dto) {
        if (dto != null) {
            VehicleEntity entity = new VehicleEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static VehicleDTO basicEntity2DTO(VehicleEntity entity) {
        if (entity != null) {
            VehicleDTO dto = new VehicleDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setColor(entity.getColor());
            dto.setModel(entity.getModel());
            dto.setCapacity(entity.getCapacity());
            dto.setBrand(entity.getBrand());
            dto.setImage(entity.getImage());

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static VehicleEntity basicDTO2Entity(VehicleDTO dto) {
        if (dto != null) {
            VehicleEntity entity = new VehicleEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setColor(dto.getColor());
            entity.setModel(dto.getModel());
            entity.setCapacity(dto.getCapacity());
            entity.setBrand(dto.getBrand());
            entity.setImage(dto.getImage());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static VehicleDTO fullEntity2DTO(VehicleEntity entity) {
        if (entity != null) {
            VehicleDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static VehicleEntity fullDTO2Entity(VehicleDTO dto) {
        if (dto != null) {
            VehicleEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */

    public static List<VehicleDTO> listEntity2DTO(List<VehicleEntity> entities) {
        List<VehicleDTO> dtos = new ArrayList<VehicleDTO>();
        if (entities != null) {
            for (VehicleEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<VehicleEntity> listDTO2Entity(List<VehicleDTO> dtos) {
        List<VehicleEntity> entities = new ArrayList<VehicleEntity>();
        if (dtos != null) {
            for (VehicleDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
