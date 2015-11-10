/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.persistence;

import co.edu.uniandes.csw.mpusedvehicle.entities.MultimediaEntity;
import javax.ejb.Stateless;

/**
 *
 * @author miguelolivares
 */
@Stateless
public class MultimediaPersistence extends CrudPersistence<MultimediaEntity>  {
    
    public MultimediaPersistence() {
        this.entityClass = MultimediaEntity.class;
    }
    
}
