package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import java.util.List;

/**
 *
 * @author David Kadlec
 */
public class RoomServiceImpl implements RoomService {

    @Override
    public void create(RoomTO room) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void update(RoomTO room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(RoomTO room) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public RoomTO find(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RoomTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /*
    @Override
    public List<RoomTO> findAll(HotelTO hotel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    */
}
