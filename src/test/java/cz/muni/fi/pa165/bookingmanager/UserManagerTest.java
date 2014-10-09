package cz.muni.fi.pa165.bookingmanager;

import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Administrator;
import cz.muni.fi.pa165.bookingmanager.entities.Guest;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Adam Studenic
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application"
        + "Test"
        + "Context.xml")
public class UserManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserDAO userManager;

    public UserManagerTest() {
        super();
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
        User guest = new Guest();
        User administrator = new Administrator();

        assertNull("ID must be null before persist", guest.getId());
        userManager.create(guest);
        assertNotNull("ID should not be null", guest.getId());

        assertNull("ID must be null before persist", administrator.getId());
        userManager.create(administrator);
        assertNotNull("ID should not be null", administrator.getId());

        User guestEqual = userManager.find(guest.getId());
        assertNotNull(guestEqual.getId());
        assertEquals(guestEqual, guest);

        User adminEqual = userManager.find(administrator.getId());
        assertNotNull(adminEqual.getId());
        assertEquals(adminEqual, administrator);
    }

    @Test
    @Transactional
    public void testUpdate() {
        User guest = new Guest();
        User administrator = new Administrator();

        assertNull("ID must be null before persist", guest.getId());
        userManager.create(guest);
        assertNotNull("ID should not be null", guest.getId());

        guest.setFirstName("Thomas");
        guest.setLastName("Podolski");
        userManager.update(guest);

        assertNotNull(guest.getFirstName());
        assertNotNull(guest.getLastName());
        assertEquals("Thomas", guest.getFirstName());
        assertEquals("Podolski", guest.getLastName());

        assertNull("ID must be null before persist", administrator.getId());
        userManager.create(administrator);
        assertNotNull("ID should not be null", administrator.getId());

        administrator.setFirstName("Miroslav");
        administrator.setLastName("Stoch");
        userManager.update(administrator);

        assertNotNull(administrator.getFirstName());
        assertNotNull(administrator.getLastName());
        assertEquals("Miroslav", administrator.getFirstName());
        assertEquals("Stoch", administrator.getLastName());
    }

    @Test
    @Transactional
    public void testDelete() {
        User guest = new Guest();
        User administrator = new Administrator();

        userManager.create(guest);
        userManager.delete(guest);
        assertNull("ID must be null after delete", userManager.find(guest.getId()));

        userManager.create(administrator);
        userManager.delete(administrator);
        assertNull("ID must be null after delete", userManager.find(administrator.getId()));

    }

    @Test
    @Transactional
    public void testFind() {
        User guest = new Guest();
        User administrator = new Administrator();

        assertNull(guest.getId());
        userManager.create(guest);

        User guestEqual = userManager.find(guest.getId());
        assertNotNull(guestEqual);
        assertEquals(guestEqual, guest);

        assertNull(administrator.getId());
        userManager.create(administrator);

        User adminEqual = userManager.find(administrator.getId());
        assertNotNull(adminEqual);
        assertEquals(adminEqual, administrator);
    }

    @Test
    @Transactional
    public void testFindAll() {
        User guest1 = new Guest();
        User guest2 = new Guest();
        User guest3 = new Guest();
        User administrator1 = new Administrator();
        User administrator2 = new Administrator();
        User administrator3 = new Administrator();

        assertNull(guest1.getId());
        assertNull(guest2.getId());
        assertNull(guest3.getId());
        assertNull(administrator1.getId());
        assertNull(administrator2.getId());
        assertNull(administrator3.getId());

        userManager.create(guest1);
        userManager.create(guest2);
        userManager.create(guest3);
        userManager.create(administrator1);
        userManager.create(administrator2);
        userManager.create(administrator3);

        List<User> userList = userManager.findAll();

        assertTrue(userList.contains(guest1));
        assertTrue(userList.contains(guest2));
        assertTrue(userList.contains(guest3));
        assertTrue(userList.contains(administrator1));
        assertTrue(userList.contains(administrator2));
        assertTrue(userList.contains(administrator3));

    }

}
