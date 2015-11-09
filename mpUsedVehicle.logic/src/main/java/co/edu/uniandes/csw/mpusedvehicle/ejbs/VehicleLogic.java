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
     * @return 
     * @generated
     */
    @Override
    public int countVehicles() {
        return persistence.count();
    }

    /**
     * @param page
     * @param maxRecords
     * @return 
     * @generated
     */
    @Override
    public List<VehicleDTO> getVehicles(Integer page, Integer maxRecords) {
        return VehicleConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    /**
     * @param id
     * @return 
     * @generated
     */
    @Override
    public VehicleDTO getVehicle(Long id) {
        return VehicleConverter.fullEntity2DTO(persistence.find(id));
    }

    /**
     * @param dto
     * @return 
     * @generated
     */
    @Override
    public VehicleDTO createVehicle(VehicleDTO dto) {
        VehicleEntity entity = VehicleConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return VehicleConverter.fullEntity2DTO(entity);
    }

    /**
     * @param dto
     * @return 
     * @generated
     */
    @Override
    public VehicleDTO updateVehicle(VehicleDTO dto) {
        VehicleEntity entity = persistence.update(VehicleConverter.fullDTO2Entity(dto));
        return VehicleConverter.fullEntity2DTO(entity);
    }

    /**
     * @param id
     * @generated
     */
    @Override
    public void deleteVehicle(Long id) {
        persistence.delete(id);
    }

    /**
     * @param name
     * @return 
     * @generated
     */
    @Override
    public List<VehicleDTO> findByName(String name) {
        return VehicleConverter.listEntity2DTO(persistence.findByName(name));
    }
}
