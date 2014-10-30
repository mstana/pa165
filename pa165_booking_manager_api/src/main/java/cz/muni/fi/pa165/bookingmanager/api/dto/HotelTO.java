package cz.muni.fi.pa165.bookingmanager.api.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mstana
 */

public class HotelTO {
      private Long id;

    private String name;

    private String address;

    private List<RoomTO> rooms = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<RoomTO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomTO> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final HotelTO other = (HotelTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    public void addRoom (RoomTO rooom) {
        this.rooms.add(rooom);
    }
  
}
