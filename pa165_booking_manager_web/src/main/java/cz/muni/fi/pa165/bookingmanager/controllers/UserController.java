package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import cz.muni.fi.pa165.bookingmanager.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import org.springframework.security.access.annotation.Secured;

/**
 * @author mstana
 */
@Controller
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    MessageSource messageSource;

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/userList")
    public ModelAndView handleRequest(HttpServletRequest request) throws ServletException, IOException {

        String aMessage = new String();
        List<UserTO> users = userService.findAll();

        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(request) + "userList");
        modelAndView.addObject("listUsers", users);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public ModelAndView registerUserForm() throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView("userRegister");
        modelAndView.addObject("user", new UserTO());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ModelAndView registerUserSubmit(@Valid @ModelAttribute("userTo") UserTO user, HttpServletRequest req, BindingResult result) throws ServletException, IOException {

        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("register");
            modelAndView.addObject("error", messageSource.getMessage("user.error.create", null, LocaleContextHolder.getLocale()));
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            user.setAdmin(Boolean.FALSE);

            for (UserTO userTO : userService.findAll()) {
                if (user.getUsername().equals(userTO.getUsername())) {
                    modelAndView.addObject("error", messageSource.getMessage("user.error.username.unique", null, LocaleContextHolder.getLocale()));
                    return modelAndView;
                }
            }

            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));
            userService.create(user);
            return modelAndView;
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/userCreate")
    public ModelAndView createUserForm(HttpServletRequest request) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(request) + "userEdit");
        modelAndView.addObject("user", new UserTO());

        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, value = "/userCreate")
    public ModelAndView createUserSubmit(@Valid @ModelAttribute("userTo") UserTO user, HttpServletRequest req, BindingResult result) throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "userEdit");
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            modelAndView.addObject("error", messageSource.getMessage("user.error.create", null, LocaleContextHolder.getLocale()));
            return modelAndView;

        } else {
            if (req.getParameter("isAdmin") != null && req.getParameter("isAdmin").equals("True")) {
                user.setAdmin(Boolean.TRUE);
            } else {
                user.setAdmin(Boolean.FALSE);
            }

            for (UserTO userTO : userService.findAll()) {
                if (user.getUsername().equals(userTO.getUsername())) {
                    modelAndView.addObject("error", messageSource.getMessage("user.error.username.unique", null, LocaleContextHolder.getLocale()));
                    return modelAndView;
                }
            }

            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));
            userService.create(user);
            return modelAndView;
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/userEdit/{userId}")
    public ModelAndView editUser(@PathVariable("userId") long userId, HttpServletRequest req) throws ServletException, IOException {

        UserTO user = userService.find(userId);
        if (user == null) {
            return new ModelAndView(getLayoutUrlPrefix(req) + "index");
        }

        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "userEdit");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, value = "/userEdit/{userId}")
    public ModelAndView editUserSubmit(@PathVariable("userId") long userId, @Valid @ModelAttribute("userTo") UserTO user, HttpServletRequest req, BindingResult result) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "userEdit");
        UserValidator userValidator = new UserValidator();

        UserTO userFromDB = userService.find(userId);
        if (userFromDB == null) {
            modelAndView.addObject("error", messageSource.getMessage("user.error.edit", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        userValidator.validate(user, result);
        modelAndView.addObject("user", userFromDB);

        if (result.hasErrors()) {
            modelAndView.addObject("error", messageSource.getMessage("user.error.edit", null, LocaleContextHolder.getLocale()));
            return modelAndView;

        } else {
            userFromDB.setFirstName(user.getFirstName());
            userFromDB.setLastName(user.getLastName());
            userFromDB.setEmail(user.getEmail());
            userFromDB.setUsername(user.getUsername());
            userFromDB.setPassword(user.getPassword());

            if (req.getParameter("isAdmin") != null && req.getParameter("isAdmin").equals("True")) {
                userFromDB.setAdmin(Boolean.TRUE);
            } else {
                userFromDB.setAdmin(Boolean.FALSE);
            }

            for (UserTO userTO : userService.findAll()) {
                if (userFromDB.getUsername().equals(userTO.getUsername())) {
                    modelAndView.addObject("error", messageSource.getMessage("user.error.username.unique", null, LocaleContextHolder.getLocale()));
                    return modelAndView;
                }
            }

            userService.update(userFromDB);
            modelAndView.addObject("user", userFromDB);
            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));

            return modelAndView;
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/userDelete/{userId}")
    public String deleteSpecifiedUser(@PathVariable("userId") long userId) throws ServletException, IOException {

        UserTO user = userService.find(userId);
        if (user == null) {
            return "redirect:/";
        }

        try {
            userService.delete(user);
        } catch (Exception ex) {
            return "redirect:/userList";
        }

        return "redirect:/userList";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
