package cz.muni.fi.pa165.bookingmanager.services;


import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import java.util.List;

import cz.muni.fi.pa165.bookingmanager.entities.User;
import junit.framework.TestCase;
import org.dozer.Mapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Ondrej Pavelka <106752@mail.muni.cz>
 */

@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
public class RoomServiceImplTest extends TestCase {

    @Mock
    private RoomDAO roomDAO;

    @Autowired
    private Mapper mapper;

    private RoomService roomService;

    public RoomServiceImplTest() {
    }

    @Before
    public void setUp() {
        roomDAO = Mockito.mock(RoomDAO.class);
        roomService = new RoomServiceImpl();
        
        ReflectionTestUtils.setField(roomService, "roomDAO", roomDAO);
        ReflectionTestUtils.setField(roomService, "mapper", mapper);
    }

    @After
    public void tearDown() {
        roomDAO = null;
        mapper = null;
        roomService = null;
    }

    @Test
    public void testCreateGetAndDeleteRoom() {
        try {
            roomService.create(null);
            fail("Cannot create null room.");
        } catch (Exception ex) {
            //OK
        }

        Mockito.verify(roomDAO, Mockito.never()).create(Mockito.any(Room.class));

        RoomTO room = new RoomTO();
        room.setNumber("1");
        room.setBedsCount(2);
        room.setPrice(500);

        roomService.create(room);
        Mockito.verify(roomDAO).create(mapper.map(room,Room.class));
        room.setId(1L);

        try {
            roomService.find(null);
            fail("Cannot find room with null id.");
        } catch (Exception ex) {
            //OK
        }

        Mockito.when(roomDAO.find(room.getId())).thenReturn(mapper.map(room, Room.class));
        roomService.find(room.getId());
        Mockito.verify(roomDAO).find(room.getId());

        roomService.delete(room);
        Mockito.verify(roomDAO).delete(mapper.map(room, Room.class));
    }

}
