/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bookingmanager.dao;

import cz.muni.fi.pa165.bookingmanager.entities.User;

import java.util.List;

/**
 * @author mstana
 */
public interface UserDAO {

    public void create(User user);

    public void update(User user);

    public void delete(User user);

    public User find(Long id);

    public List<User> findAll();
}
