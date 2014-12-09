package cz.muni.fi.pa165.bookingmanager.desktop.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Adams
 */
public class HotelRESTManager {

    private String url;
    private Client client;
    private ObjectMapper mapper = new ObjectMapper();
    private WebResource webResource;

    public HotelRESTManager() {
        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        url = ServerURIHelper.loadURLForHotel();
        client = Client.create(clientConfig);
        webResource = client.resource(url);
        webResource.addFilter(new HTTPBasicAuthFilter("rest", "rest"));
    }

    public HotelTO findHotel(Long id) {
        return webResource.path("hotel")
                .queryParam("id", id.toString())
                .accept(MediaType.APPLICATION_JSON)
                .get(HotelTO.class);
    }

    public List<HotelTO> findAllHotels() {
        List<HotelTO> hotels = new ArrayList<>();
        String json = webResource.path("hotels")
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);
        try {
            hotels = mapper.readValue(json, new TypeReference<List<HotelTO>>() {
            });
        } catch (IOException e) {
            Logger.getLogger(HotelRESTManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return hotels;
    }

    public List<HotelTO> findHotelsByName(String name) {
        List<HotelTO> hotels = new ArrayList<>();
        String json = webResource.path("hotel")
                .queryParam("name", name)
                .accept(MediaType.APPLICATION_JSON)
                .get(String.class);
        try {
            hotels = mapper.readValue(json, new TypeReference<List<HotelTO>>() {
            });
        } catch (IOException e) {
            Logger.getLogger(HotelRESTManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return hotels;
    }

    public ClientResponse createHotel(HotelTO hotel) {
        return webResource.path("hotel")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(ClientResponse.class, hotel);
    }

    public ClientResponse updateHotel(HotelTO hotel) {
        return webResource.path("hotel")
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, hotel);
    }

    public ClientResponse deleteHotel(HotelTO hotel) {
        return webResource.path("hotel")
                .queryParam("id", hotel.getId().toString())
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);
    }

}
