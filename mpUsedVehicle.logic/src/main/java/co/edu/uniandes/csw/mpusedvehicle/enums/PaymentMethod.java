/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.enums;

/**
 * Enumeración que representa un método de pago
 * 
 * @author die-agud@uniandes.edu.co
 */
public enum PaymentMethod {
    
    /** Método de pago para tarjeta Débito**/
    DEBIT_CARD("Tarjeta Débito"),
    /** Método de pago de tipo tarjeta de Crédito**/
    CREDIT_CARD("Tarjeta de Crédito");
    
    /** Nombre legible del método de pago**/
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
