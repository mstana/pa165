package cz.muni.fi.pa165.bookingmanager.controllers;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.ReservationTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.RoomTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.HotelService;
import cz.muni.fi.pa165.bookingmanager.api.services.ReservationService;
import cz.muni.fi.pa165.bookingmanager.api.services.RoomService;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

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

        if(language.equals("cs")) {
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString("cs"));
        } else {
            localeResolver.setLocale(request, response, StringUtils.parseLocaleString("en"));
        }

        setDefaultValues();

        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "home")
    public String Home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        setDefaultValues();

        return "index";
    }


    public void setDefaultValues () {
        for (HotelTO hotel : hotelService.findAll()) {
            hotelService.delete(hotel);
        }

        HotelTO hotelTO = new HotelTO();
        hotelTO.setAddress("Lidická 6, Brno");
        hotelTO.setName("BobyCentrum");

        hotelService.create(hotelTO);

        RoomTO room1 = new RoomTO();
        room1.setBedsCount(4);
        room1.setNumber("404");
        room1.setPrice(451);
        room1.setHotel(hotelTO);

        RoomTO room2 = new RoomTO();
        room2.setBedsCount(6);
        room2.setNumber("408");
        room2.setPrice(411);
        room2.setHotel(hotelTO);

        RoomTO room3 = new RoomTO();
        room3.setBedsCount(9);
        room3.setNumber("4044");
        room3.setPrice(41);
        room3.setHotel(hotelTO);

        if (hotelTO.getRooms() == null) {
            List<RoomTO> rooms = new ArrayList<>();
            rooms.add(room1);
            rooms.add(room2);
            rooms.add(room3);
            hotelTO.setRooms(rooms);
        }

        roomService.create(room1);
        roomService.create(room2);
        roomService.create(room3);

        HotelTO hotelTO2 = new HotelTO();
        hotelTO2.setAddress("Hrcirska 10, Brno");
        hotelTO2.setName("Hotel Slovan");
        hotelService.create(hotelTO2);

        HotelTO hotelTO3 = new HotelTO();
        hotelTO3.setAddress("Masarykova 1, Brno");
        hotelTO3.setName("Hotel Continental");
        hotelService.create(hotelTO3);

        for (UserTO user : userService.findAll()) {
            userService.delete(user);
        }

        UserTO u1 = new UserTO();
        u1.setFirstName("Marek");
        u1.setLastName("Stana");
        u1.setEmail("marek.stana@ms.com");
        u1.setAdmin(Boolean.TRUE);
        userService.create(u1);

        UserTO u2 = new UserTO();
        u2.setFirstName("David");
        u2.setLastName("Kadlec");
        u2.setEmail("david.kadlec@dk.com");
        u2.setAdmin(Boolean.FALSE);
        userService.create(u2);

        UserTO u3 = new UserTO();
        u3.setFirstName("Adam");
        u3.setLastName("Studenic");
        u3.setEmail("adam.studenic@as.com");
        u3.setAdmin(Boolean.FALSE);
        userService.create(u3);
        
        
        UserTO u4 = new UserTO();
        u4.setFirstName("Ondrej");
        u4.setLastName("Pavelka");
        u4.setEmail("ondrej.pavelka@op.com");
        u4.setAdmin(Boolean.FALSE);
        userService.create(u4);

        ReservationTO res = new ReservationTO();
        res.setRoom(room1);
        res.setUser(u1);

        Calendar beginDate = Calendar.getInstance();
        beginDate.set(2014, 10, 1);

        Calendar endDate = Calendar.getInstance();
        endDate.set(2014, 11, 1);

        res.setBeginDate(beginDate.getTime());
        res.setEndDate(endDate.getTime());

        reservationService.create(res);

    }
}
