package cz.muni.fi.pa165.bookingmanager.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author David Kadlec
 */
@Entity
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int bedsCount;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    private Hotel hotel;

    @OneToMany(mappedBy = "room", cascade = {CascadeType.MERGE,CascadeType.REFRESH}, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        this.price = price;
    }

    public int getBedsCount() {
        return bedsCount;
    }

    public void setBedsCount(int bedsCount) {
        if (bedsCount <= 0) {
            throw new IllegalArgumentException("Room must have at least one bed.");
        }

        this.bedsCount = bedsCount;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        if (hotel == null) {
             throw new IllegalArgumentException("Hotel cannot be null.");
        }

        this.hotel = hotel;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void addReservation (Reservation reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = 9187 * hash + Objects.hashCode(this.id);
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
        final Room other = (Room) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
