package pl.patrykkawula.linguapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class LinguAppApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(LinguAppApplication.class, args);
        LinguController linguController = contex.getBean(LinguController.class);
        linguController.mainLoop();
    }

    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }
}
