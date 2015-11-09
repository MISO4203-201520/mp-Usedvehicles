package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.ICartItemLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.CartItemConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.CartItemDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.CartItemEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.CartItemPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class CartItemLogic implements ICartItemLogic {

    @Inject
    private CartItemPersistence persistence;

    /**
     * @generated @return
     */
    @Override
    public int countCartItems() {
        return persistence.count();
    }

    /**
     * @generated @return
     */
    @Override
    public List<CartItemDTO> getCartItems(Integer page, Integer maxRecords) {
        return CartItemConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated @return
     */
    @Override
    public CartItemDTO getCartItem(Long id) {
        return CartItemConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated @return
     */
    @Override
    public CartItemDTO createCartItem(CartItemDTO dto) {
        CartItemEntity entity = CartItemConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return CartItemConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated @return
     */
    @Override
    public CartItemDTO updateCartItem(CartItemDTO dto) {
        CartItemEntity entity = persistence.update(CartItemConverter.fullDTO2Entity(dto));
        return CartItemConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteCartItem(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated @return
     */
    @Override
    public List<CartItemDTO> findByName(String name) {
        return CartItemConverter.listEntity2DTO(persistence.findByName(name));
    }

    /**
     * @return
     */
    @Override
    public List<CartItemDTO> getCartItemsByClient(Integer page, Integer maxRecords, Long idClient) {
        return persistence.getCartItemsByClient(page, maxRecords, idClient);
    }

    /**
     *
     * @param dto
     * @param idClient
     * @return
     */
    @Override
    public CartItemDTO createCartItemByClient(CartItemDTO dto, Long idClient) {
        return persistence.createCartItemByClient(dto, idClient);
    }

    /**
     *
     * @param id
     * @param idClient
     * @return
     */
    @Override
    public CartItemDTO getCartItemsByClientById(Long id, Long idClient) {
        return persistence.getCartItemsByClientById(idClient, idClient);
    }

    /**
     *
     * @param idClient
     * @param dto
     * @return
     * @return
     */
    @Override
    public CartItemDTO updateCartItemByClient(Long idClient, CartItemDTO dto) {
        CartItemDTO result = new CartItemDTO();
        if (dto.getClient().getId().equals(idClient)) {
            result = updateCartItem(dto);
        }
        return result;
    }

    /**
     *
     * @param idClient
     * @param id
     * @return
     */
    @Override
    public void deleteCartItemByClient(Long idClient, Long id) {
        persistence.deleteCartItemByClient(idClient, id);
    }

    /**
     *
     * @param idClient
     * @return
     */
    @Override
    public int countCartItemsByClient(Long idClient) {
        return persistence.countByClient(idClient);
    }
    
    
    @Override
    public List<CartItemDTO> getHistoryByClient(Integer page, Integer maxRecords, Long idClient){
        return persistence.historyByClient(page, maxRecords,idClient);
    }

}
