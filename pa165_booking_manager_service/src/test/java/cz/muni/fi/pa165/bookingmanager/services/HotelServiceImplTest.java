package cz.muni.fi.pa165.bookingmanager.services;
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
public class HotelServiceImplTest extends TestCase {

}
