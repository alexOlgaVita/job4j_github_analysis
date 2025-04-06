package ru.job4j;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@SpringBootApplication
@AllArgsConstructor
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
