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
import javax.persistence.Query;

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

    public List<ProductEntity> getProductsByAdvancedSearch(String brand, String model, Integer capacity, Integer price) {
        
        try {
            
            int startPrice = 0;
            int endPrice = 49;
            
            // Constructing the sql query
            String sql = " SELECT p "
                        + "FROM ProductEntity p "
                        + "WHERE    1 = 1 ";
            if (brand != null && !"".equalsIgnoreCase(brand)) {
                sql += "            AND UPPER(p.vehicle.brand) like UPPER(:brand) ";
            }
            if (model != null && !"".equalsIgnoreCase(model)) {
                sql += "            AND p.vehicle.model = :model ";
            }
            if (capacity != null && capacity > 0) {
                sql += "            AND p.vehicle.capacity = :capacity ";
            }
            if (price != null && price > 0) {
                sql += "            AND p.price between :startPrice AND :endPrice ";
                switch (price) {
                    case 2:
                        startPrice = 50;
                        endPrice = 100;
                        break;
                    case 3:
                        startPrice = 100;
                        endPrice = 99999999;
                        break;
                }
            }
            sql += "ORDER BY p.price";

            // Loading the sql query and setting parameters
            Query query = em.createQuery(sql);
            if (brand != null && !"".equalsIgnoreCase(brand)) {
                query.setParameter("brand", "%" + brand + "%");
            }
            if (model != null && !"".equalsIgnoreCase(model)) {
                query.setParameter("model", model);
            }
            if (capacity != null && capacity > 0) {
                query.setParameter("capacity", capacity);
            }
            if (price != null && price > 0) {
                query.setParameter("startPrice", startPrice);
                query.setParameter("endPrice", endPrice);
            }

            // Executing the query to get products
            List<ProductEntity> products = new ArrayList<ProductEntity>();
            products = query.getResultList();
            
            return products;
            
        } catch (NoResultException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
            return null;
        }
    }
}
