package cz.muni.fi.pa165.bookingmanager.api.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import java.util.List;

/**
 *
 * @author David Kadlec
 */
public interface RoomService {
    public void create(RoomTO room);

    public void update(RoomTO room);

    public void delete(RoomTO room);

    public RoomTO find(Long id);    
    
    public List<RoomTO> findAll();    
    
    // TODO HotelTO
    //public List<RoomTO> findAll(HotelTO hotel);
}
