package co.edu.uniandes.csw.mpusedvehicle.converters;

import co.edu.uniandes.csw.mpusedvehicle.dtos.MessageDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.MessageEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * @generated
 */
public abstract class MessageConverter {

    /**
     * @generated
     */
    private MessageConverter() {
    }

    /**
     * @param entity
     * @return
     * @generated
     */
    public static MessageDTO refEntity2DTO(MessageEntity entity) {
        if (entity != null) {
            MessageDTO dto = new MessageDTO();
            dto.setId(entity.getId());
            dto.setClient(ClientConverter.refEntity2DTO(entity.getClient()));
            dto.setProvider(ProviderConverter.refEntity2DTO(entity.getProvider()));
            dto.setIdTypeMessage(entity.getIdTypeMessage());
            dto.setDate(entity.getDate());
            dto.setQuestion(entity.getQuestion());
            dto.setProduct(ProductConverter.fullEntity2DTO(entity.getProduct()));
            
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
    public static MessageEntity refDTO2Entity(MessageDTO dto) {
        if (dto != null) {
            MessageEntity entity = new MessageEntity();
            entity.setId(dto.getId());

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static MessageDTO basicEntity2DTO(MessageEntity entity) {
        if (entity != null) {
            MessageDTO dto = new MessageDTO();
            dto.setId(entity.getId());
            dto.setClient(ClientConverter.refEntity2DTO(entity.getClient()));
            dto.setProvider(ProviderConverter.refEntity2DTO(entity.getProvider()));
            dto.setIdTypeMessage(entity.getIdTypeMessage());
            dto.setDate(entity.getDate());
            dto.setQuestion(entity.getQuestion());
            dto.setProduct(ProductConverter.refEntity2DTO(entity.getProduct()));

            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    private static MessageEntity basicDTO2Entity(MessageDTO dto) {
        if (dto != null) {
            MessageEntity entity = new MessageEntity();
            entity.setId(dto.getId());
            entity.setClient(ClientConverter.refDTO2Entity(dto.getClient()));
            entity.setProvider(ProviderConverter.refDTO2Entity(dto.getProvider()));
            entity.setIdTypeMessage(dto.getIdTypeMessage());
            entity.setDate(dto.getDate());
            entity.setQuestion(dto.getQuestion());
            entity.setProduct(ProductConverter.fullDTO2Entity(dto.getProduct()));

            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static MessageDTO fullEntity2DTO(MessageEntity entity) {
        if (entity != null) {
            MessageDTO dto = basicEntity2DTO(entity);
            return dto;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */
    public static MessageEntity fullDTO2Entity(MessageDTO dto) {
        if (dto != null) {
            MessageEntity entity = basicDTO2Entity(dto);
            return entity;
        } else {
            return null;
        }
    }

    /**
     * @generated
     */

    public static List<MessageDTO> listEntity2DTO(List<MessageEntity> entities) {
        List<MessageDTO> dtos = new ArrayList<MessageDTO>();
        if (entities != null) {
            for (MessageEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    /**
     * @generated
     */
    public static List<MessageEntity> listDTO2Entity(List<MessageDTO> dtos) {
        List<MessageEntity> entities = new ArrayList<MessageEntity>();
        if (dtos != null) {
            for (MessageDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }

}
