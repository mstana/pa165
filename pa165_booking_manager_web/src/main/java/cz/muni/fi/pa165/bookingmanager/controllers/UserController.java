package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Controller;

/**
 *
 * @author mstana
 */

@Controller 
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method=RequestMethod.GET, value="/users")
    public ModelAndView handleRequest() throws ServletException, IOException {

        String aMessage = new String();
        List<UserTO> users = userService.findAll();

        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("listUsers", users);
        
        return modelAndView;
    }
}
