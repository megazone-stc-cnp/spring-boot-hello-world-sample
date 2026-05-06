package com.example.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class HelloController {

    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    private final CounterRepository counterRepository;

    public HelloController(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @GetMapping("/")
    @Transactional
    public String hello() {
        String name = "";
        try {
            Path path = Paths.get("my_name.txt");
            name = Files.readString(path, StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            name = "Unknown";
        }

        counterRepository.incrementByName("page_views");
        Counter counter = counterRepository.findByName("page_views").orElse(null);
        long count = (counter != null) ? counter.getValue() : 0;
        
        log.info("Hello World! " + name + " ( count: " + count + ")");
        return "Hello World! " + name + " ( count: " + count + ")";
    }
}
