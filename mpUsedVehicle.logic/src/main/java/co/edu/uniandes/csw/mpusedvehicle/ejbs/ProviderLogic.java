package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IProviderLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.ProviderConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.ProviderDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProviderEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.ProviderPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ProviderLogic implements IProviderLogic {

    @Inject
    private ProviderPersistence persistence;

    /**
     * @return @generated
     */
    @Override
    public int countProviders() {
        return persistence.count();
    }

    /**
     * @param page
     * @param maxRecords
     * @return
     * @generated
     */
    @Override
    public List<ProviderDTO> getProviders(Integer page, Integer maxRecords) {
        return ProviderConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @param id
     * @return
     * @generated
     */
    @Override
    public ProviderDTO getProvider(Long id) {
        return ProviderConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @param dto
     * @return
     * @generated
     */
    @Override
    public ProviderDTO createProvider(ProviderDTO dto) {
        ProviderEntity entity = ProviderConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return ProviderConverter.fullEntity2DTO(entity);
    }

    /**
     * @param dto
     * @return
     * @generated
     */
    @Override
    public ProviderDTO updateProvider(ProviderDTO dto) {
        ProviderEntity entity = persistence.update(ProviderConverter.fullDTO2Entity(dto));
        return ProviderConverter.fullEntity2DTO(entity);
    }

    /**
     * @param id
     * @generated
     */
    @Override
    public void deleteProvider(Long id) {
        persistence.delete(id);
    }

    /**
     * @param name
     * @return
     * @generated
     */
    @Override
    public List<ProviderDTO> findByName(String name) {
        return ProviderConverter.listEntity2DTO(persistence.findByName(name));
    }

    @Override
    public ProviderDTO getProviderByUserId(String userId) {
        return ProviderConverter.fullEntity2DTO(persistence.getProviderByUserId(userId));
    }

    @Override
    public ProviderDTO getProviderByModel(String model) {
        return ProviderConverter.fullEntity2DTO(persistence.getProviderByModel(model));
    }

    @Override
    public ProviderDTO getProviderByBrand(String brand) {
        return ProviderConverter.fullEntity2DTO(persistence.getProviderByBrand(brand));
    }

    @Override
    public ProviderDTO getProviderByCity(String city) {
        return ProviderConverter.fullEntity2DTO(persistence.getProviderByCity(city));
    }

    @Override
    public ProviderDTO getProviderByPriceRange(Integer lower, Integer upper) {
        return ProviderConverter.fullEntity2DTO(persistence.getProviderByPriceRange(lower, upper));
    }

    @Override
    public List<ProviderDTO> getProviders() {
        return ProviderConverter.listEntity2DTO(persistence.getProviders());
    }

    @Override
    public ProviderDTO getProviderById(Long Id) {
        return ProviderConverter.refEntity2DTO(persistence.getProviderById(Id));
    }
}
