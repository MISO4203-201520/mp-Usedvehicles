/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.ejbs;

import co.edu.uniandes.csw.mpusedvehicle.api.IPaymentMethodLogic;
import co.edu.uniandes.csw.mpusedvehicle.converters.PaymentMethodConverter;
import co.edu.uniandes.csw.mpusedvehicle.dtos.PaymentMethodDTO;
import co.edu.uniandes.csw.mpusedvehicle.persistence.PaymentMethodPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author dham
 */
@Stateless
public class PaymentMethodLogic implements IPaymentMethodLogic {

    @Inject
    private PaymentMethodPersistence persistence;

    /**
     *
     * @return
     */
    @Override
    public List<PaymentMethodDTO> finAll() {
        return PaymentMethodConverter.listEntity2DTO(persistence.findAll(null, null));
    }

}
