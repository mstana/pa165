package cz.muni.fi.pa165.bookingmanager.services;


import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.ReservationService;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationDAO reservationDAO;

    private final Mapper mapper;

    @Autowired
    public ReservationServiceImpl(ReservationDAO reservationDAO, Mapper mapper) {
        this.reservationDAO = reservationDAO;
        this.mapper = mapper;
    }

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }

    @Override
    public void create(ReservationTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }
        if (reservation.getId() != null) {
            throw new IllegalArgumentException("Reservation cannot have id already set");
        }
        Reservation reservationDO = mapper.map(reservation, Reservation.class);

        reservationDAO.create(reservationDO);
        reservation.setId(reservationDO.getId());
    }

    @Override
    public void update(ReservationTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (reservation.getId() == null || reservationDAO.find(reservation.getId()) == null) {
            throw new IllegalArgumentException("Uset does not exist");
        }
        Reservation reservationDO = mapper.map(reservation, Reservation.class);

        reservationDAO.update(reservationDO);
    }

    @Override
    public void delete(ReservationTO reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        Reservation userDO = mapper.map(reservation, Reservation.class);

        reservationDAO.delete(userDO);
    }

    @Override
    public ReservationTO find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Reservation reservationDO = reservationDAO.find(id);
        if (reservationDO == null) {
            return null;
        } else {
            return mapper.map(reservationDO, ReservationTO.class);
        }
    }

    @Override
    public List<ReservationTO> findAll() {
        List<Reservation> reservations = reservationDAO.findAll();
        List<ReservationTO> reservationTOs = new ArrayList<>();
        for (Reservation reservationDO : reservations) {
            reservationTOs.add(mapper.map(reservationDO, ReservationTO.class));
        }

        return reservationTOs;
    }

    @Override
    public List<ReservationTO> findAll(RoomTO room) {
        List<Reservation> reservations = reservationDAO.findAll(mapper.map(room, Room.class));
        List<ReservationTO> reservationTOs = new ArrayList<>();
        for (Reservation reservationDO : reservations) {
            reservationTOs.add(mapper.map(reservationDO, ReservationTO.class));
        }

        return reservationTOs;
    }

    @Override
    public List<ReservationTO> findAll(UserTO user) {
        List<Reservation> reservations = reservationDAO.findAll(mapper.map(user, User.class));
        List<ReservationTO> reservationTOs = new ArrayList<>();
        for (Reservation reservationDO : reservations) {
            reservationTOs.add(mapper.map(reservationDO, ReservationTO.class));
        }

        return reservationTOs;
    }
}

