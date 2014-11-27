/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.validators;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mstana
 */
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class aClass) {
      return UserTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserTO user = (UserTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "First name can't be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "Last name can't be blank");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Email can't be blank");
    }
}
