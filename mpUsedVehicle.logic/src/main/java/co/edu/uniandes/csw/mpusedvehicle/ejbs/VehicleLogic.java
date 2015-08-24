package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IVehicleLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.VehicleConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.VehicleDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.VehicleEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.VehiclePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class VehicleLogic implements IVehicleLogic {

    @Inject private VehiclePersistence persistence;

    /**
     * @generated
     */
    public int countVehicles() {
        return persistence.count();
    }

    /**
     * @generated
     */
    public List<VehicleDTO> getVehicles(Integer page, Integer maxRecords) {
        return VehicleConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @generated
     */
    public VehicleDTO getVehicle(Long id) {
        return VehicleConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @generated
     */
    public VehicleDTO createVehicle(VehicleDTO dto) {
        VehicleEntity entity = VehicleConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return VehicleConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public VehicleDTO updateVehicle(VehicleDTO dto) {
        VehicleEntity entity = persistence.update(VehicleConverter.fullDTO2Entity(dto));
        return VehicleConverter.fullEntity2DTO(entity);
    }

    /**
     * @generated
     */
    public void deleteVehicle(Long id) {
        persistence.delete(id);
    }

    /**
     * @generated
     */
    public List<VehicleDTO> findByName(String name) {
        return VehicleConverter.listEntity2DTO(persistence.findByName(name));
    }
}
