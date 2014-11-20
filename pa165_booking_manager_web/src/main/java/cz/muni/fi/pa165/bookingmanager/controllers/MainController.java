package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.entities.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private HotelService hotelService;
}
