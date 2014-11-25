package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
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
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method=RequestMethod.GET, value="/hello")
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

        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");
        hotelService.create(hotelTO);

        HotelTO hotelTO2 = new HotelTO();
        hotelTO2.setAddress("Hrcirska 10, Brno");
        hotelTO2.setName("Hotel Slovan");
        hotelService.create(hotelTO2);

        HotelTO hotelTO3 = new HotelTO();
        hotelTO3.setAddress("Masarykova 1, Brno");
        hotelTO3.setName("Hotel Continental");
        hotelService.create(hotelTO3);


//        for (UserTO user : userService.findAll()) {
//            userService.delete(user);
//        }

        UserTO u1 = new UserTO();
        u1.setFirstName("Marek");
        u1.setLastName("Stana");
        u1.setEmail("marek.stana@ms.com");
        userService.create(u1);

        UserTO u2 = new UserTO();
        u2.setFirstName("David");
        u2.setLastName("Kadlec");
        u2.setEmail("david.kadlec@dk.com");
        userService.create(u2);

        UserTO u3 = new UserTO();
        u3.setFirstName("Adam");
        u3.setLastName("Studenic");
        u3.setEmail("adam.studenic@as.com");
        userService.create(u3);


        return "index";
    }
}