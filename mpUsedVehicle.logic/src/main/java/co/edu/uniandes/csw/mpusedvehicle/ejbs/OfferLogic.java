package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IOfferLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.OfferConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.OfferDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.OfferEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.OfferPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author d.jimenez13
 */
public class OfferLogic implements IOfferLogic {
    
    @Inject private OfferPersistence persistence;

    public int countOffers() {
        return persistence.count();
    }

    public List<OfferDTO> getOffers(Integer page, Integer maxRecords) {
        return OfferConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    public OfferDTO getOffer(Long id) {
        return OfferConverter.fullEntity2DTO(persistence.find(id));
    }

    public OfferDTO createOffer(OfferDTO dto) {
        OfferEntity entity = OfferConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return OfferConverter.fullEntity2DTO(entity);
    }

    public OfferDTO updateOffer(OfferDTO dto) {
        OfferEntity entity = persistence.update(OfferConverter.fullDTO2Entity(dto));
        return OfferConverter.fullEntity2DTO(entity);
    }

    public void deleteOffer(Long id) {
        persistence.delete(id);
    }
}
