
package cz.muni.fi.pa165.bookingmanager.api.services;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import java.util.List;

/**
 *
 * @author Adam Studenic
 */
public interface UserService {

    public void create(UserTO user);

    public void update(UserTO user);

    public void delete(UserTO user);

    public UserTO find(Long id);

    public List<UserTO> findAll();

}
