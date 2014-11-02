package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.ReservationService;
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
import org.dozer.Mapper;
import org.junit.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
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
    
    @Mock
    private ReservationDAO reservationDAO;

    @Autowired
    private Mapper mapper;

    private ReservationService reservationService;

    public ReservationServiceImplTest() {
    }

    @Before
    public void setUp() {
        reservationDAO = Mockito.mock(ReservationDAO.class);
        reservationService = new ReservationServiceImpl();
        
        ReflectionTestUtils.setField(reservationService, "reservationDAO", reservationDAO);
        ReflectionTestUtils.setField(reservationService, "mapper", mapper);
    }

    @After
    public void tearDown() {
        reservationDAO = null;
        mapper = null;
        reservationService = null;
    }

    @Test
    public void testCreateEmptyReservation() {
        ReservationTO reservation = new ReservationTO();
        
        reservationService.create(reservation);
        Mockito.verify(reservationDAO).create(mapper.map(reservation, Reservation.class));
    }
    
    @Test
    public void testUpdateReservation() {
        ReservationTO reservation = new ReservationTO();
        
        reservationService.create(reservation);
        Mockito.verify(reservationDAO).create(mapper.map(reservation, Reservation.class));
        
        reservation.setId(1L);
        Mockito.when(reservationDAO.find(1L)).thenReturn(mapper.map(reservation, Reservation.class));
        
        Calendar beginDate = Calendar.getInstance();
        beginDate.set(2014, 10, 1);
        
        reservation.setBeginDate(beginDate.getTime());
        
        reservationService.update(reservation);
        Mockito.verify(reservationDAO).update(mapper.map(reservation, Reservation.class));
    }
    
    @Test
    public void testDeleteReservation() {
        ReservationTO reservation = new ReservationTO();
        
        reservationService.create(reservation);
        Mockito.verify(reservationDAO).create(mapper.map(reservation, Reservation.class));
        
        reservationService.delete(reservation);
        Mockito.verify(reservationDAO).delete(mapper.map(reservation, Reservation.class));
    }
    
    @Test
    public void testFindReservation() {
        ReservationTO reservation = new ReservationTO();
        
        reservationService.create(reservation);
        Mockito.verify(reservationDAO).create(mapper.map(reservation, Reservation.class));
        
        reservation.setId(1L);
        
        Mockito.when(reservationDAO.find(1L)).thenReturn(mapper.map(reservation, Reservation.class));
        
        ReservationTO found = reservationService.find(1L);
        
        Assert.assertEquals(reservation, found);
    }
}