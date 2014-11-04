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
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    RoomDAO roomDAO;

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
        try {
            roomDAO.create(null);
        }
        catch (DataAccessException exception) {
            //OK
        }

        Hotel hotel = new Hotel();
        hotel.setName("Continental");
        hotel.setAddress("Botanicka");

        Room room1 = new Room();
        room1.setHotel(hotel);
        room1.setNumber("A01");
        room1.setBedsCount(2);
        room1.setPrice(50);

        assertNull("New room should have null id", room1.getId());

        roomDAO.create(room1);

        assertNotNull("The room id should be assigned by now", room1.getId());

        Room room2 = roomDAO.find(room1.getId());

        assertNotNull("Failed dto retreive room from db", room2);
        assertEquals("The retreived room does not equal the original one", room1, room2);
    }

    @Test
    @Transactional
    public void testUpdate() {
        Room room = new Room();
        assertNull(room.getId());

        Hotel hotel = new Hotel();
        hotel.setName("Holliday Inn");
        hotel.setAddress("Tankodrom");

        room.setHotel(hotel);
        room.setNumber("100");
        room.setBedsCount(6);
        room.setPrice(100);

        roomDAO.create(room);
        assertNotNull(room.getId());

        room.setBedsCount(1);
        room.setPrice(1);

        roomDAO.update(room);

        assertEquals(room.getBedsCount(), 1);
        assertEquals(room.getPrice(), 1);
        assertEquals(room.getNumber(), "100");
    }
    @Test
    @Transactional
    public void testDelete() {
        Room room = new Room();
        Hotel hotel = new Hotel();
        hotel.setName("SKM");
        hotel.setAddress("Botanicka");
        room.setHotel(hotel);
        room.setNumber("100");
        room.setBedsCount(100);
        room.setPrice(100);

        roomDAO.create(room);
        assertNotNull(room.getId());
        roomDAO.delete(room);
        assertNull(roomDAO.find(room.getId()));
    }

    @Test
    @Transactional
    public void testFind() {

        Hotel hotel = new Hotel();
        hotel.setName("FI MU");
        hotel.setAddress("Sumavska");
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber("100");
        room.setBedsCount(100);
        room.setPrice(100);

        roomDAO.create(room);
        assertNotNull(room.getId());

        Room expected = roomDAO.find(room.getId());
        assertEquals(expected, room);
    }

    @Test
    @Transactional
    public void testFindAll() {
        Hotel hotel = new Hotel();
        hotel.setName("Molin rouge");
        hotel.setAddress("Opavska");
        
        Room room = new Room();
        room.setHotel(hotel);
        room.setNumber("A01");
        room.setBedsCount(2);
        room.setPrice(50);

        roomDAO.create(room);

        List<Room> rooms = roomDAO.findAll();

        assertTrue(rooms.contains(room));
        assertEquals("The db should contain one room (contains "+rooms.size()+" rooms)", 1, rooms.size());

        rooms = roomDAO.findAll(hotel);

        assertTrue(rooms.contains(room));
        assertEquals("The hotel should contain one room (contains "+rooms.size()+" rooms)", 1, rooms.size());
    }

}
