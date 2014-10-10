package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.*;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author David Kadlec
 */
@Repository
@Transactional
public class RoomManager implements RoomDAO {

    @Autowired
    EntityManager em;

    @Override
    public void create(Room room) {
        if (room.getId() != null) throw new IllegalArgumentException("The room to be added must have null id (has"+room.getId()+")");
        if (! validate(room)) throw new IllegalArgumentException("The hotel and room number must be set");
        em.persist(room);
    }

    @Override
    public void update(Room room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Room room) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Room find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public List<Room> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    private boolean validate(Room room) {
        if (room.getHotel() == null ||
                room.getNumber() == null ||
                "".equals(room.getNumber())) return false;
        return true;
    }
}
