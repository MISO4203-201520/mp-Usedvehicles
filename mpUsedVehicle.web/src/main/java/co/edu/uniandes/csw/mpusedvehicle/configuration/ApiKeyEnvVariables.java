/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mpusedvehicle.configuration;

import java.util.Properties;
import org.springframework.util.StringUtils;

/**
 *
 * @author dham
 */
public class ApiKeyEnvVariables extends Properties {

    public static final String STORMPATH_API_KEY_ID = "2N3X9U3OYL6HYMUOROUT3983F";
    public static final String STORMPATH_API_KEY_SECRET = "lZoFt+ZmsX3H5jl1hzI1rjC884TENkg5BKsxFx/GAWs";
    
    public ApiKeyEnvVariables() {
        String apiId = System.getenv("STORMPATH_API_KEY_ID");
        String apiKey = System.getenv("STORMPATH_API_KEY_SECRET");
        super.put("apiKey.id", (StringUtils.isEmpty(apiId))?STORMPATH_API_KEY_ID:apiId);
        super.put("apiKey.secret", (StringUtils.isEmpty(apiKey))?STORMPATH_API_KEY_SECRET:apiId);
    }
}
