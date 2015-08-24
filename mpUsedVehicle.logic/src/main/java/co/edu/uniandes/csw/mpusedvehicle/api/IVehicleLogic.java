package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.VehicleDTO;
import java.util.List;

public interface IVehicleLogic {
    public int countVehicles();
    public List<VehicleDTO> getVehicles(Integer page, Integer maxRecords);
    public VehicleDTO getVehicle(Long id);
    public VehicleDTO createVehicle(VehicleDTO dto);
    public VehicleDTO updateVehicle(VehicleDTO dto);
    public void deleteVehicle(Long id);
    public List<VehicleDTO> findByName(String name);
}
