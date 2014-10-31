package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Administrator;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.Calendar;
import java.util.List;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author David Kadlec
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
public class ReservationServiceImplTest extends TestCase {

}