package cz.muni.fi.pa165.bookingmanager.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Adam Studenic
 */
@Entity
@DiscriminatorValue("1")
public class Administrator extends User{
    public Administrator ()  {
        super();
    }
}
