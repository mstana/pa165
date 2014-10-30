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
    
    public void create(HotelTO user);

    public void update(HotelTO user);

    public void delete(HotelTO user);

    public HotelTO find(Long id);

    public List<HotelTO> findAll();
}
