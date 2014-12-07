/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.validators;

import cz.muni.fi.pa165.bookingmanager.api.dto.HotelTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mstana
 */
public class HotelValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
      return HotelTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        HotelTO user = (HotelTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "hotel.validation.error.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "hotel.validation.error.lastName");
      
    
    }
}
