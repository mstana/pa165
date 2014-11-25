package cz.muni.fi.pa165.bookingmanager.managers;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
    @Autowired
    private RoomDAO roomDAO;

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
        hotel.setName("hilton");
        hotel.setAddress("some adress");
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
        hotel.setName("hilton");
        hotel.setAddress("some adress");
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
        hotel.setName("hilton");
        hotel.setAddress("some adress");
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

        hotel1.setName("hilton1");
        hotel1.setAddress("some adress1");

        hotel2.setName("hilton2");
        hotel2.setAddress("some adress2");

        hotel3.setName("hilton3");
        hotel3.setAddress("some adress3");


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

        List<Room> listOfRooms = new ArrayList<>();
        Hotel hotel = new Hotel();
        hotel.setName("Hotel");
        hotel.setAddress("Avenue1");



        assertNull(hotel.getId());

        hotelDAO.create(hotel);


        Room room1 = new Room();
        room1.setHotel(hotel);
        room1.setNumber("101");
        room1.setBedsCount(100);
        room1.setPrice(100);
        listOfRooms.add(room1);

        Room room2 = new Room();
        room2.setHotel(hotel);
        room2.setNumber("102");
        room2.setBedsCount(100);
        room2.setPrice(100);
        listOfRooms.add(room2);

        Room room3 = new Room();
        room3.setHotel(hotel);
        room3.setNumber("103");
        room3.setBedsCount(100);
        room3.setPrice(100);
        listOfRooms.add(room3);

        Room room4 = new Room();
        room4.setHotel(hotel);
        room4.setNumber("104");
        room4.setBedsCount(100);
        room4.setPrice(100);
        listOfRooms.add(room4);

        roomDAO.create(room1);
        roomDAO.create(room2);
        roomDAO.create(room3);
        roomDAO.create(room4);

        hotel.addRoom(room1);
        hotel.addRoom(room2);
        hotel.addRoom(room3);
        hotel.addRoom(room4);

        hotelDAO.update(hotel);


        Hotel hotelFromDb = hotelDAO.find(hotel.getId());
        assertNotNull(hotelFromDb);
        Assert.assertEquals(hotelFromDb.getName(), "Hotel");


        assertEquals(listOfRooms.size(), hotelFromDb.getRooms().size());
        assertEquals(listOfRooms.contains(hotelFromDb.getRooms().get(0)), true);
        assertEquals(listOfRooms.contains(hotelFromDb.getRooms().get(1)), true);
        assertEquals(listOfRooms.contains(hotelFromDb.getRooms().get(2)), true);
        assertEquals(listOfRooms.contains(hotelFromDb.getRooms().get(3)), true);

        System.out.println("Hello"+hotelFromDb.getRooms());
        System.out.println(listOfRooms);


        }
}
