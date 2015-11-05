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

    @Inject
    private AdminPersistence persistence;

    @Override
    public int countAdmins() {
        return persistence.count();
    }

    @Override
    public List<AdminDTO> getAdmins(Integer page, Integer maxRecords) {
        return AdminConverter.listEntity2DTO(persistence.findAll(page, maxRecords));
    }

    @Override
    public AdminDTO getAdmin(Long id) {
        return AdminConverter.fullEntity2DTO(persistence.find(id));
    }

    @Override
    public AdminDTO createAdmin(AdminDTO dto) {
        AdminEntity entity = AdminConverter.fullDTO2Entity(dto);
        persistence.create(entity);
        return AdminConverter.fullEntity2DTO(entity);
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO dto) {
        AdminEntity entity = persistence.update(AdminConverter.fullDTO2Entity(dto));
        return AdminConverter.fullEntity2DTO(entity);
    }

    @Override
    public void deleteAdmin(Long id) {
        persistence.delete(id);
    }

    @Override
    public List<AdminDTO> findByName(String name) {
        return AdminConverter.listEntity2DTO(persistence.findByName(name));
    }

    @Override
    public AdminDTO getAdminByUserId(String userId) {
        return AdminConverter.refEntity2DTO(persistence.getAdminByUserId(userId));
    }
}
