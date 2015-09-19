package volvox.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import volvox.beans.User;
import volvox.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    RoomService roomService;

    @Autowired
    UserRepository userRepository;

    public LogoutHandler() {
        this.setDefaultTargetUrl("/logout");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userRepository.findUserByName(authentication.getName());
        if (user != null) roomService.exitAllRooms(user.getId());
        super.onLogoutSuccess(request, response, authentication);
    }
}
