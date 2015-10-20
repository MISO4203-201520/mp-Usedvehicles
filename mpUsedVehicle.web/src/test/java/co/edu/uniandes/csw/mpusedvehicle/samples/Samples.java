/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.samples;

import co.edu.uniandes.csw.mpusedvehicle.dtos.UserDTO;
import co.edu.uniandes.csw.mpusedvehicle.entities.ClientEntity;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Util class for the most common methods
 * @author dham
 */
public class Samples {
    
    public static final String URLRESOURCES = "src/main/webapp";
    public static final String URLBASE = "http://localhost:8181/mpUsedVehicle.web/webresources";
    public static final String PATH_REGISTER = "/users/register";
    public static final String PATH_LOGIN = "/users/login";
    public static final String PATH_ORDERS = "/orders";
    public static final String PATH_CART_ITEMS = "/cartItems";
    public static final int Ok = 200;
    public static final int Created = 201;
    public static final int OkWithoutContent = 204;
    
    
    public static Integer register() {
        Client cliente = ClientBuilder.newClient();
        UserDTO user = Samples.createSampleUser();
        Response response = cliente.target(URLBASE).path(PATH_REGISTER).request().
                post(Entity.entity(user, MediaType.APPLICATION_JSON));       
        
        return response.getStatus();
    }
    
    /**
     * Login Cookie
     * @param username
     * @param password
     * @return 
     */
    public static Cookie login(String username, String password) {
        Client cliente = ClientBuilder.newClient();
        UserDTO user = new UserDTO();
        user.setUserName(username);
        user.setPassword(password);
        Response response = cliente.target(URLBASE).path("/users/login").request().
                post(Entity.entity(user, MediaType.APPLICATION_JSON));       
        UserDTO foundUser = (UserDTO) response.readEntity(UserDTO.class);
        
        if (foundUser != null && response.getStatus() == Ok) {
            return response.getCookies().get("JSESSIONID");
        } else {
            return null;
        }
    }
    
    public static UserDTO createSampleUser(){
        UserDTO client = new UserDTO();
        client.setName("test");
        client.setLastName("test");
        client.setPassword("Pepitoperez123");
        client.setEmail("die-agud@uniandes.edu.co");
        client.setRole("user");
        client.setUserName("test");
//        client.setLastName("https://api.stormpath.com/v1/accounts/24twbgpeBCjuufjZEDPlNr");
        return client;
    }
    
    
}
