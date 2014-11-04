package cz.muni.fi.pa165.bookingmanager.services;


import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import java.util.List;

import junit.framework.TestCase;
import org.dozer.Mapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;


/**
 *
 * @author Ondrej Pavelka <106752@mail.muni.cz>
 */

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class RoomServiceImplTest extends TestCase {

    private RoomService roomService;

    @Mock
    private RoomDAO roomDAO;

    @Autowired
    private Mapper mapper;

    public RoomServiceImplTest() {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        roomService = new RoomServiceImpl(roomDAO,mapper);
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
