/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import java.util.ArrayList;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;


public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private Mapper mapper;

    public HotelDAO getHotelDAO() {
        return hotelDAO;
    }
    @Override
    public void create(HotelTO hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
        if (hotel.getId() != null) {
            throw new IllegalArgumentException("Hotel cannot have id already set");
        }
        Hotel hotelDO = mapper.map(hotel, Hotel.class);

        hotelDAO.create(hotelDO);
        hotel.setId(hotelDO.getId());
    }

    @Override
    public void update(HotelTO hotel) {
            if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
        if (hotel.getId() == null || hotelDAO.find(hotel.getId()) == null) {
            throw new IllegalArgumentException("Hotel does not exist");
        }
        Hotel hotelDO = mapper.map(hotel, Hotel.class);

        hotelDAO.update(hotelDO);
    }

    @Override
    public void delete(HotelTO hotel) {
            if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }
        Hotel hotelDO = mapper.map(hotel, Hotel.class);

        hotelDAO.delete(hotelDO); 
    }

    @Override
    public HotelTO find(Long id) {
            if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Hotel hotelDO = hotelDAO.find(id);
        if (hotelDO == null) {
            return null;
        } else {
            return mapper.map(hotelDO, HotelTO.class);
        }
    }

    @Override
    public List<HotelTO> findAll() {
            List<Hotel> hotels = hotelDAO.findAll();
        List<HotelTO> hotelsTO = new ArrayList<>();
        for (Hotel hotelDO : hotels) {
            hotelsTO.add(mapper.map(hotelDO, HotelTO.class));
        }
        return hotelsTO;
    }
    
}
