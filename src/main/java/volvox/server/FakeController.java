package volvox.server;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import volvox.beans.Text;

@RestController
public class FakeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/ping")
    public Text ping(@RequestParam(value="name", defaultValue="World") String name) {
        return new Text(counter.incrementAndGet(), String.format(template, name));
    }
}
