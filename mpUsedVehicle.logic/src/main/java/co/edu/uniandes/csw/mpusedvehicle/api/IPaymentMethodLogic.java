/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.api;

import co.edu.uniandes.csw.mpusedvehicle.dtos.PaymentMethodDTO;
import java.util.List;

/**
 *
 * @author dham
 */
public interface IPaymentMethodLogic {
    
    public List<PaymentMethodDTO> finAll();
}
