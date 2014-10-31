package cz.muni.fi.pa165.bookingmanager.services;


import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import java.util.List;
import junit.framework.TestCase;
import org.dozer.Mapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author Ondrej Pavelka <106752@mail.muni.cz>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
public class RoomServiceImplTest extends TestCase {

    @Mock
    RoomService roomService;

    public RoomServiceImplTest() {
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
    public void testCreate() {
        RoomTO roomTO = new RoomTO();
        roomService.create(roomTO);
    }
}
