package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.ProductDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProviderEntity;

/**
 * @generated
 */
public abstract class ProductConverter {

    /**
     * @generated
     */
    private ProductConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static ProductDTO refEntity2DTO(ProductEntity entity) {
        if (entity != null) {
            ProductDTO dto = new ProductDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setVehicle(VehicleConverter.refEntity2DTO(entity.getVehicle()));
            dto.setComments(CommentConverter.listEntity2DTO(entity.getComments()));
            dto.setDiscount(entity.getDiscount());   //Nuevo Atributo REQ06
            dto.setRating(entity.getRating()); // Calificacion del producto
            dto.setAmmountVotes(entity.getAmmountVotes());
            dto.setPurchasedBy(ClientConverter.listEntity2DTO(entity.getPurchasedBy()));
            
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
    public static ProductEntity refDTO2Entity(ProductDTO dto) {
        if (dto != null) {
            ProductEntity entity = new ProductEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static ProductDTO basicEntity2DTO(ProductEntity entity) {
        if (entity != null) {
            ProductDTO dto = new ProductDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
            dto.setAvailability(entity.getAvailability());
            dto.setProvider(ProviderConverter.refEntity2DTO(entity.getProvider()));
            dto.setVehicle(VehicleConverter.refEntity2DTO(entity.getVehicle()));
            dto.setComments(CommentConverter.listEntity2DTO(entity.getComments()));
            dto.setDiscount(entity.getDiscount());   //Nuevo Atributo REQ06
            dto.setRating(entity.getRating()); // Calificacion del producto
            dto.setAmmountVotes(entity.getAmmountVotes());
            dto.setPurchasedBy(ClientConverter.listEntity2DTO(entity.getPurchasedBy()));
            
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static ProductEntity basicDTO2Entity(ProductDTO dto) {
        if (dto != null) {
            ProductEntity entity = new ProductEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());
            entity.setPrice(dto.getPrice());
            entity.setAvailability(dto.getAvailability());
            entity.setProvider(ProviderConverter.refDTO2Entity(dto.getProvider()));
            entity.setVehicle(VehicleConverter.refDTO2Entity(dto.getVehicle()));
            entity.setDiscount(dto.getDiscount());   //Nuevo Atributo REQ06
            entity.setRating(dto.getRating());
            entity.setAmmountVotes(dto.getAmmountVotes());
            entity.setPurchasedBy(ClientConverter.listDTO2Entity(dto.getPurchasedBy()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static ProductDTO fullEntity2DTO(ProductEntity entity) {
        if (entity != null) {
            ProductDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static ProductEntity fullDTO2Entity(ProductDTO dto) {
        if (dto != null) {
            ProductEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */

    public static List<ProductDTO> listEntity2DTO(List<ProductEntity> entities) {
        List<ProductDTO> dtos = new ArrayList<ProductDTO>();
        if (entities != null) {
            for (ProductEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<ProductEntity> listDTO2Entity(List<ProductDTO> dtos) {
        List<ProductEntity> entities = new ArrayList<ProductEntity>();
        if (dtos != null) {
            for (ProductDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * @generated
     */
    public static ProductEntity childDTO2Entity(ProductDTO dto, ProviderEntity parent){
        ProductEntity entity = basicDTO2Entity(dto);
        entity.setProvider(parent);
        return entity;
    }

    /**
     * @generated
     */
    public static List<ProductEntity> childListDTO2Entity(List<ProductDTO> dtos, ProviderEntity parent) {
        List<ProductEntity> entities = new ArrayList<ProductEntity>();
        if (dtos != null) {
            for (ProductDTO dto : dtos) {
                entities.add(childDTO2Entity(dto, parent));
            }
        }
        return entities;
    }
    public static List<ProductDTO> listString2DTO(List<String> listParams) {
        List<ProductDTO> dtos = new ArrayList<ProductDTO>();
        if (listParams != null) {
            for (String name : listParams) {
                ProductDTO dto = new ProductDTO();
                dto.setName(name);
                dtos.add(dto);
            }
        }
        return dtos;
    }
}
