package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class ProductPersistence extends CrudPersistence<ProductEntity> {

    /**
     * @generated
     */
    public ProductPersistence() {
        this.entityClass = ProductEntity.class;
    }

    public List<ProductEntity> getByVehicleName(String name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", "%" + name.toUpperCase() + "%");
        return executeListNamedQuery("Product.getByVehicleName", params);
    }

    public ProductEntity getCheaperProductByProvider(String nameProvider) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("nameProvider", "%" + nameProvider.toUpperCase() + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getCheaperProductByProvider", params);
            return list.get(0);
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }

    public ProductEntity getCheaperProductByVehicle(String nameVehicle) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("nameVehicle", "%" + nameVehicle.toUpperCase() + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getCheaperProductByVehicle", params);
            return list.get(0);
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
