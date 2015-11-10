/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IMultimediaLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.MultimediaConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.MultimediaDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.MultimediaEntity;
import co.edu.uniandes.csw.mpusedvehicle.persistence.MultimediaPersistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author miguelolivares
 */
@Stateless
public class MultimediaLogic implements IMultimediaLogic {
    
    @Inject
    private MultimediaPersistence persistence;
    
    
    
    public List<MultimediaDTO> getImagesByVehicle(String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        List<MultimediaEntity> list = persistence.executeListNamedQuery("MultimediaEntity.getImages", map);
        return MultimediaConverter.listEntity2DTO(list);
    }

    
}
