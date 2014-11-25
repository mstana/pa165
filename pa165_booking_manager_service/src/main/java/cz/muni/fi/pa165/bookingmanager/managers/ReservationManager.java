package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.ReservationDAO;
import cz.muni.fi.pa165.bookingmanager.entities.Reservation;
import cz.muni.fi.pa165.bookingmanager.entities.Room;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author: Ondřej Pavelka <pavelka@cesnet.cz>
 */
@Repository
public class ReservationManager implements ReservationDAO {

    @Autowired
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Reservation reservation) {
        entityManager.persist(reservation);
    }

    @Override
    public void update(Reservation reservation) {
        entityManager.merge(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        Reservation r = entityManager.merge(reservation);
        entityManager.remove(r);
    }

    @Override
    public Reservation find(Long id) {
        return entityManager.find(Reservation.class, id);
    }

    @Override
    public List<Reservation> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> c = query.from(Reservation.class);
        query.select(c);

        TypedQuery<Reservation> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();    }

    @Override
    public List<Reservation> findAll(Room room) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> from = query.from(Reservation.class);
        javax.persistence.criteria.Predicate param1 = criteriaBuilder.equal(from.get("room").get("id").as(Long.class), room.getId());
        query.select(from).where(param1);

        TypedQuery<Reservation> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }

    @Override
    public List<Reservation> findAll(User user) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reservation> query = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> from = query.from(Reservation.class);
        javax.persistence.criteria.Predicate param1 = criteriaBuilder.equal(from.get("user").get("id").as(Long.class), user.getId());
        query.select(from).where(param1);

        TypedQuery<Reservation> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
    }
}