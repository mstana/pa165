package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by David on 25. 11. 2014.
 */
@Controller
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    HotelService hotelService;

    @RequestMapping(method= RequestMethod.GET, value="/rooms")
    public ModelAndView getAllRooms() throws ServletException, IOException
    {
        List<RoomTO> rooms = roomService.findAll();

        ModelAndView modelAndView = new ModelAndView("roomList");
        modelAndView.addObject("rooms", rooms);

        return modelAndView;
    }

    @RequestMapping(method= RequestMethod.GET, value="/rooms/{hotelId}")
    public ModelAndView getAllRoomsOfSpecifiedHotel(@PathVariable("hotelId") long hotelId) throws ServletException, IOException
    {
        HotelTO hotel = hotelService.find(hotelId);

        if (hotel == null) {
            return new ModelAndView("index");
        }

        List<RoomTO> rooms = roomService.findAll(hotel);

        ModelAndView modelAndView = new ModelAndView("roomList");
        modelAndView.addObject("rooms", rooms);

        return modelAndView;
    }


    @RequestMapping(method= RequestMethod.GET, value="/room/{hotelId}/{roomId}")
    public ModelAndView editSpecifiedRoomForm(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") long roomId) throws ServletException, IOException
    {
        HotelTO hotel = hotelService.find(hotelId);
        if (hotel == null)
        {
            return new ModelAndView("index");
        }

        RoomTO room = roomService.find(roomId);
        if (room == null) {
            return new ModelAndView("index");
        }

        ModelAndView modelAndView = new ModelAndView("roomEdit");
        modelAndView.addObject("room", room);

        return modelAndView;
    }


    @RequestMapping(method= RequestMethod.POST, value="/room/{hotelId}/{roomId}")
    public ModelAndView editSpecifiedRoomSubmit(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") long roomId, @ModelAttribute("RoomTO")RoomTO room) throws ServletException, IOException
    {
        HotelTO hotel = hotelService.find(hotelId);
        if (hotel == null)
        {
            return new ModelAndView("index");
        }

        RoomTO roomFromDB = roomService.find(roomId);
        if (roomFromDB == null) {
            return new ModelAndView("index");
        }

        roomFromDB.setPrice(room.getPrice());
        roomFromDB.setNumber(room.getNumber());
        roomFromDB.setBedsCount(room.getBedsCount());

        roomService.update(roomFromDB);

        ModelAndView modelAndView = new ModelAndView("roomEdit");
        modelAndView.addObject("room", roomFromDB);
        modelAndView.addObject("message", "All right!");


        return modelAndView;
    }


    @RequestMapping(method= RequestMethod.GET, value="/newroom/{hotelId}")
    public ModelAndView createRoomForm(@PathVariable("hotelId") long hotelId) throws ServletException, IOException
    {
        HotelTO hotel = hotelService.find(hotelId);
        if (hotel == null)
        {
            return new ModelAndView("index");
        }

        ModelAndView modelAndView = new ModelAndView("roomEdit");
        modelAndView.addObject("room", new RoomTO());

        return modelAndView;
    }


    @RequestMapping(method= RequestMethod.POST, value="/newroom/{hotelId}")
    public String createRoomSubmit(@PathVariable("hotelId") long hotelId, @ModelAttribute("RoomTO")RoomTO room) throws ServletException, IOException
    {
        HotelTO hotel = hotelService.find(hotelId);
        room.setHotel(hotel);
        hotel.addRoom(room);

        // Zde to pada, problem se persistem detached objektu, reseni sem za 5 minut nenasel, tak sem to zatim nechal
        roomService.create(room);

        return "redirect:/room/" + hotelId + "/" + room.getId();
    }
}
