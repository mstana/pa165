package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
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
public class ReservationManagerTest extends TestCase {

    @Autowired
    ReservationDAO reservationDAO;
    
    @Autowired
    RoomDAO roomDAO;
        
    @Autowired
    UserDAO userDAO;
    
    @Autowired
    HotelDAO hotelDAO;
        
    @Test
    @Transactional
    public void testCreate() {      
        Reservation reservation = createReservationInMemory();
        
        assertNull("New reservation should have null id", reservation.getId());

        reservationDAO.create(reservation);

        assertNotNull("The reservation id is not assigned", reservation.getId());

        Reservation reservationFromDAO = reservationDAO.find(reservation.getId());

        assertNotNull("Failed dto retreive reservation from DB", reservationFromDAO);
        assertEquals("The retreived reservation does not equal the original one", reservation, reservationFromDAO);
    }

    @Test
    @Transactional
    public void testUpdate() {
        Reservation reservation = createReservationInMemory();
        reservationDAO.create(reservation);
        assertNotNull("The reservation id is not assigned",reservation.getId());

        Calendar updatedBeginDate = Calendar.getInstance();
        updatedBeginDate.set(2014, 10, 2);
        
        Calendar updatedEndDate = Calendar.getInstance();
        updatedEndDate.set(2014, 10, 2);
        
        reservation.setBeginDate(updatedBeginDate.getTime());
        reservation.setEndDate(updatedEndDate.getTime());

        reservationDAO.update(reservation);

        Reservation reservationFromDAO = reservationDAO.find(reservation.getId());
        
        assertEquals("Begin date has not changed", updatedBeginDate.getTime(), reservationFromDAO.getBeginDate());
        assertEquals("End date has not changed", updatedEndDate.getTime(), reservationFromDAO.getEndDate());
    }
    @Test
    @Transactional
    public void testDelete() {        
        Reservation reservation = createReservationInMemory();

        reservationDAO.create(reservation);
        assertNotNull("The reservation id is not assigned",reservation.getId());

        reservationDAO.delete(reservation);
        assertNull("Reservation was not deleted.", reservationDAO.find(reservation.getId()));
    }

    @Test
    @Transactional
    public void testFind() {
        Reservation reservation = createReservationInMemory();

        reservationDAO.create(reservation);
        assertNotNull("The reservation id is not assigned", reservation.getId());

        Reservation foundReservation = reservationDAO.find(reservation.getId());
        assertEquals("Found reservation is not same as the created one.", reservation, foundReservation);
    }

    @Test
    @Transactional
    public void testFindAll() {
        Reservation reservation = createReservationInMemory();
        reservationDAO.create(reservation);
        
        List<Reservation> reservations = reservationDAO.findAll();

        assertTrue("Reservations do not contain created one", reservations.contains(reservation));
        assertEquals("The db does not contain one reservation using the findAll() method", 1, reservations.size());

        Room room = reservation.getRoom();
        reservations = reservationDAO.findAll(room);

        assertTrue(reservations.contains(reservation));
        assertEquals("The db does not contain one reservation using the findAll(Room) method", 1, reservations.size());

        reservations = reservationDAO.findAll(reservation.getUser());

        assertTrue(reservations.contains(reservation));
        assertEquals("The db does not contain one reservation using the findAll(User) method", 1, reservations.size());
    }

    
    private Reservation createReservationInMemory() {
        User administrator = new User();
        administrator.setAdmin(true);
        userDAO.create(administrator);
        
        Hotel hotel = new Hotel();
        hotel.setName("One");
        hotel.setAddress("Street");
        hotelDAO.create(hotel);
          
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber("A01");
        room.setBedsCount(2);
        room.setPrice(50);
        roomDAO.create(room);
        
        Calendar beginDate = Calendar.getInstance();
        beginDate.set(2014, 10, 1);
        
        Calendar endDate = Calendar.getInstance();
        endDate.set(2014, 10, 10);
        
        Reservation reservation = new Reservation(room, beginDate.getTime(), endDate.getTime());
        reservation.setUser(administrator);
        
        return reservation;
    }
}