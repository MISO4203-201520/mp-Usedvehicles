package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IAdminLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.AdminConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.AdminDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.AdminEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.AdminPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author d.jimenez13
 */
@Stateless
public class AdminLogic implements IAdminLogic {
    
    @Inject private AdminPersistence persistence;

    public int countAdmins() {
        return persistence.count();
    }

    public List<AdminDTO> getAdmins(Integer page, Integer maxRecords) {
        return AdminConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    public AdminDTO getAdmin(Long id) {
        return AdminConverter.fullEntity2DTO(persistence.find(id));
    }

    public AdminDTO createAdmin(AdminDTO dto) {
        AdminEntity entity = AdminConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return AdminConverter.fullEntity2DTO(entity);
    }

    public AdminDTO updateAdmin(AdminDTO dto) {
        AdminEntity entity = persistence.update(AdminConverter.fullDTO2Entity(dto));
        return AdminConverter.fullEntity2DTO(entity);
    }

    public void deleteAdmin(Long id) {
        persistence.delete(id);
    }

    public List<AdminDTO> findByName(String name) {
        return AdminConverter.listEntity2DTO(persistence.findByName(name));
    }
    
    public AdminDTO getAdminByUserId(String userId) {
        return AdminConverter.refEntity2DTO(persistence.getAdminByUserId(userId));
    }
}
