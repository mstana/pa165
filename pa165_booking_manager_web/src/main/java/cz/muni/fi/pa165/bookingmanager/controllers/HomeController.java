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
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {

    @Autowired
    HotelService hotelService;
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "/cs", method = RequestMethod.GET)
    public String changeLocaleCs(HttpServletRequest request, HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response, StringUtils.parseLocaleString("cs"));
        return "index";
    }

    @RequestMapping(value = "/en", method = RequestMethod.GET)
    public String changeLocaleEn(HttpServletRequest request, HttpServletResponse response) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response, StringUtils.parseLocaleString("en"));
        return "index";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String changeLocaleDefault(HttpServletRequest request, HttpServletResponse response) {

        Locale locale = request.getLocale();
        String language = locale.getLanguage();
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        if (language.equals("cs")) {
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString("cs"));
        } else {
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString("en"));
        }

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String Home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return "index";
    }
}
