package cz.muni.fi.pa165.bookingmanager.services;


import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.dao.HotelDAO;
import cz.muni.fi.pa165.bookingmanager.dao.RoomDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import java.util.List;
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

    private RoomService roomService = new RoomServiceImpl();

    public RoomServiceImplTest() {
    }

    @Before
    public void setUp() {
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
    public void testCreateRoomWithoutHotel() {
        RoomTO room = new RoomTO();
        room.setNumber("1");
        room.setBedsCount(2);
        room.setPrice(500);

        roomService.create(room);
        Mockito.verify(roomDAO).create(mapper.map(room, Room.class));
    }
}