package cz.muni.fi.pa165.bookingmanager.controllers;

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

@Controller
public class ReservationController extends BaseController {

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

    @RequestMapping(method = RequestMethod.GET, value = "/userreservations/{userId}")
    public ModelAndView getUserReservations(@PathVariable("userId") long userId, HttpServletRequest req) throws ServletException, IOException {
        UserTO user = userService.find(userId);
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "index");

        if (user == null) {
            modelAndView.addObject("error", messageSource.getMessage("user.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        if (!req.isUserInRole(ROLE_ADMIN) && !user.getUsername().equals(req.getRemoteUser())) {
            modelAndView.addObject("error", messageSource.getMessage("user.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        List<ReservationTO> reservations = reservationService.findAll(user);

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "reservationList");
        modelAndView.addObject("reservations", reservations);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/myreservations")
    public String getLoggedUserReservations(HttpServletRequest req) throws ServletException, IOException {

        Long userId = 0L;
        for (UserTO user : userService.findAll()) {
            if (user.getUsername().equals(req.getRemoteUser())) {
                userId = user.getId();
            }
        }
        if (userId == 0) {
            return "redirect:/";
        }

        return "redirect:/userreservations/" + userId;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/roomreservations/{roomId}")
    public ModelAndView getRoomReservations(@PathVariable("roomId") long roomId, HttpServletRequest req) throws ServletException, IOException {
        RoomTO room = roomService.find(roomId);
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "index");

        if (room == null) {
            modelAndView.addObject("error", messageSource.getMessage("room.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        List<ReservationTO> reservations = reservationService.findAll(room);

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "reservationList");
        modelAndView.addObject("reservations", reservations);
        modelAndView.addObject("room", room);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reservation/{reservationId}")
    public ModelAndView editReservation(@PathVariable("reservationId") long reservationId, HttpServletRequest req) throws ServletException, IOException {
        ReservationTO reservation = reservationService.find(reservationId);

        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "reservationList");

        if (reservation == null) {
            modelAndView.addObject("error", messageSource.getMessage("reservation.not.found.id", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "reservationEdit");
        modelAndView.addObject("reservation", reservation);
        modelAndView.addObject("room", reservation.getRoom());
        modelAndView.addObject("users", userService.findAll());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reservation/{reservationId}")
    public ModelAndView editReservationSubmit(@PathVariable("reservationId") long reservationId, @Valid ReservationTO reservation, BindingResult result, HttpServletRequest req) throws ServletException, IOException {
        ModelAndView modelAndView = new ModelAndView("index");

        ReservationTO resFromDB = reservationService.find(reservationId);
        if (resFromDB == null) {
            modelAndView.addObject("error", messageSource.getMessage("reservation.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "reservationEdit");

        try {
            Long userId = Long.parseLong(req.getParameter("user"));
            UserTO user = userService.find(userId);

            resFromDB.setBeginDate(reservation.getBeginDate());
            resFromDB.setEndDate(reservation.getEndDate());
            resFromDB.setUser(user);

            reservationService.update(resFromDB);

            modelAndView.addObject("reservation", resFromDB);
            modelAndView.addObject("room", resFromDB.getRoom());
            modelAndView.addObject("users", userService.findAll());

            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));
        } catch (Exception ex) {
            modelAndView.addObject("reservation", reservation);
            modelAndView.addObject("error", messageSource.getMessage("general.error", null, LocaleContextHolder.getLocale()));
        }

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deletereservation/{reservationId}")
    public String deleteSpecifiedReservation(@PathVariable("reservationId") long reservationId) throws ServletException, IOException {
        ReservationTO reservation = reservationService.find(reservationId);

        if (reservation == null) {
            return "redirect:/";
        }

        RoomTO room = reservation.getRoom();

        try {
            reservationService.delete(reservation);
        } catch (Exception ex) {
            return "redirect:/";
        }

        return "redirect:/roomreservations/" + room.getId();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/newreservation/{roomId}")
    public ModelAndView createReservationForm(@PathVariable("roomId") long roomId, HttpServletRequest req) throws ServletException, IOException {
        RoomTO room = roomService.find(roomId);
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "index");

        if (room == null) {
            modelAndView.addObject("error", messageSource.getMessage("room.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "reservationEdit");
        modelAndView.addObject("reservation", new ReservationTO());
        modelAndView.addObject("room", room);
        modelAndView.addObject("users", userService.findAll());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/newreservation/{roomId}")
    public String createReservationSubmit(@PathVariable("roomId") long roomId, @Valid @ModelAttribute("reservationTo") ReservationTO reservation, BindingResult result, HttpServletRequest req) throws ServletException, IOException {
        RoomTO room = roomService.find(roomId);
        if (room == null) {
            return "redirect:/";
        }

        try {
            Long userId = Long.parseLong(req.getParameter("user"));
            UserTO user = userService.find(userId);

            room.addReservation(reservation);
            reservation.setRoom(room);
            reservation.setUser(user);

            reservationService.create(reservation);
        } catch (Exception ex) {
            return "redirect:/";
        }

        return "redirect:/reservation/" + reservation.getId();
    }
}
