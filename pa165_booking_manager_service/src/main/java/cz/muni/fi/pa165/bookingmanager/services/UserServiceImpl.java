package cz.muni.fi.pa165.bookingmanager.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import cz.muni.fi.pa165.bookingmanager.dao.UserDAO;
import cz.muni.fi.pa165.bookingmanager.entities.User;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Adam Studenic
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Mapper mapper;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    @Override
    public void create(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getId() != null) {
            throw new IllegalArgumentException("User cannot have id already set");
        }
        User userDO = mapper.map(user, User.class);

        userDAO.create(userDO);
        user.setId(userDO.getId());
    }

    @Override
    public void update(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getId() == null || userDAO.find(user.getId()) == null) {
            throw new IllegalArgumentException("Uset does not exist");
        }
        User userDO = mapper.map(user, User.class);

        userDAO.update(userDO);
    }

    @Override
    public void delete(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        User userDO = mapper.map(user, User.class);

        userDAO.delete(userDO);
    }

    @Override
    public UserTO find(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        User userDO = userDAO.find(id);
        if (userDO == null) {
            return null;
        } else {
            return mapper.map(userDO, UserTO.class);
        }
    }

    @Override
    public List<UserTO> findAll() {
        List<User> users = userDAO.findAll();
        List<UserTO> usersTO = new ArrayList<>();
        for (User userDO : users) {
            usersTO.add(mapper.map(userDO, UserTO.class));
        }

        return usersTO;
    }

}
