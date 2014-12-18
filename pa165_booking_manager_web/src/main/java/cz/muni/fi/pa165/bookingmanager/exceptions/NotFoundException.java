package cz.muni.fi.pa165.bookingmanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author David Kadlec
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Object not found")
public class NotFoundException extends RuntimeException {
}
