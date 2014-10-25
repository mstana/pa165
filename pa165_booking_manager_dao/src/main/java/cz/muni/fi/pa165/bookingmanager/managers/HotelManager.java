package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adam Studenic
 */
@Repository
@Transactional
public class HotelManager implements HotelDAO {

    @Autowired
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Hotel hotel) {
        if (hotel.getId() != null) {
            throw new IllegalArgumentException("The hotel is already created, should have null id (has " + hotel.getId() + ")");
        }
        if(! validate(hotel)) {
            throw new IllegalArgumentException("The hotel name is not valid");
        }
        entityManager.persist(hotel);
    }

    @Override
    public void update(Hotel hotel) {
        if (hotel.getId() == null) {
            throw new IllegalArgumentException("The hotel to be update cannot have null id");
        }
        if(! validate(hotel)) {
            throw new IllegalArgumentException("The hotel name is not valid");
        }
        entityManager.merge(hotel);
    }

    @Override
    public void delete(Hotel hotel) {
        if (hotel.getId() == null) {
            throw new IllegalArgumentException("The hotel to be removed cannot have null id");
        }
        Hotel h = entityManager.merge(hotel);
        entityManager.remove(h);
    }

    @Override
    public Hotel find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The hotel to be found cannot have null id");
        }
        return entityManager.find(Hotel.class, id);
    }

    @Override
    public Hotel find(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The hotel to be found cannot have null name");
        }
        return entityManager.find(Hotel.class, name);
    }

    @Override
    public List<Hotel> findAll() {
        return entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
    }

    private boolean validate(Hotel hotel) {
        if (hotel.getName() == null
                || "".equals(hotel.getName())) {
            return false;
        }
        return true;
    }
}
