package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.ReservationService;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
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

    @RequestMapping(method= RequestMethod.GET, value="/userreservations/{userId}")
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

    @RequestMapping(method= RequestMethod.GET, value="/roomreservations/{roomId}")
    public ModelAndView getRoomReservations(@PathVariable("roomId") long roomId) throws ServletException, IOException
    {
        RoomTO room = roomService.find(roomId);
        ModelAndView modelAndView = new ModelAndView("index");

        if (room == null) {
            modelAndView.addObject("error",  messageSource.getMessage("room.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        List<ReservationTO> reservations = reservationService.findAll(room);

        modelAndView.setViewName("reservationList");
        modelAndView.addObject("reservations", reservations);
        modelAndView.addObject("room", room);

        return modelAndView;
    }

    @RequestMapping(method= RequestMethod.GET, value="/deletereservation/{reservationId}")
    public String deleteSpecifiedReservation(@PathVariable("reservationId") long reservationId) throws ServletException, IOException
    {
        ReservationTO reservation = reservationService.find(reservationId);

        if (reservation == null)
        {
            return "redirect:/";
        }

        RoomTO room = reservation.getRoom();

        try
        {
            reservationService.delete(reservation);
        }
        catch (Exception ex)
        {
            return "redirect:/";
        }

        return "redirect:/roomreservations/" + room.getId();
    }

    @RequestMapping(method= RequestMethod.GET, value="/newreservation/{roomId}")
    public ModelAndView createReservationForm(@PathVariable("roomId") long roomId) throws ServletException, IOException
    {
        RoomTO room = roomService.find(roomId);
        ModelAndView modelAndView = new ModelAndView("index");

        if (room == null) {
            modelAndView.addObject("error",  messageSource.getMessage("room.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        modelAndView.setViewName("reservationEdit");
        modelAndView.addObject("reservation", new ReservationTO());
        modelAndView.addObject("room", room);
        modelAndView.addObject("users", userService.findAll());

        return modelAndView;
    }


    @RequestMapping(method= RequestMethod.POST, value="/newreservation/{roomId}")
    public String createReservationSubmit(@PathVariable("roomId") long roomId,  @Valid ReservationTO reservation, BindingResult result) throws ServletException, IOException
    {
        RoomTO room = roomService.find(roomId);
        if (room == null)
        {
            return "redirect:/";
        }

        room.addReservation(reservation);
        reservation.setRoom(room);
        reservation.setUser(userService.findAll().get(0));

        reservationService.create(reservation);

        return "redirect:/reservation/" + reservation.getId();
    }
}
