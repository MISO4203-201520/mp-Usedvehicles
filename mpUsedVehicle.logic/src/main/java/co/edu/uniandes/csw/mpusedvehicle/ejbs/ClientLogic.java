package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IClientLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.ClientConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ClientDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.ClientEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.ClientPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ClientLogic implements IClientLogic {

    @Inject
    private ClientPersistence persistence;

    /**
     * @generated @return
     */
    @Override
    public int countClients() {
        return persistence.count();
    }

    /**
     * @generated @return
     */
    @Override
    public List<ClientDTO> getClients(Integer page, Integer maxRecords) {
        return ClientConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated @return
     */
    @Override
    public ClientDTO getClient(Long id) {
        return ClientConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated @return
     */
    @Override
    public ClientDTO createClient(ClientDTO dto) {
        ClientEntity entity = ClientConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return ClientConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated @return
     */
    @Override
    public ClientDTO updateClient(ClientDTO dto) {
        ClientEntity entity = persistence.update(ClientConverter.fullDTO2Entity(dto));
        return ClientConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public void deleteClient(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated @return
     */
    @Override
    public List<ClientDTO> findByName(String name) {
        return ClientConverter.listEntity2DTO(persistence.findByName(name));
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public ClientDTO getClientByUserId(String userId) {
        return ClientConverter.refEntity2DTO(persistence.getClientByUserId(userId));
    }

    /**
     *
     * @param Id
     * @return
     */
    @Override
    public ClientDTO getClientById(Long Id) {
        return ClientConverter.refEntity2DTO(persistence.getClientById(Id));
    }

    /**
     *
     * @return
     */
    @Override
    public List<ClientDTO> getIdANDUsername() {
        return ClientConverter.listEntity2DTO(persistence.getIdANDUserName());
    }
}
