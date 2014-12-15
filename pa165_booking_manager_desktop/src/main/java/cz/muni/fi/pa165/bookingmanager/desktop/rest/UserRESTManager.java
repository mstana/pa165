/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.desktop.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
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
 * @author Adam Studenic
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
    }


    public UserTO findUser(Long id) {
        UserTO user  = webResource.path("users/"+ id)
                .accept(MediaType.APPLICATION_JSON)
                .get(UserTO.class);
        return user;
    }

    public List<UserTO> findAllUsers() {
        List<UserTO> users = new ArrayList<>();
        String json = webResource.path("users/")
                        .accept(MediaType.APPLICATION_JSON)
                        .get(String.class);
        try {
            System.out.println("Hello! " + json);
            users = mapper.readValue(json, new TypeReference<List<UserTO>>() {});
        } catch (IOException e) {
            Logger.getLogger(UserRESTManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }


    public ClientResponse createUser(UserTO user) {
        return webResource.path("users/")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, user);
    }


    public ClientResponse updateUser(UserTO user) {
        return webResource.path("users/")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, user);
    }

    public ClientResponse deleteUser(UserTO user) {
        return webResource.path("users/" + user.getId())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);
    }
}
