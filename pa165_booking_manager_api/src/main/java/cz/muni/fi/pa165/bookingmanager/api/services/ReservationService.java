package cz.muni.fi.pa165.bookingmanager.api.services;


import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;

import java.util.List;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
public interface ReservationService {

    /**
     * Creates reservation.
     *
     * @param reservation
     */
    public void create(ReservationTO reservation);

    /**
     * Update reservation.
     *
     * @param reservation
     */
    public void update(ReservationTO reservation);

    /**
     * Delete reservation.
     *
     * @param reservation
     */
    public void delete(ReservationTO reservation);

    /**
     * Find reservation by id.
     *
     * @param id
     * @return
     */
    public ReservationTO find(Long id);

    /**
     * List all reservations.
     *
     * @return
     */
    public List<ReservationTO> findAll();

    /**
     * List all reservation by room/
     *
     * @param room
     * @return
     */
    public List<ReservationTO> findAll(RoomTO room);

    /**
     * List all reservations by user.
     *
     * @param user
     * @return
     */
    public List<ReservationTO> findAll(UserTO user);
}