package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.PaymentMethodEntity;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * @generated
 */
@Stateless
public class PaymentMethodPersistence extends CrudPersistence<PaymentMethodEntity> {

    /**
     * @generated
     */
    public PaymentMethodPersistence() {
        this.entityClass = PaymentMethodEntity.class;
    }

}
