package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author mstana
 */

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    MessageSource messageSource;

    @RequestMapping(method=RequestMethod.GET, value="/userList")
    public ModelAndView handleRequest() throws ServletException, IOException {

        String aMessage = new String();
        List<UserTO> users = userService.findAll();

        ModelAndView modelAndView = new ModelAndView("userList");
        modelAndView.addObject("listUsers", users);

        return modelAndView;
    }

    @RequestMapping(method= RequestMethod.GET, value="/userCreate")
    public ModelAndView createUserForm() throws ServletException, IOException
    {

        ModelAndView modelAndView = new ModelAndView("userCreate");
        modelAndView.addObject("user", new UserTO());

        return modelAndView;
    }


    @RequestMapping(method= RequestMethod.POST, value="/userCreate")
    public String createUserSubmit(@ModelAttribute("UserTO")UserTO user) throws ServletException, IOException
    {
        userService.create(user);
        return "redirect:userList";
    }
    @RequestMapping(method= RequestMethod.GET, value="/userEdit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") long userId) throws ServletException, IOException
    {

        UserTO user = userService.find(userId);
        if (user == null) {
            return new ModelAndView("index");
        }

        ModelAndView modelAndView = new ModelAndView("userEdit");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(method= RequestMethod.POST, value="/userEdit/{userId}")
    public ModelAndView editUserSubmit(@PathVariable("userId") long userId, @ModelAttribute("UserTO")UserTO user) throws ServletException, IOException
    {

        UserTO userFromDB = userService.find(userId);
        if (userFromDB == null) {
            return new ModelAndView("index");
        }

        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setEmail(user.getEmail());
//        userFromDB.setIsAdmin(user.getIsAdmin());

        userService.update(userFromDB);

        ModelAndView modelAndView = new ModelAndView("userEdit");
        modelAndView.addObject("user", userFromDB);
        modelAndView.addObject("message", "Edit done!");
        modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));

        return modelAndView;
    }

}
