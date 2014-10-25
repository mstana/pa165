package cz.muni.fi.pa165.bookingmanager.services;


import cz.muni.fi.pa165.bookingmanager.BookingDataAccessException;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@Service
@Transactional
public class ReservationService {

    private ReservationDAO reservationDAO;

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }

    public void create(Reservation reservation) {
        try{
            //TODO:mapping na TO
            //Reservation transferalReservation = ReservationTOMapping.toEntity(reservation);
            reservationDAO.create(reservation);
        } catch(Exception ex) {
            throw new BookingDataAccessException(ex.getMessage());
        }
    }

}