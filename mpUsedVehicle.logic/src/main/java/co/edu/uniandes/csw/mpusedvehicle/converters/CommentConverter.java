package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.CommentDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CommentEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class CommentConverter {

    /**
     * @generated
     */
    private CommentConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static CommentDTO refEntity2DTO(CommentEntity entity) {
        if (entity != null) {
            CommentDTO dto = new CommentDTO();
            dto.setId(entity.getId());
            dto.setDescription(entity.getDescription());
            dto.setDate(entity.getDate());
            dto.setClient(ClientConverter.refEntity2DTO(entity.getClient()));
            dto.setProduct(ProductConverter.refEntity2DTO(entity.getProduct()));

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
    public static CommentEntity refDTO2Entity(CommentDTO dto) {
        if (dto != null) {
            CommentEntity entity = new CommentEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static CommentDTO basicEntity2DTO(CommentEntity entity) {
        if (entity != null) {
            CommentDTO dto = new CommentDTO();
            dto.setId(entity.getId());
            dto.setDate(entity.getDate());
            dto.setDescription(entity.getDescription());
            dto.setClient(ClientConverter.refEntity2DTO(entity.getClient()));
         
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static CommentEntity basicDTO2Entity(CommentDTO dto) {
        if (dto != null) {
            CommentEntity entity = new CommentEntity();
            entity.setId(dto.getId());
            entity.setDescription(dto.getDescription());
            entity.setDate(dto.getDate());
            entity.setClient(ClientConverter.refDTO2Entity(dto.getClient()));
            entity.setProduct(ProductConverter.refDTO2Entity(dto.getProduct()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static CommentDTO fullEntity2DTO(CommentEntity entity) {
        if (entity != null) {
            CommentDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static CommentEntity fullDTO2Entity(CommentDTO dto) {
        if (dto != null) {
            CommentEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */

    public static List<CommentDTO> listEntity2DTO(List<CommentEntity> entities) {
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        if (entities != null) {
            for (CommentEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<CommentEntity> listDTO2Entity(List<CommentDTO> dtos) {
        List<CommentEntity> entities = new ArrayList<CommentEntity>();
        if (dtos != null) {
            for (CommentDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * @generated
     */
    public static CommentEntity childDTO2Entity(CommentDTO dto, ProductEntity parent){
        CommentEntity entity = basicDTO2Entity(dto);
        entity.setProduct(parent);
        return entity;
    }

    /**
     * @generated
     */
    public static List<CommentEntity> childListDTO2Entity(List<CommentDTO> dtos, ProductEntity parent) {
        List<CommentEntity> entities = new ArrayList<CommentEntity>();
        if (dtos != null) {
            for (CommentDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
}
