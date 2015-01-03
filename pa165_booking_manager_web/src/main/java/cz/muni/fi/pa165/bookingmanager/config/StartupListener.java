package cz.muni.fi.pa165.bookingmanager.config;

import cz.muni.fi.pa165.bookingmanager.api.dto.UserTO;
import cz.muni.fi.pa165.bookingmanager.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserService userService;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!userExists("rest")) {
            userService.create(createUser("rest", true));
        }
        if (!userExists("admin")) {
            userService.create(createUser("admin", true));
        }
    }

    private boolean userExists(String username) {
        for (UserTO u : userService.findAll()){
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private UserTO createUser(String all, boolean isAdmin){
        UserTO user = new UserTO();
        user.setUsername(all);
        user.setPassword(all);
        user.setUsername(all);
        user.setLastName(all);
        user.setFirstName(all);
        user.setEmail(all + "@" + all + "." + all);
        user.setAdmin(isAdmin);
        return user;
    }
}
