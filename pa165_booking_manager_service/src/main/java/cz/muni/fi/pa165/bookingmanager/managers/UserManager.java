/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author mstana
 */
@Repository
public class UserManager implements UserDAO {

    @Autowired
    private EntityManager entityManager;

    public UserManager() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager em) {
        entityManager = em;
    }

    @Override
    public void create(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        User u = entityManager.merge(user);
        entityManager.remove(u);
    }

    @Override
    public User find(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> c = query.from(User.class);
        query.select(c);

        TypedQuery<User> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
        //return em.createQuery("SELECT u FROM User u WHERE u.user = :user", User.class).setParameter("user", user).getResultList();
    }


}
