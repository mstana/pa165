package cz.muni.fi.pa165.bookingmanager.api.services;


import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
public interface ReservationService {
    public void create(ReservationTO reservation);

}