package volvox.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import volvox.repository.PersonRepository;

@RestController
public class AccountController {
    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/login2")
    public String login(@RequestParam(value="hash")String hash) {
        return "Permission denied"; //TODO replace this with spring security
    }
}
