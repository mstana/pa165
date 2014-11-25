package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.List;
import static junit.framework.Assert.fail;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mstana
 */
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = "classpath:/application" +
        "Test" +
        "Context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @Autowired
    private Mapper mapper;

    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDAO,mapper);
    }

    @After
    public void tearDown() {
        userService = null;
        mapper = null;
        userDAO = null;
    }
    @Test
    public void testCreateUserWithNoRooms() {
        UserTO userTO = new UserTO();
        userTO.setFirstName("Marek");
        userTO.setLastName("Stana");
        userTO.setEmail("rstanamarek@gmail.com");

        userService.create(userTO);
        Mockito.verify(userDAO).create(mapper.map(userTO, User.class));
    }
    @Test
    public void testFindUser() {
        try {
            userService.find(null);
            fail("No IllegalArgumentException for null id");
        } catch (IllegalArgumentException e) {
            //OK
        }

        UserTO userTO = new UserTO();
        userTO.setFirstName("Marek");
        userTO.setLastName("Stana");
        userTO.setEmail("rstanamarek@gmail.com");

        userService.create(userTO);
        Mockito.verify(userDAO).create(mapper.map(userTO, User.class));

        userTO.setId(1L);

        Mockito.when(userDAO.find(userTO.getId())).thenReturn(mapper.map(userTO, User.class));
        userDAO.find(userTO.getId());
        Mockito.verify(userDAO).find(userTO.getId());

    }
    
   
    @Test
    public void testDeleteUser() {
        try {
            userService.delete(null);
            fail("NO IllegalArgumentException when deleting null UserTO");
        } catch (Exception e) {
            //OK
        }

        Mockito.verifyZeroInteractions(userDAO);

        UserTO userTO = new UserTO();
        userTO.setFirstName("Marek");
        userTO.setLastName("Stana");
        userTO.setEmail("rstanamarek@gmail.com");
        

        userService.create(userTO);
        userTO.setId(1L);

        Mockito.when(userDAO.find(userTO.getId())).thenReturn(mapper.map(userTO, User.class));
        userService.delete(userTO);

    }
    @Test
    public void testFindAllUsers() {
        List<UserTO> users = userService.findAll();
        Mockito.verify(userDAO).findAll();
    }
    
}
