package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.ProviderEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @generated
 */
@Stateless
public class ProviderPersistence extends CrudPersistence<ProviderEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderPersistence.class);

    /**
     * @generated
     */
    public ProviderPersistence() {
        this.entityClass = ProviderEntity.class;
    }

    public ProviderEntity getProviderByUserId(String userId) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("user_id", userId);
            return this.executeSingleNamedQuery("Provider.getByUserId", params);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ProviderEntity getProviderByModel(String model) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("model", "%" + model.toUpperCase() + "%");
            List<ProviderEntity> list = new ArrayList<ProviderEntity>();
            list = executeListNamedQuery("Provider.getProviderByModel", params);
            return list.get(0);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ProviderEntity getProviderByBrand(String brand) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("brand", "%" + brand.toUpperCase() + "%");
            List<ProviderEntity> list = new ArrayList<ProviderEntity>();
            list = executeListNamedQuery("Provider.getProviderByBrand", params);
            return list.get(0);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ProviderEntity getProviderByCity(String city) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("city", "%" + city.toUpperCase() + "%");
            List<ProviderEntity> list = new ArrayList<ProviderEntity>();
            list = executeListNamedQuery("Provider.getProviderByCity", params);
            return list.get(0);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ProviderEntity getProviderByPriceRange(Integer lower, Integer upper) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("lower", lower);
            params.put("upper", upper);
            List<ProviderEntity> list = new ArrayList<ProviderEntity>();
            list = executeListNamedQuery("Provider.getProviderByPriceRange", params);
            return list.get(0);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public List<ProviderEntity> getProviders() {
        try {
            return executeListNamedQuery("Provider.getProviders");
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    public ProviderEntity getProviderById(Long Id) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", Id);
            return this.executeSingleNamedQuery("Provider.getById", params);
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
