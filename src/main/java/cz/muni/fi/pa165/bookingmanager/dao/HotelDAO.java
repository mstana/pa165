package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import java.util.List;

/**
 *
 * @author Adam Studenic
 */
public interface HotelDAO {

    public void create(Hotel hotel);

    public void update(Hotel hotel);

    public void delete(Hotel hotel);

    public Hotel find(Long id);

    public Hotel find(String name);

    public List<Hotel> findAll(String name);

}
