package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Administrator;
import cz.muni.fi.pa165.bookingmanager.entities.Guest;
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
public class UserServiceImplTest {
}
