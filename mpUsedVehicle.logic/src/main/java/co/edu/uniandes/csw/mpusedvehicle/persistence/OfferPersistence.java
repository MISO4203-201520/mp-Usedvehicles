package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.OfferEntity;

/**
 *
 * @author d.jimenez13
 */
public class OfferPersistence extends CrudPersistence<OfferEntity> {
    
    public OfferPersistence() {
        this.entityClass = OfferEntity.class;
    }
}
