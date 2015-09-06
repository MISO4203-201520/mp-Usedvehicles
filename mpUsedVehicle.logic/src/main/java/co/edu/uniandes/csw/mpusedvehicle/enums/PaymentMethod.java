/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.enums;

/**
 * Enumeraci�n que representa un m�todo de pago
 * 
 * @author die-agud@uniandes.edu.co
 */
public enum PaymentMethod {
    
    /** M�todo de pago para tarjeta D�bito**/
    DEBIT_CARD("Tarjeta D�bito"),
    /** M�todo de pago de tipo tarjeta de Cr�dito**/
    CREDIT_CARD("Tarjeta de Cr�dito");
    
    /** Nombre legible del m�todo de pago**/
    private final String name;
    
    /**
     * Constructor
     * @param name - El nombre del metodo de pago
     */
    private PaymentMethod(String name){
        this.name = name;
    }

    /** Metodo para retornar el nombre del metodo de pago **/
    public String getName() {
        return name;
    }
    
}
