package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adams
 */

@Controller
public class HotelController {

    @Autowired
    HotelService hotelService;

    @RequestMapping(method=RequestMethod.GET, value="/hotels")
    public ModelAndView handleRequest() throws ServletException, IOException {
        List<HotelTO> hotels = hotelService.findAll();

        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("hotels", hotels);

        return modelAndView;
    }

}
