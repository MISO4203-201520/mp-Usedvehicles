package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mp.ann.MPLoCAnn;
import co.edu.uniandes.csw.mpusedvehicle.entities.ProductEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @generated
 */
@Stateless
public class ProductPersistence extends CrudPersistence<ProductEntity> {

    private static Logger LOGGER = LoggerFactory.getLogger(AdminPersistence.class);

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
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
    
    public List<ProductEntity> getProductByProvider(String nameProvider) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("nameProvider", "%" + nameProvider.toUpperCase() + "%");
            List<ProductEntity> list = new ArrayList<ProductEntity>();
            list = executeListNamedQuery("Product.getProductByProvider", params);
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
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
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getVehiclesName() {
        try {
            List<String> list = new ArrayList<String>();
            list = executeListNamedQuery("Product.getVehiclesName");
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getVehiclesBrand() {
        try {
            List<String> list = new ArrayList<String>();
            list = executeListNamedQuery("Product.getVehiclesBrand");
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getVehiclesCapacity() {
        try {
            List<Integer> listInteger = new ArrayList<Integer>();
            List<String> list = new ArrayList<String>();
            listInteger = executeListNamedQuery("Product.getVehiclesCapacity");
            for (Integer temp : listInteger) {
                list.add(temp.toString());
            }
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getVehiclesColor() {
        try {
            List<String> list = new ArrayList<String>();
            list = executeListNamedQuery("Product.getVehiclesColor");
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getVehiclesModel() {
        try {
            List<String> list = new ArrayList<String>();
            list = executeListNamedQuery("Product.getVehiclesModel");
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getVehiclesPlate() {
        try {
            List<String> list = new ArrayList<String>();
            list.add("Even");
            list.add("Odd");
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<String> getVehiclesLocation() {
        try {
            List<String> list = new ArrayList<String>();
            list = executeListNamedQuery("Product.getVehiclesLocation");
            return list;
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
    
    @MPLoCAnn(tier="Backend", reqId="R15")
    public List<ProductEntity> getProductsByAdvancedSearch(String brand, String model, Integer capacity, Integer price, String color, String plate, String location) {

        try {

            int startPrice = 0;
            int endPrice = 9999999;
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
            if (color != null && !"".equalsIgnoreCase(color)) {
                sql += "            AND p.vehicle.color = :color ";
            }
            if (plate != null && !"".equalsIgnoreCase(plate)) {
                if (plate.equals("Even")) {
                    sql += "            AND p.vehicle.plate = TRUE ";
                } else {
                    sql += "            AND p.vehicle.plate = FALSE ";
                }
            }
            if (capacity != null && capacity > 0) {
                sql += "            AND p.vehicle.capacity = :capacity ";
            }
            if (location != null && !"".equalsIgnoreCase(location)) {
                sql += "            AND p.vehicle.location = :location ";
            }
            if (price != null && price > 0) {
                sql += "            AND p.price between :startPrice AND :endPrice ";
                switch (price) {
                    case 2:
                        startPrice = 10000000;
                        endPrice = 99000000;
                        break;
                    case 3:
                        startPrice = 99000000;
                        endPrice = 999999999;
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
            if (color != null && !"".equalsIgnoreCase(color)) {
                query.setParameter("color", color);
            }
            if (location != null && !"".equalsIgnoreCase(location)) {
                query.setParameter("location", location);
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
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
