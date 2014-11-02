package cz.muni.fi.pa165.bookingmanager.api.dto;

import java.util.Objects;

/**
 *
 * @author David Kadlec
 */
public class RoomTO {

    private Long id;

    private String number;

    private int price;

    private int bedsCount;
    
    private HotelTO hotel;

    public HotelTO getHotel() {
        return hotel;
    }

    public void setHotel(HotelTO hotel) {
        this.hotel = hotel;
    }
    
    
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

    @Override
    public int hashCode() {
        int hash = 37;
        hash = 9187 * hash + Objects.hashCode(this.id);
        hash = 9187 * hash + Objects.hashCode(this.bedsCount);
        hash = 9187 * hash + Objects.hashCode(this.number);
        hash = 9187 * hash + Objects.hashCode(this.price); 
        hash = 9187 * hash + Objects.hashCode(this.hotel);
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
        final RoomTO other = (RoomTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.bedsCount, other.bedsCount)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        /*if (!Objects.equals(this.hotel, other.hotel)) {
            return false;
        }*/
        return Objects.equals(this.number, other.number);
    }

    @Override
    public String toString() {
        return "Room {" + "id=" + id + ", number=" + number + ", price=" + price + ", bedsCount=" + bedsCount + '}';
    }
}
