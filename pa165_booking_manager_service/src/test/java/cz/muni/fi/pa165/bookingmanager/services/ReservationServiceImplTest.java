package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.services.ReservationService;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
import junit.framework.TestCase;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

/**
 * @author David Kadlec
 */
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
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
        MockitoAnnotations.initMocks(this);
        reservationService = new ReservationServiceImpl(reservationDAO, mapper);
    }

    @After
    public void tearDown() {
        reservationDAO = null;
        mapper = null;
        reservationService = null;
    }

    @org.junit.Test
    public void testCreateEmptyReservation() {
        ReservationTO reservation = new ReservationTO();

        reservationService.create(reservation);
        Mockito.verify(reservationDAO).create(mapper.map(reservation, Reservation.class));
    }

    @org.junit.Test
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

    @org.junit.Test
    public void testDeleteReservation() {
        ReservationTO reservation = new ReservationTO();

        reservationService.create(reservation);
        Mockito.verify(reservationDAO).create(mapper.map(reservation, Reservation.class));

        reservationService.delete(reservation);
        Mockito.verify(reservationDAO).delete(mapper.map(reservation, Reservation.class));
    }

    @org.junit.Test
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