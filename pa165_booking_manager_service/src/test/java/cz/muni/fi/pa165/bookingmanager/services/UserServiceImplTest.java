package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import static junit.framework.Assert.fail;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author mstana
 */
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class UserServiceImplTest {

    
    private UserDAO userDAO;
    private UserServiceImpl userService = new UserServiceImpl();
    private Mapper mapper;

    @Before
    public void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationTestContext.xml");

        mapper = context.getBean("mapper", org.dozer.DozerBeanMapper.class);
        userDAO = Mockito.mock(UserDAO.class);

        ReflectionTestUtils.setField(userService, "userDAO", userDAO);
        ReflectionTestUtils.setField(userService, "mapper", mapper);
    }

    @After
    public void tearDown() {
        userService = null;
        mapper = null;
        userDAO = null;
    }
    @Test
    public void testCreateHotelWithNoRooms() {
        UserTO userTO = new UserTO();
        userTO.setFirstName("Marek");
        userTO.setLastName("Stana");
        userTO.setEmail("rstanamarek@gmail.com");

        userService.create(userTO);
        Mockito.verify(userDAO).create(mapper.map(userTO, User.class));
    }
        @Test
    public void testFindHotel() {
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

        Mockito.when(userDAO.find(userTO.getId())).thenReturn(mapper.map(userTO, User.class));
        Mockito.verify(userDAO).find(userTO.getId());

    }
    
   
    @Test
    public void testDeleteHotel() {
        try {
            userService.delete(null);
            fail("NO IllegalArgumentException when deleting null HotelTO");
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
