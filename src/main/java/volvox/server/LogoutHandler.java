package volvox.server;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

    //TODO figure out why these won't autowire
    //@Autowire
    //RoomService roomService;
    //@Autowire
    //UserRepository userRepository;

    public LogoutHandler() {
        this.setDefaultTargetUrl("/");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String name = authentication.getName();
        //User user = userRepository.findUserByName(name);
        //if (user != null) roomService.exitAllRooms(user.getId());

        super.onLogoutSuccess(request, response, authentication);
    }
}
