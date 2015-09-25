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
            params.put("nameVehicle","%" + nameVehicle.toUpperCase() + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getCheaperProductByVehicle", params);
            return list.get(0);
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
   public ProductEntity getProductByModel(String model) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("model","%" + model.toUpperCase() + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getProductByModel", params);
            return list.get(0);
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
      public ProductEntity getProductByBrand(String brand) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("brand","%" + brand.toUpperCase() + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getProductByBrand", params);
            return list.get(0);
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
    public ProductEntity getProductByCity(String city) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("city","%" + city.toUpperCase() + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getProductByCity", params);
            return list.get(0);
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
    public ProductEntity getProductByPriceRange(Integer lower, Integer upper) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("lower","%" + lower + "%");
            params.put("upper","%" + upper + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getProductByPriceRange", params);
            return list.get(0);
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
