package volvox.server;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import volvox.beanies.Authenticator;
import volvox.beans.User;
import volvox.repository.UserRepository;

import java.util.Set;

@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);
        if (user == null) {
            if (isBuiltIn(username)) return fakeUser(username);
            else throw new UsernameNotFoundException(username);
        }
        return new Authenticator(user);
    }

    Set<String> fakes = Sets.newHashSet("admin", "mel", "rxx");
    /**
     * we start with a fake accounts to make test configuration easier
     * if real accounts with the same name are found they override the fake ones
     */
    private boolean isBuiltIn(String username) {
        return fakes.contains(username);
    }

    private UserDetails fakeUser(String username) {
        User user = new User();
        user.setName(username);
        user.setEmail("root@localhost.com");
        user.setPassword(username);
        user.setId(-1);
        return new Authenticator(user);
    }

    public User createPerson(String username, String password, String email) {
        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);
        //TODO replace this with a human implementation of the bot interface
        user.setClazz(null);
        return userRepository.save(user);
    }
}
