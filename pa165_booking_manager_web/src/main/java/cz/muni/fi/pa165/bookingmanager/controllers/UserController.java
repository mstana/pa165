package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import cz.muni.fi.pa165.bookingmanager.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

        ModelAndView modelAndView = new ModelAndView("userEdit");
        modelAndView.addObject("user", new UserTO());

        return modelAndView;
    }


    @RequestMapping(method= RequestMethod.POST, value="/userCreate")
    public ModelAndView createUserSubmit(@ModelAttribute("UserTO") @Valid UserTO user, HttpServletRequest req, BindingResult result) throws ServletException, IOException
    {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);
        ModelAndView modelAndView = new ModelAndView("userEdit");
        if (result.hasErrors()) {
            modelAndView.addObject("error", messageSource.getMessage("user.error", null, LocaleContextHolder.getLocale()));
                
            return modelAndView;
        } else {
            if (req.getParameter("isAdmin")!= null && req.getParameter("isAdmin").equals("True")) 
            {
                user.setIsAdmin(Boolean.TRUE);
            } else 
            {
                user.setIsAdmin(Boolean.FALSE);
            }

            userService.create(user);
            return modelAndView;
        }
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
    public ModelAndView editUserSubmit(@PathVariable("userId") long userId, @ModelAttribute("UserTO") @Valid UserTO user, HttpServletRequest req, BindingResult result) throws ServletException, IOException
    {   
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);
        ModelAndView modelAndView = new ModelAndView("userEdit");
        modelAndView.addObject("user", user);
         if (result.hasErrors()) {
            modelAndView.addObject("error", messageSource.getMessage("user.error.edit" , null, LocaleContextHolder.getLocale()));
            
            return modelAndView;
        } else {
            UserTO userFromDB = userService.find(userId);
            if (userFromDB == null) {
                modelAndView.addObject("error", messageSource.getMessage("user.error.edit", null, LocaleContextHolder.getLocale()));
                return modelAndView;
            }

            userFromDB.setFirstName(user.getFirstName());
            userFromDB.setLastName(user.getLastName());
            userFromDB.setEmail(user.getEmail());

            if (req.getParameter("isAdmin")!= null && req.getParameter("isAdmin").equals("True")) 
            {
                userFromDB.setIsAdmin(Boolean.TRUE);
            } else 
            {
                userFromDB.setIsAdmin(Boolean.FALSE);
            }

            userService.update(userFromDB);
            modelAndView.addObject("user", userFromDB);
            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));

            return modelAndView;
         }
    }
    @RequestMapping(method= RequestMethod.GET, value="/userDelete/{userId}")
    public String deleteSpecifiedUser(@PathVariable("userId") long userId) throws ServletException, IOException
    {

        UserTO user = userService.find(userId);
        if (user == null) {
            return "redirect:/";
        }

        try
        {
            userService.delete(user);
        }
        catch (Exception ex)
        {
            return "redirect:/userList";
        }


        return "redirect:/userList";
    }
}
