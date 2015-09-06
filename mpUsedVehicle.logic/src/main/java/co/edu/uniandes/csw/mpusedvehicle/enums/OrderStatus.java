/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.enums;

/**
 * Clase que representa los estados posibles de una orden
 * 
 * @author die-agud@uniandes.edu.co
 */
public enum OrderStatus {
    
    /** Estado para una orden nueva **/
    NEW("Nueva"),
    /** 
     * Estado para cuando se presenta el formulario en donde 
     * se captura la información de los pagos.  
     */
    CAPTURING("Capturando datos"),
    /**
     * Estado para cuando ocurre un error al procesar los datos de una 
     * Orden, pago etc.
     **/
    ERROR("Error"),
    /** Estado final utilizado cuando el pago fue exitoso **/
    AUTHORIZED("Autorizada");
    
    /**
     * Nombre legible del estado de la orden
     */
    private String name;
    
    /**
     * Constructor
     * @param name - El nombre del estado de la orden 
     */
    private OrderStatus(String name){
        this.name = name;
    }
    
    /**
     * Nombre del estado de la orden
     * @return name - El nombre del estado de la orden
     */
    public String getName(){
        return this.name;
    }
    
}
