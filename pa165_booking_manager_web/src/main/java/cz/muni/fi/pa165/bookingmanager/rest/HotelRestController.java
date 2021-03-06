package cz.muni.fi.pa165.bookingmanager.rest;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.exceptions.BadRequestException;
import cz.muni.fi.pa165.bookingmanager.exceptions.NotFoundException;
import cz.muni.fi.pa165.bookingmanager.managers.HotelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.IllegalFormatException;
import java.util.List;

/**
 * @author David Kadlec
 */
@RestController
@RequestMapping("/rest/hotels/")
public class HotelRestController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<HotelTO> findAll() {
        return hotelService.findAll();
    }

    @RequestMapping(value = "/{hotelId}", method = RequestMethod.GET)
    public HotelTO findOne(@PathVariable("hotelId") long hotelId) {
        HotelTO hotel;

        try {
            hotel = hotelService.find(hotelId);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        if (hotel == null) {
            throw new NotFoundException();
        }
        return hotel;
    }


    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String create(@RequestBody HotelTO hotel) {
        if (!validate(hotel)) {
            throw new BadRequestException();
        }
        try {
            hotelService.create(hotel);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        return "created";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String update(@RequestBody HotelTO hotel) {
        if (!validate(hotel)) {
            throw new BadRequestException();
        }
        try {
            hotelService.update(hotel);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        return "updated";
    }

    @RequestMapping(value = "/{hotelId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("hotelId") long hotelId) {
        HotelTO hotel;

        try {
            hotel = hotelService.find(hotelId);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        if (hotel == null) {
            throw new NotFoundException();
        }

        try {
            hotelService.delete(hotel);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        return "deleted";
    }

    private boolean validate(HotelTO hotel)
    {
        if (hotel == null) {
            return false;
        }
        if (hotel.getName() == null || "".equals(hotel.getName())) {
            return false;
        }
        return true;
    }
}
