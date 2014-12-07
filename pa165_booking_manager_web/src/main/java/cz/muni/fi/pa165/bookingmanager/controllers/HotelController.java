package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.validators.HotelValidator;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adam Studenic
 */
@Controller
public class HotelController {

    @Autowired
    HotelService hotelService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET, value = "/hotels")
    public ModelAndView handleRequest() throws ServletException, IOException {
        List<HotelTO> hotels = hotelService.findAll();

        ModelAndView modelAndView = new ModelAndView("hotelList");
        modelAndView.addObject("hotels", hotels);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hotel/{hotelId}")
    public ModelAndView editHotelForm(@PathVariable("hotelId") long hotelId) throws ServletException, IOException {
        HotelTO hotel = hotelService.find(hotelId);
        if (hotel == null) {
            return new ModelAndView("hotelList");
        }

        ModelAndView modelAndView = new ModelAndView("hotelEdit");
        modelAndView.addObject("hotel", hotel);

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hotel/{hotelId}")
    public ModelAndView editHotelSubmit(@PathVariable("hotelId") long hotelId, @Valid @ModelAttribute("hotelTo") HotelTO hotel, BindingResult result) throws ServletException, IOException { 
        
        HotelValidator hotelValidator = new HotelValidator();
        HotelTO hotelFromDB = hotelService.find(hotelId);
        ModelAndView modelAndView = new ModelAndView("hotelEdit");
        if (hotelFromDB == null) {
            modelAndView.addObject("error", messageSource.getMessage("hotel.not.found.id", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }
        hotelValidator.validate(hotel, result);
        modelAndView.addObject("hotel", hotelFromDB);
        if (result.hasErrors()) {
            modelAndView.addObject("error", messageSource.getMessage("hotel.error.edit" , null, LocaleContextHolder.getLocale()));
            return modelAndView;
            
        } else {
            hotelFromDB.setName(hotel.getName());
            hotelFromDB.setAddress(hotel.getAddress());
            hotelService.update(hotelFromDB);

            modelAndView.addObject("hotel", hotelFromDB);
            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));

            return modelAndView;
    
          }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/newhotel")
    public ModelAndView createHotelForm() throws ServletException, IOException {

        ModelAndView modelAndView = new ModelAndView("hotelEdit");
        modelAndView.addObject("hotel", new HotelTO());
        
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/newhotel")
    public ModelAndView createHotelSubmit(@Valid @ModelAttribute("hotelTo") HotelTO hotel, BindingResult result,HttpServletRequest req) throws ServletException, IOException {
        
        HotelValidator hotelValidator = new HotelValidator();
        ModelAndView modelAndView = new ModelAndView("hotelEdit");
        hotelValidator.validate(hotel, result);
        if (result.hasErrors()) {
            modelAndView.addObject("error", messageSource.getMessage("hotel.error.create" , null, LocaleContextHolder.getLocale()));
            return modelAndView;
        } else {
            hotelService.create(hotel);
            modelAndView.addObject("ok", messageSource.getMessage("general.ok", null, LocaleContextHolder.getLocale()));
            return modelAndView;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/deletehotel/{hotelId}")
    public String deleteHotel(@PathVariable("hotelId") long hotelId, HttpServletRequest req) throws ServletException, IOException {

        HotelTO hotel = hotelService.find(hotelId);
        if (hotel == null) {
            return "redirect:" + req.getContextPath() + "/";
        }

        try {
            hotelService.delete(hotel);
        } catch (Exception ex) {
            return "redirect:/hotels";
        }

        return "redirect:/hotels";
    }

}
