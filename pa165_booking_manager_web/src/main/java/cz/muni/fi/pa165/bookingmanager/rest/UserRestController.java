package cz.muni.fi.pa165.bookingmanager.rest;

import cz.muni.fi.pa165.bookingmanager.rest.dto.UserRestTO;
import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import cz.muni.fi.pa165.bookingmanager.exceptions.BadRequestException;
import cz.muni.fi.pa165.bookingmanager.exceptions.NotFoundException;
import org.apache.commons.beanutils.BeanUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.enterprise.deploy.spi.exceptions.TargetException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@RestController
@RequestMapping("/rest/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<UserTO> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserTO findOne(@PathVariable("userId") long userId) {
        UserTO user;

        try {
            user = userService.find(userId);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public String create(@RequestBody UserTO user) {
        try {
            userService.create(user);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }
        return "created";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String update(@RequestBody UserTO user) {
        try {
            userService.update(user);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        return "updated";
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("userId") long userId) {
        UserTO user;

        try {
            user = userService.find(userId);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        if (user == null) {
            throw new NotFoundException();
        }

        try {
            userService.delete(user);
        } catch (IllegalArgumentException ex) {
            throw new BadRequestException();
        }

        return "deleted";
    }
}
