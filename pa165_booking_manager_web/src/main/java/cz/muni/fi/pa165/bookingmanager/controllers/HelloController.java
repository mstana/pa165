package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import java.io.IOException;

@Controller
public class HelloController  {

    @Autowired
    HotelService hotelService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method=RequestMethod.GET, value="/hotels")
    public ModelAndView handleRequest() throws ServletException, IOException {

        String aMessage = new String();
        for (HotelTO hotel : hotelService.findAll()) {
            aMessage += "<br /> " + messageSource.getMessage("hotel.title",null, LocaleContextHolder.getLocale()) + ": "+hotel.getName();
        }

        ModelAndView modelAndView = new ModelAndView("hotels");
        modelAndView.addObject("message", aMessage);
        modelAndView.addObject("hotelsTitle",messageSource.getMessage("hotel.list.title",null, LocaleContextHolder.getLocale()));

        return modelAndView;
    }

    @RequestMapping(method=RequestMethod.GET, value="")
    public String handleRequest2() throws ServletException, IOException {
        for (HotelTO hotel : hotelService.findAll()) {
            hotelService.delete(hotel);
        }
        HotelTO hotel = new HotelTO();
        hotel.setAddress("sdf");
        hotel.setName("prvni");
        hotelService.create(hotel);

        HotelTO hotel2 = new HotelTO();
        hotel2.setName("druhy");
        hotelService.create(hotel2);

        return "index";
    }
}