package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entities.Room;
import java.util.List;

/**
 *
 * @author David Kadlec
*/
public interface RoomDAO {
        
    public void create(Room room);
    
    public void update(Room room);
    
    public void delete(Room room);
    
    public Room find(Long id);
    
    public List<Room> findAll();
}
