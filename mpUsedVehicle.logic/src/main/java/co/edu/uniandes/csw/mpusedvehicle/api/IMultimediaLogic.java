/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.MultimediaDTO;
import java.util.List;

/**
 *
 * @author miguelolivares
 */
public interface IMultimediaLogic {
    public List<MultimediaDTO> getImagesByVehicle(String name);
}
