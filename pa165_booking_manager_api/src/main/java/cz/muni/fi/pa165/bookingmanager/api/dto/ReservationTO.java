package cz.muni.fi.pa165.bookingmanager.api.dto;

import java.util.Date;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
public class ReservationTO {
    private Long id;

    private RoomTO room;

    private Date beginDate;

    private Date endDate;

    private UserTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomTO getRoom() {
        return room;
    }

    public void setRoom(RoomTO room) {
        this.room = room;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public UserTO getUser() {
        return user;
    }

    public void setUser(UserTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationTO that = (ReservationTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}