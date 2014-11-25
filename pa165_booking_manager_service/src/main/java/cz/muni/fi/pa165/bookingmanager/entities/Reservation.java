package cz.muni.fi.pa165.bookingmanager.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private Room room;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date beginDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    private User user;

    public Reservation() {
    
    }
    
    public Reservation(Room room, Date beginDate, Date endDate) {
        this.room = room;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        if ((endDate != null) && (beginDate != null) && !endDate.after(beginDate)) {
            throw new IllegalArgumentException("BeginDate cannot be after endDate");
        }
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        if ((endDate != null) && (beginDate != null) && beginDate.after(endDate)) {
            throw new IllegalArgumentException("EndDate cannot be before beginDate");
        }
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
