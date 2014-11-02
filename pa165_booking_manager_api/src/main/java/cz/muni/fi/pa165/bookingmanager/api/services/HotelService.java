/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.api.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import java.util.List;

/**
 *
 * @author mstana
 */
public interface HotelService {
    /**
     * Creates hotel.
     *
     * @param hotel
     */
    public void create(HotelTO hotel);
    
    /**
     * Update hotel.
     *
     * @param hotel
     */
    public void update(HotelTO hotel);
    
    /**
     * Delete hotel.
     *
     * @param hotel
     */
    public void delete(HotelTO hotel);
    
    /**
     * Find hotel by id.
     *
     * @param id
     * @return 
     */
    public HotelTO find(Long id);
    
    /**
     * Find all hotels.
     *
     * @return
     */
    public List<HotelTO> findAll();
}
