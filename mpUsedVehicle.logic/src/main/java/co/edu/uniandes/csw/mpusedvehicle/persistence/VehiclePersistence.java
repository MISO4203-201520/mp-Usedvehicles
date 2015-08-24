package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.VehicleEntity;
import javax.ejb.Stateless;

/**
 * @generated
 */
@Stateless
public class VehiclePersistence extends CrudPersistence<VehicleEntity> {

    /**
     * @generated
     */
    public VehiclePersistence() {
        this.entityClass = VehicleEntity.class;
    }
}
