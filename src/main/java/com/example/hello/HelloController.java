package com.example.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        String name = "";
        try {
            Path path = Paths.get("my_name.txt");
            name = Files.readString(path, StandardCharsets.UTF_8).trim();
        } catch (IOException e) {
            name = "Unknown";
        }
        return "Hello World! " + name;
    }
}