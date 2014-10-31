package cz.muni.fi.pa165.bookingmanager.services;


import cz.muni.fi.pa165.bookingmanager.api.BookingDataAccessException;
import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.services.ReservationService;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }

    public void create(ReservationTO reservation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //TODO:mapping na TO
        //Reservation transferalReservation = ReservationTOMapping.toEntity(reservation);
        //reservationDAO.create(reservation);
    }
}

