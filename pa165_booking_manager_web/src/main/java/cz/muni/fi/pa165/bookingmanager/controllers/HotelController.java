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

        for (HotelTO hotel : hotelService.findAll()) {
            hotelService.delete(hotel);
        }

        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");
        hotelService.create(hotelTO);
        hotelTO.setId(1L);

        HotelTO hotelTO2 = new HotelTO();
        hotelTO2.setAddress("Hrcirska 10, Brno");
        hotelTO2.setName("Hotel Slovan");
        hotelService.create(hotelTO2);
        hotelTO2.setId(2L);

        HotelTO hotelTO3 = new HotelTO();
        hotelTO3.setAddress("Masarykova 1, Brno");
        hotelTO3.setName("Hotel Continental");

        hotelService.create(hotelTO3);
        hotelTO3.setId(3L);


        List<HotelTO> hotels = hotelService.findAll();

        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("hotels", hotels);


        String aMessage = new String();
        for (HotelTO hotel : hotelService.findAll()) {
            aMessage += "<br /> id: "+hotel.getId();
            aMessage += "<br /> hotel: "+hotel.getName();
        }

        modelAndView.addObject("message", aMessage);


        return modelAndView;
    }

}
