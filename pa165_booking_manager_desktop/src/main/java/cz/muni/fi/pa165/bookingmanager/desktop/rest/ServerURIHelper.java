/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.desktop.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mstana
 */

public class ServerURIHelper {
    
    public static String loadURLForUser() {
        Properties properties = PropertiesInit();
        return properties.getProperty("urlUser");
    }
    
    public static String loadURLForHotel() {
        Properties properties = PropertiesInit();
        return properties.getProperty("urlHotel");
    }
    
    private static Properties PropertiesInit() {
        Properties properties = new Properties();
        try {
            try (InputStream in = ServerURIHelper.class.getResourceAsStream("/serverConfig.properties")) {
                properties.load(in);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerURIHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }
}
