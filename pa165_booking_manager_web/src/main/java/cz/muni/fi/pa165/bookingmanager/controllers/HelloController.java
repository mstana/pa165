package cz.muni.fi.pa165.bookingmanager.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import java.io.IOException;

@Controller
public class HelloController  {

    @Autowired
    HotelService hotelService;

    @RequestMapping(method=RequestMethod.GET, value="/hello")
    public ModelAndView handleRequest() throws ServletException, IOException {

        String aMessage = "Hello World MVC!";

        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("message", aMessage);

        return modelAndView;
    }

    @RequestMapping(method=RequestMethod.GET, value="")
    public String handleRequest2() throws ServletException, IOException {
        HotelTO hotel = new HotelTO();
        hotel.setAddress("sdf");
        hotel.setName("dalsi");
        hotelService.create(hotel);
        return "index";
    }
}
