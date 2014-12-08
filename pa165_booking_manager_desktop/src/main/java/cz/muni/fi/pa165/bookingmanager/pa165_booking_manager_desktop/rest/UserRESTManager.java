/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.pa165_booking_manager_desktop.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author mstana
 */
public class UserRESTManager {
    
    private String url;
    private Client client;
    private ObjectMapper mapper = new ObjectMapper();
    private WebResource webResource;

    
    public UserRESTManager() {
        
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        url = ServerURIHelper.loadURLForUser();
        client = Client.create(clientConfig);
        webResource = client.resource(url);
        webResource.addFilter(new HTTPBasicAuthFilter("rest", "rest"));
    }
    
    
    /**
     * Returns user with given id.
     * Error status codes (wrapped in UniformInterfaceException) are:
     * 400 Bad request				Not applicable for now, cannot send null id to server, because IllegalArgumentException is thrown.
     * 404 Not found				If user with given id was not found.
     * 500 Internal server error	Other error.
     * 
     * @param id
     * @return user with given id.
     * @throws IllegalArgumentException if id is null.
     * @throws ClientHandlerException if signals a failure to process the HTTP request or HTTP response.
     * @throws UniformInterfaceException when the status code of the HTTP response indicates a response that is not expected.
     */
    public UserTO findUser(Long id) {
        return webResource.path("findUser")
                          .queryParam("id", id.toString())
                          .accept(MediaType.APPLICATION_JSON)
                          .get(UserTO.class);
    }
    
    /**
     * Returns all users.
     * Error status codes (wrapped in UniformInterfaceException) are:
     * 500 Internal server error	In case of any error on service layer.
     * 
     * @return all users.
     * @throws ClientHandlerException if signals a failure to process the HTTP request or HTTP response.
     * @throws UniformInterfaceException when the status code of the HTTP response indicates a response that is not expected.
     */
    public List<UserTO> findAllUsers() {
        List<UserTO> users = new ArrayList<>();
        String json = webResource.path("findAllUsers")
                                 .accept(MediaType.APPLICATION_JSON)
                                 .get(String.class);
        try {
            users = mapper.readValue(json, new TypeReference<List<UserTO>>() {});    
        } catch (IOException e) {
			Logger.getLogger(UserRESTManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }
    /**
     * Creates user.
     * Error status codes (wrapped in server response) are:
     * 400 Bad request				In case of invalid user.
     * 500 Internal server error	Other error.
     * 
     * @param user
     * @return response from server.
     * @throws ClientHandlerException if signals a failure to process the HTTP request or HTTP response.
     */
    public ClientResponse createUser(UserTO user) {
        return webResource.path("createUser")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, user);
    }
        
    /**
     * Updates user.
     * Error status codes (wrapped in server response) are:
     * 400 Bad request				In case of invalid user.
     * 500 Internal server error	Other error.
     * 
     * @param user
     * @return response from server.
     * @throws ClientHandlerException if signals a failure to process the HTTP request or HTTP response.
     */
    public ClientResponse updateUser(UserTO user) {
        return webResource.path("updateUser")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, user);
    }
    
    /**
     * Deletes user.
     * Error status codes (wrapped in server response) are:
     * 400 Bad request				If user is invalid.
     * 404 Not found				If user was not found.
     * 417 Expectation Failed		In case of DB error, mainly DB constraint.
     * 500 Internal server error	Other error.
     * 
     * @param user
     * @return response from server.
     * @throws IllegalArgumentException if user is null or has null id.
     * @throws ClientHandlerException if signals a failure to process the HTTP request or HTTP response.
     */
    public ClientResponse deleteUser(UserTO user) {
        return webResource.path("deleteUser")
                .queryParam("id", user.getId().toString())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);
    }
}
