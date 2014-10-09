package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import cz.muni.fi.pa165.bookingmanager.entities.User;

import java.util.List;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
public interface ReservationDAO {
    /**
     * Create entry for reservation
     *
     * @param reservation to create
     */
    public void create(Reservation reservation);

    /**
     * Update reservation entry
     *
     * @param reservation to update
     */
    public void update(Reservation reservation);

    /**
     * Remove reservation entry
     *
     * @param reservation to remove
     */
    public void delete(Reservation reservation);

    /**
     * Find reservation by given {@param id}, or null
     *
     * @param id reservation id in database
     * @return
     */
    public Reservation find(Long id);

    /**
     * Returns list of all reservations
     *
     * @return
     */
    public List<Reservation> findAll();

    /**
     * Returns list of all reservations for given room
     *
     * @param room to find all reservations for
     * @return {@link List} of {@link Reservation} for given {@param room}
     */
    public List<Reservation> findAll(Room room);

    /**
     * Returns list of all reservations for given user
     *
     * @param user to find all reservations for
     * @return {@link List} of {@link Reservation} for given {@param user}
     */
    public List<Reservation> findAll(User user);
}