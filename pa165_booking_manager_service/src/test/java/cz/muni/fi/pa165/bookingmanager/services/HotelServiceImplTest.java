package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import junit.framework.TestCase;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adam Studenic
 */
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HotelServiceImplTest extends TestCase {

    @Mock
    private HotelDAO hotelDAO;

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private Mapper mapper;

    private HotelService hotelService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        hotelService = new HotelServiceImpl(hotelDAO, mapper);
    }

    @After
    public void tearDown() {
        hotelService = null;
        mapper = null;
        hotelDAO = null;
    }

    @Test
    public void testCreateHotelWithNoRooms() {
        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");

        hotelService.create(hotelTO);
        Mockito.verify(hotelDAO).create(mapper.map(hotelTO, Hotel.class));
    }

    @Test
    public void testCreateHotelWithRooms() {

        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");

        RoomTO room1 = new RoomTO();
        room1.setNumber("123");
        room1.setPrice(100);
        room1.setBedsCount(2);
//        room1.setHotel(hotelTO);

        RoomTO room2 = new RoomTO();
        room2.setNumber("12");
        room2.setPrice(56);
        room2.setBedsCount(1);
//        room2.setHotel(hotelTO);

        if (hotelTO.getRooms() == null) {
            List<RoomTO> rooms = new ArrayList<>();
            rooms.add(room1);
            rooms.add(room2);
            hotelTO.setRooms(rooms);
        }

        hotelService.create(hotelTO);
        Mockito.verify(hotelDAO).create(mapper.map(hotelTO, Hotel.class));
    }

    @Test
    public void testFindHotel() {
        try {
            hotelService.find(null);
            fail("No IllegalArgumentException for null id");
        } catch (IllegalArgumentException e) {
            //OK
        }

        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");
        hotelService.create(hotelTO);
        hotelTO.setId(1L);

        Mockito.when(hotelDAO.find(hotelTO.getId())).thenReturn(mapper.map(hotelTO, Hotel.class));

        hotelTO = hotelService.find(1L);
        Mockito.verify(hotelDAO).find(hotelTO.getId());

    }

    @Test
    public void testUpdateHotel() {

        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");
        hotelTO.setId(1L);

        RoomTO room1 = new RoomTO();
        room1.setNumber("123");
        room1.setPrice(100);
        room1.setBedsCount(2);

        if (hotelTO.getRooms() == null) {
            List<RoomTO> rooms = new ArrayList<>();
            rooms.add(room1);
            hotelTO.setRooms(rooms);
        }

        //Room is null
        RoomTO room2 = null;
        hotelTO.getRooms().add(room2);
        try {
            hotelService.update(hotelTO);
            fail("No IllegalArgumentException for null room.");
        } catch (IllegalArgumentException e) {
            //OK
        }

        //Room has no ID
        HotelTO hotelTO2 = new HotelTO();
        hotelTO2.setAddress("Lidická 6, Brno");
        hotelTO2.setName("BobyCentrum");
        hotelTO2.setId(1L);


        RoomTO room3 = new RoomTO();
        room3.setNumber("123");
        room3.setPrice(100);
        room3.setBedsCount(2);

        hotelTO2.getRooms().add(room3);
        try {
            hotelService.update(hotelTO2);
            fail("Room must exist in a database.");
        } catch (IllegalArgumentException e) {
            //OK
        }

        try {
            // BedsCount negative
            RoomTO room4 = new RoomTO();
            room4.setNumber("123");
            room4.setPrice(100);
            room4.setBedsCount(-10);

            hotelTO2.getRooms().add(room4);
            hotelService.update(hotelTO2);
            fail("Rooms BedsCount cannot be negative");
        } catch (IllegalArgumentException e) {
            //OK
        }
        Mockito.verify(hotelDAO, Mockito.times(2)).find(1L);
    }

    @Test
    public void testDeleteHotel() {
        try {
            hotelService.delete(null);
            fail("NO IllegalArgumentException when deleting null HotelTO");
        } catch (Exception e) {
            //OK
        }

        Mockito.verifyZeroInteractions(hotelDAO);

        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");

        hotelService.create(hotelTO);
        hotelTO.setId(1L);

        Mockito.when(hotelDAO.find(hotelTO.getId())).thenReturn(mapper.map(hotelTO, Hotel.class));

        hotelService.delete(hotelTO);
        Mockito.verify(hotelDAO).delete(mapper.map(hotelTO, Hotel.class));

    }

    @Test
    public void testFindAllHotels() {
        List<HotelTO> hotels = hotelService.findAll();
        Mockito.verify(hotelDAO).findAll();
    }

}
