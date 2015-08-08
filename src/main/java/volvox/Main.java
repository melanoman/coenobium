package volvox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import volvox.repository.TextRepository;

@SpringBootApplication
public class Main {

    @Autowired
    TextRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
