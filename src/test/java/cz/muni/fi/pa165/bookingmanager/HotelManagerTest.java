package cz.muni.fi.pa165.bookingmanager;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Administrator;
import cz.muni.fi.pa165.bookingmanager.entities.Guest;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.transaction.Transactional;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mstana
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
public class HotelManagerTest extends TestCase {
    
    @Autowired
    private HotelDAO hotelDAO;
    
    public HotelManagerTest() {
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
        
        assertNull("ID must be null before persist", hotel.getId());
        hotelDAO.create(hotel);
        assertNotNull("ID CANT BE NULL", hotel.getId());

        Hotel hotelEqual = hotelDAO.find(hotel.getId());
        assertNotNull(hotelEqual.getId());
        assertEquals(hotelEqual, hotel);

    }
    
    @Test
    @Transactional
    public void testUpdate() {
        Hotel hotel = new Hotel();
        
        assertNull("ID must be null before persist", hotel.getId());
        hotelDAO.create(hotel);
        assertNotNull("ID CANT BE NULL", hotel.getId());

        hotel.setName("Hilton");
        hotel.setAddress("Pekna 5, Brno");
        hotelDAO.update(hotel);

        assertNotNull(hotel.getAddress());
        assertNotNull(hotel.getName());
        assertEquals("Pekna 5, Brno", hotel.getAddress());
        assertEquals("Hilton", hotel.getName());
}

    @Test
    @Transactional
    public void testDelete() {
        Hotel hotel = new Hotel();
        hotel.setAddress("some address");
        hotel.setName("hilton");
        
        hotelDAO.create(hotel);
        hotelDAO.delete(hotel);
        assertNull("ID must be null after delete", hotelDAO.find(hotel.getId()));
    }

    @Test
    @Transactional
    public void testFind() {
        Hotel hotel = new Hotel();
        
        assertNull(hotel.getId());
        hotelDAO.create(hotel);

        Hotel hotelEqual = hotelDAO.find(hotel.getId());
        assertNotNull(hotelEqual);
        assertEquals(hotelEqual, hotel);
    }

    @Test
    @Transactional
    public void testFindAll() {
        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        Hotel hotel3 = new Hotel();
    
        assertNull(hotel1.getId());
        assertNull(hotel2.getId());
        assertNull(hotel3.getId());
        
        hotelDAO.create(hotel1);
        hotelDAO.create(hotel2);
        hotelDAO.create(hotel3);
        
        List<Hotel> hotelList = hotelDAO.findAll();

        assertTrue(hotelList.contains(hotel1));
        assertTrue(hotelList.contains(hotel2));
        assertTrue(hotelList.contains(hotel3));
    }
    @Test
    @Transactional
    public void testFindAllRoomsInHotel() {
        
        Hotel hotel = new Hotel();
        List<Room> listOfRooms = new ArrayList<>();
        
        Room room1 = new Room();
        Room room2 = new Room();
        Room room3 = new Room();
        Room room4 = new Room();
        Room room5 = new Room();
        
        listOfRooms.add(room1);
        listOfRooms.add(room2);
        listOfRooms.add(room3);
        listOfRooms.add(room4);
        listOfRooms.add(room5);
        
        
        assertNull(hotel.getId());
        assertNull(room1.getId());
        assertNull(room2.getId());
        assertNull(room3.getId());
        assertNull(room4.getId());
        assertNull(room5.getId());

        hotel.setRooms(listOfRooms);
        
        
        hotelDAO.create(hotel);
        
        Hotel hotelFromDb = hotelDAO.find(hotel.getId());
        assertNotNull(hotelFromDb);
        assertEquals(hotelFromDb.getRooms(), listOfRooms);
        //assertTrue(hotelList.contains(hotel1));
        }
}
