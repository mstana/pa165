/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.managers;

import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author mstana
 */
@Repository
@Transactional
public class UserManager implements UserDAO{

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
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        User u = entityManager.merge(user);
        entityManager.remove(user);
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
        Expression<Long> param1 = criteriaBuilder.parameter(Long.class);
        query.select(c).where(criteriaBuilder.equal(c.get("user_id").as(Long.class),param1));

        TypedQuery<User> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList();
        //return em.createQuery("SELECT u FROM User u WHERE u.user = :user", User.class).setParameter("user", user).getResultList();
    }
    
    
}
