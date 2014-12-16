package cz.muni.fi.pa165.bookingmanager;

import org.springframework.dao.DataAccessException;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
public class BookingDataAccessException extends DataAccessException {
    public BookingDataAccessException(String msg) {
        super(msg);
    }

    public BookingDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
