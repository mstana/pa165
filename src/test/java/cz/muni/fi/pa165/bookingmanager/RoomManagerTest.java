package cz.muni.fi.pa165.bookingmanager;

import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 * @author Ondrej Pavelka <106752@mail.muni.cz>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
public class RoomManagerTest extends TestCase {

    @Autowired
    RoomDAO roomManager;

    public RoomManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    @Transactional
    public void testCreate() {
        Hotel hotel = new Hotel();
        /*hotel.setName("One");
        hotel.setAddress("Street");*/

        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber("A01");
        room.setBedsCount(2L);
        room.setPrice(50L);

        assertNull("New room should have null id", room.getId());

        roomManager.create(room);

        assertNotNull("The room id should be assigned by now", room.getId());

        Room r2 = roomManager.find(room.getId());

        assertNotNull("Failed to retreive room from DB", r2);
        assertEquals("The retreived room does not equal the original one", room, r2);
    }

    @Test
    @Transactional
    public void testUpdate() {
        Room room = new Room();
        assertNull(room.getId());

        Hotel hotel = new Hotel();
        hotel.setName("Hotel1");
        hotel.setAddress("Avenue1");

        room.setHotel(hotel);
        room.setNumber("100");
        room.setBedsCount(100L);
        room.setPrice(100L);

        roomManager.create(room);
        assertNotNull(room.getId());
        assertTrue(room.getReservations().isEmpty());

        room.setBedsCount(1);
        room.setPrice(1);

        roomManager.update(room);

        assertEquals(room.getBedsCount(), (Long) 1L);
        assertEquals(room.getPrice(), (Long) 1L);
        assertEquals(room.getNumber(), "100");
    }
    @Test
    @Transactional
    public void testDelete() {
        Room room = new Room();
        Hotel hotel = new Hotel();
        hotel.setName("Hotel1");
        hotel.setAddress("Avenue1");
        room.setHotel(hotel);
        room.setNumber("100");
        room.setBedsCount(100L);
        room.setPrice(100L);

        roomManager.create(room);
        assertNotNull(room.getId());
        roomManager.delete(room);
        assertNull(roomManager.find(room.getId()));
    }

    @Test
    @Transactional
    public void testFind() {

        Hotel hotel = new Hotel();
        hotel.setName("Hotel1");
        hotel.setAddress("Avenue1");
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber("100");
        room.setBedsCount(100L);
        room.setPrice(100L);

        roomManager.create(room);
        assertNotNull(room.getId());

        Room expected = roomManager.find(room.getId());
        assertEquals(expected, room);
    }

    @Test
    @Transactional
    public void testFindAll() {
        Hotel hotel = new Hotel();
        hotel.setName("One");
        hotel.setAddress("Street");

        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber("A01");
        room.setBedsCount(2L);
        room.setPrice(50L);

        roomManager.create(room);

        List<Room> rooms = roomManager.findAll();

        assertTrue(rooms.contains(room));
        assertEquals("The db should contain one room (contains "+rooms.size()+" rooms)", 1, rooms.size());

        rooms = roomManager.findAll(hotel);

        assertTrue(rooms.contains(room));
        assertEquals("The hotel should contain one room (contains "+rooms.size()+" rooms)", 1, rooms.size());


    }

}
