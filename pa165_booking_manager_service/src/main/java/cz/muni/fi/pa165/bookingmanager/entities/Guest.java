package cz.muni.fi.pa165.bookingmanager.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Adam Studenic
 */
@Entity
@DiscriminatorValue("0")
public class Guest extends User{
    public Guest () {
        super();
    }

}
