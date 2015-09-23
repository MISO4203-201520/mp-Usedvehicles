package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.AdminDTO;
import java.util.List;

/**
 *
 * @author d.jimenez13
 */
public interface IAdminLogic {
    
    public int countAdmins();
    public List<AdminDTO> getAdmins(Integer page, Integer maxRecords);
    public AdminDTO getAdmin(Long id);
    public AdminDTO createAdmin(AdminDTO dto);
    public AdminDTO updateAdmin(AdminDTO dto);
    public void deleteAdmin(Long id);
    public List<AdminDTO> findByName(String name);
    public AdminDTO getAdminByUserId(String userId);
}
