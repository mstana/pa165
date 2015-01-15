/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.controllers;

import com.fasterxml.jackson.databind.deser.Deserializers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


/**
 *
 * @author David Kadlec
 */
@Controller
public class ErrorController extends BaseController{
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403(HttpServletRequest request) {
        return getLayoutUrlPrefix(request)+"403";
    }
    
    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public String error404(HttpServletRequest request) {
        return getLayoutUrlPrefix(request)+"404";
    }
}
