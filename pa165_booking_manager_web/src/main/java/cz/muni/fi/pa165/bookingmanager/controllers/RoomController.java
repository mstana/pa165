package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
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
 * Created by David Kadlec
 */
@Controller
public class RoomController extends BaseController {

    @Autowired
    RoomService roomService;

    @Autowired
    HotelService hotelService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, value = "/rooms/{hotelId}")
    public ModelAndView getAllRoomsOfSpecifiedHotel(@PathVariable("hotelId") long hotelId, HttpServletRequest req) throws ServletException, IOException {
        HotelTO hotel = hotelService.find(hotelId);
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "index");

        if (hotel == null) {
            modelAndView.addObject("error", messageSource.getMessage("hotel.not.found.id", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        List<RoomTO> rooms = roomService.findAll(hotel);

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "roomList");
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("hotel", hotel);
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/room/{hotelId}/{roomId}")
    public ModelAndView editSpecifiedRoomForm(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") long roomId, HttpServletRequest req) throws ServletException, IOException {
        HotelTO hotel = hotelService.find(hotelId);
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "index");

        if (hotel == null) {
            modelAndView.addObject("error", messageSource.getMessage("hotel.not.found.id", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        RoomTO room = roomService.find(roomId);
        if (room == null) {
            modelAndView.addObject("error", messageSource.getMessage("room.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "roomEdit");
        modelAndView.addObject("room", room);
        modelAndView.addObject("hotel", hotel);

        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, value = "/room/{hotelId}/{roomId}")
    public ModelAndView editSpecifiedRoomSubmit(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") long roomId, @Valid RoomTO room, BindingResult result, HttpServletRequest req) throws ServletException, IOException {
        HotelTO hotel = hotelService.find(hotelId);
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "index");

        if (hotel == null) {
            modelAndView.addObject("error", messageSource.getMessage("hotel.not.found.id", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        RoomTO roomFromDB = roomService.find(roomId);
        if (roomFromDB == null) {
            modelAndView.addObject("error", messageSource.getMessage("room.notfound", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "roomEdit");
        try {
            roomFromDB.setPrice(room.getPrice());
            roomFromDB.setNumber(room.getNumber());
            roomFromDB.setBedsCount(room.getBedsCount());

            roomService.update(roomFromDB);

            modelAndView.addObject("room", roomFromDB);
            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));
        } catch (Exception ex) {
            modelAndView.addObject("room", room);
            modelAndView.addObject("error", messageSource.getMessage("general.error", null, LocaleContextHolder.getLocale()));
        }

        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/newroom/{hotelId}")
    public ModelAndView createRoomForm(@PathVariable("hotelId") long hotelId, HttpServletRequest req) throws ServletException, IOException {
        HotelTO hotel = hotelService.find(hotelId);
        ModelAndView modelAndView = new ModelAndView(getLayoutUrlPrefix(req) + "index");

        if (hotel == null) {
            modelAndView.addObject("error", messageSource.getMessage("hotel.not.found.id", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }

        modelAndView.setViewName(getLayoutUrlPrefix(req) + "roomEdit");
        modelAndView.addObject("room", new RoomTO());
        modelAndView.addObject("hotel", hotel);

        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.POST, value = "/newroom/{hotelId}")
    public String createRoomSubmit(@PathVariable("hotelId") long hotelId, @Valid @ModelAttribute("roomTo") RoomTO room, BindingResult result) throws ServletException, IOException {
        HotelTO hotel = hotelService.find(hotelId);
        if (hotel == null) {
            return "redirect:/";
        }

        room.setHotel(hotel);
        hotel.addRoom(room);

        roomService.create(room);

        return "redirect:/room/" + hotelId + "/" + room.getId();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, value = "/deleteroom/{hotelId}/{roomId}")
    public String deleteSpecifiedRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") long roomId) throws ServletException, IOException {
        HotelTO hotel = hotelService.find(hotelId);
        if (hotel == null) {
            return "redirect:/";
        }

        RoomTO room = roomService.find(roomId);
        if (room == null) {
            return "redirect:/";
        }

        try {
            roomService.delete(room);
        } catch (Exception ex) {
            return "redirect:/";
        }

        return "redirect:/rooms/" + hotelId;
    }
}