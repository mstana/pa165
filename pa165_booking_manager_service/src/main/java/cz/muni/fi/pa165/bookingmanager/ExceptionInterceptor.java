package cz.muni.fi.pa165.bookingmanager;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.stereotype.Service;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@Aspect
@Service
public class ExceptionInterceptor {
    @AfterThrowing(pointcut = "execution(* cz.muni.fi.pa165.bookingmanager.dao..*(..)) || execution(* cz.muni.fi.pa165.bookingmanager.entities..*(..)) || execution(* cz.muni.fi.pa165.bookingmanager.managers..*(..))", throwing = "ex")
    public void errorInterceptor(Exception ex) {
        //logger here
        if (ex instanceof RuntimeException) {
            throw new BookingDataAccessException("Runtime exception thrown on data layer: ", ex);
        } else {
            //TODO: handle somehow
            throw new BookingDataAccessException("Exception on data layer:", ex);
        }
    }

}