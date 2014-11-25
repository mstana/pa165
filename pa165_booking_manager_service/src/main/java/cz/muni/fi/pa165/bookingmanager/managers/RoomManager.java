package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.*;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author David Kadlec
 */
@Repository
@Transactional
public class RoomManager implements RoomDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public void create(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("The room cannot be null.");
        }
        if (room.getId() != null) {
            throw new IllegalArgumentException("The room cannot have an id defined (current id is: " + room.getId() + ").");
        } 
        if (!validate(room)) {
            throw new IllegalArgumentException("The hotel and number must be set.");
        }
        entityManager.persist(room);
    }

    @Override
    public void update(Room room) {
       if (room == null) {
           throw new IllegalArgumentException("The room cannot be null.");
       }
       
       entityManager.merge(room);
    }

    @Override
    public void delete(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("The room cannot be null.");
        }
        
        Room mergedRoom = entityManager.merge(room);
        entityManager.remove(mergedRoom);
    }

    @Override
    public Room find(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("An id must be positive number.");
        }
        
        return entityManager.find(Room.class, id);
    }

    @Override
    public List<Room> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
        Root<Room> room = query.from(Room.class);
        query.select(room);

        TypedQuery<Room> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }

    @Override
    public List<Room> findAll(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("The hotel cannot be null.");
        }
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
       
        CriteriaQuery<Room> query = criteriaBuilder.createQuery(Room.class);
        Root<Room> room = query.from(Room.class);
        
        Predicate whereCondition = criteriaBuilder.equal(room.get("hotel").get("id").as(Long.class), hotel.getId());
        
        query.select(room).where(whereCondition);

        TypedQuery<Room> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }


    private boolean validate(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("The room cannot be null.");
        }
        
        return ((room.getHotel() != null) && (room.getNumber() != null) && (!room.getNumber().equals("")));
    }
}
