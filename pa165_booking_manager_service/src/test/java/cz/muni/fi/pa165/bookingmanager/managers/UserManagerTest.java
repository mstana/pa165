package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;

import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.List;
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
    private UserDAO userDAO;

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
        User guest = new User();
        guest.setAdmin(Boolean.FALSE);
        User administrator = new User();
        administrator.setAdmin(Boolean.TRUE);

        assertNull("ID must be null before persist", guest.getId());
        userDAO.create(guest);
        assertNotNull("ID should not be null", guest.getId());

        assertNull("ID must be null before persist", administrator.getId());
        userDAO.create(administrator);
        assertNotNull("ID should not be null", administrator.getId());

        User guestEqual = userDAO.find(guest.getId());
        assertNotNull(guestEqual.getId());
        assertEquals(guestEqual, guest);

        User adminEqual = userDAO.find(administrator.getId());
        assertNotNull(adminEqual.getId());
        assertEquals(adminEqual, administrator);
    }

    @Test
    @Transactional
    public void testUpdate() {
        User guest = new User();
        User administrator = new User();
        assertNull("ID must be null before persist", guest.getId());
        userDAO.create(guest);
        assertNotNull("ID should not be null", guest.getId());

        guest.setFirstName("Thomas");
        guest.setLastName("Podolski");
        guest.setAdmin(Boolean.FALSE);
        userDAO.update(guest);

        assertNotNull(guest.getFirstName());
        assertNotNull(guest.getLastName());
        assertEquals("Thomas", guest.getFirstName());
        assertEquals("Podolski", guest.getLastName());

        assertNull("ID must be null before persist", administrator.getId());
        userDAO.create(administrator);
        assertNotNull("ID should not be null", administrator.getId());

        administrator.setFirstName("Miroslav");
        administrator.setLastName("Stoch");
        administrator.setAdmin(Boolean.TRUE);
        userDAO.update(administrator);

        assertNotNull(administrator.getFirstName());
        assertNotNull(administrator.getLastName());
        assertEquals("Miroslav", administrator.getFirstName());
        assertEquals("Stoch", administrator.getLastName());
    }

    @Test
    @Transactional
    public void testDelete() {
        User guest = new User();
        guest.setAdmin(Boolean.FALSE);
        User administrator = new User();
        administrator.setAdmin(Boolean.TRUE);

        userDAO.create(guest);
        userDAO.delete(guest);
        assertNull("ID must be null after delete", userDAO.find(guest.getId()));

        userDAO.create(administrator);
        userDAO.delete(administrator);
        assertNull("ID must be null after delete", userDAO.find(administrator.getId()));

    }

    @Test
    @Transactional
    public void testFind() {
        User guest = new User();
        guest.setAdmin(Boolean.FALSE);
        User administrator = new User();
        administrator.setAdmin(Boolean.TRUE);

        assertNull(guest.getId());
        userDAO.create(guest);

        User guestEqual = userDAO.find(guest.getId());
        assertNotNull(guestEqual);
        assertEquals(guestEqual, guest);

        assertNull(administrator.getId());
        userDAO.create(administrator);

        User adminEqual = userDAO.find(administrator.getId());
        assertNotNull(adminEqual);
        assertEquals(adminEqual, administrator);
    }

    @Test
    @Transactional
    public void testFindAll() {
        User guest1 = new User();
        guest1.setAdmin(Boolean.FALSE);
        User guest2 = new User();
        guest2.setAdmin(Boolean.FALSE);
        User guest3 = new User();
        guest3.setAdmin(Boolean.FALSE);
        User administrator1 = new User();
        administrator1.setAdmin(Boolean.TRUE);
        User administrator2 = new User();
        administrator2.setAdmin(Boolean.TRUE);
        User administrator3 = new User();
        administrator3.setAdmin(Boolean.TRUE);
        assertNull(guest1.getId());
        assertNull(guest2.getId());
        assertNull(guest3.getId());
        assertNull(administrator1.getId());
        assertNull(administrator2.getId());
        assertNull(administrator3.getId());

        userDAO.create(guest1);
        userDAO.create(guest2);
        userDAO.create(guest3);
        userDAO.create(administrator1);
        userDAO.create(administrator2);
        userDAO.create(administrator3);

        List<User> userList = userDAO.findAll();

        assertTrue(userList.contains(guest1));
        assertTrue(userList.contains(guest2));
        assertTrue(userList.contains(guest3));
        assertTrue(userList.contains(administrator1));
        assertTrue(userList.contains(administrator2));
        assertTrue(userList.contains(administrator3));

    }

}
