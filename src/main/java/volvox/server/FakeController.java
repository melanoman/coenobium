package volvox.server;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import volvox.beans.Text;
import volvox.repository.TextRepository;

@RestController
public class FakeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private TextRepository textRepository;

    @RequestMapping("/ping")
    public Text ping(@RequestParam(value="name", defaultValue="World") String name) {
        Text text = new Text(String.format(template, name));
        textRepository.save(text);
        return text;
    }
}
