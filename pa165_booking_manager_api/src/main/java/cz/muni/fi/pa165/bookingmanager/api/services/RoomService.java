package cz.muni.fi.pa165.bookingmanager.api.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;

import java.util.List;

/**
 * @author David Kadlec
 */
public interface RoomService {
    /**
     * Creates room.
     *
     * @param room
     */
    public void create(RoomTO room);

    /**
     * Updates room.
     *
     * @param room
     */
    public void update(RoomTO room);

    /**
     * Deletes room.
     *
     * @param room
     */
    public void delete(RoomTO room);

    /**
     * Finds room by id.
     *
     * @param id
     */
    public RoomTO find(Long id);

    /**
     * Returns all rooms.
     */
    public List<RoomTO> findAll();

    /**
     * Returns all rooms in the specified hotel.
     *
     * @param hotel
     */
    public List<RoomTO> findAll(HotelTO hotel);
}
