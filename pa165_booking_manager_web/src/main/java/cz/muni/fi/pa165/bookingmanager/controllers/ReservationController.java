package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.ReservationService;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    RoomService roomService;

    @Autowired
    HotelService hotelService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    @RequestMapping(method= RequestMethod.GET, value="/reservations/{userId}")
    public ModelAndView getUserReservations(@PathVariable("userId") long userId) throws ServletException, IOException
    {
        UserTO user = userService.find(userId);
        ModelAndView modelAndView = new ModelAndView("index");

        if (user == null) {
            modelAndView.addObject("error",  messageSource.getMessage("user.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        List<ReservationTO> reservations = reservationService.findAll(user);

        modelAndView.setViewName("reservationList");
        modelAndView.addObject("reservations", reservations);
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}
