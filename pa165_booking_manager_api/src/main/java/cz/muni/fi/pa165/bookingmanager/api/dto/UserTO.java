package cz.muni.fi.pa165.bookingmanager.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Adam Studenic
 */
public class UserTO {

    private Long id;

    private String firstName;

    private String lastName;
    
    private String email;
    
    private Boolean isAdmin;

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    private List<ReservationTO> reservations = new ArrayList<>();

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public UserTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ReservationTO> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationTO> reservations) {
        this.reservations = reservations;
    }

    public void addReservation (ReservationTO reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public int hashCode() {
        int hash = 1337;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserTO other = (UserTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserTO{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }


}
