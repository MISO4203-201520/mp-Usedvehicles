package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.OfferDTO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author d.jimenez13
 */
public interface IOfferLogic {
    
    public int countOffers();
    public List<OfferDTO> getOffers(Integer page, Integer maxRecords);
    public OfferDTO getOffer(Long id);
    public OfferDTO createOffer(OfferDTO dto);
    public OfferDTO updateOffer(OfferDTO dto);
    public void deleteOffer(Long id);
}
