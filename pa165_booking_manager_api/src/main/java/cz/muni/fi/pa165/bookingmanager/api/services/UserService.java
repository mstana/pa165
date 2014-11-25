package cz.muni.fi.pa165.bookingmanager.api.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import java.util.List;

/**
 *
 * @author Adam Studenic
 */
public interface UserService {

    /**
     * Creates user.
     *
     * @param user
     */
    public void create(UserTO user);

    /**
     * Updates user.
     *
     * @param user
     */
    public void update(UserTO user);

    /**
     * Deletes user.
     *
     * @param user
     */
    public void delete(UserTO user);

    /**
     * Finds user by id.
     *
     * @param id
     */
    public UserTO find(Long id);

    /**
     * Returns all user.
     */
    public List<UserTO> findAll();


}
