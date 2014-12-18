package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Kadlec
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomDAO roomDAO;

    private final Mapper mapper;

    @Autowired
    public RoomServiceImpl(RoomDAO roomDAO, Mapper mapper) {
        this.roomDAO = roomDAO;
        this.mapper = mapper;
    }

    public RoomDAO getRoomDAO() {
        return roomDAO;
    }

    @Override
    public void create(RoomTO room) {
        if (room == null) {
            throw new IllegalArgumentException("The room cannot be null.");
        }
        if (room.getId() != null) {
            throw new IllegalArgumentException("The room cannot have an id defined (current id is: " + room.getId() + ").");
        }

        Room roomDO = mapper.map(room, Room.class);
        roomDAO.create(roomDO);
        room.setId(roomDO.getId());
    }

    @Override
    public void update(RoomTO room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        if (room.getId() == null || roomDAO.find(room.getId()) == null) {
            throw new IllegalArgumentException("Room not exist");
        }

        Room roomDO = mapper.map(room, Room.class);
        roomDAO.update(roomDO);
    }

    @Override
    public void delete(RoomTO room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }

        Room roomDO = mapper.map(room, Room.class);
        roomDAO.delete(roomDO);
    }

    @Override
    public RoomTO find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Room roomDO = roomDAO.find(id);
        if (roomDO == null) {
            return null;
        } else {
            return mapper.map(roomDO, RoomTO.class);
        }
    }

    @Override
    public List<RoomTO> findAll() {
        List<Room> roomsFromDB = roomDAO.findAll();
        List<RoomTO> roomTransferObjects = new ArrayList<>();
        for (Room roomDO : roomsFromDB) {
            roomTransferObjects.add(mapper.map(roomDO, RoomTO.class));
        }

        return roomTransferObjects;
    }

    @Override
    public List<RoomTO> findAll(HotelTO hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("Hotel cannot be null");
        }

        Hotel hotelDO = mapper.map(hotel, Hotel.class);
        List<Room> roomsFromDB = roomDAO.findAll(hotelDO);
        List<RoomTO> roomTransferObjects = new ArrayList<>();
        for (Room roomDO : roomsFromDB) {
            roomTransferObjects.add(mapper.map(roomDO, RoomTO.class));
        }

        return roomTransferObjects;
    }
}
