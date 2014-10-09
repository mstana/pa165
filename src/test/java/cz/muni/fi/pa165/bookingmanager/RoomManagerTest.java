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
}
