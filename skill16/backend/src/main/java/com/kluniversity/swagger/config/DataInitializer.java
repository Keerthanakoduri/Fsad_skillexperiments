package com.kluniversity.swagger.config;

import com.kluniversity.swagger.model.Student;
import com.kluniversity.swagger.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StudentRepository repo;

    @Override
    public void run(String... args) {
        repo.save(new Student("Ravi Kumar",    "ravi@klu.ac.in",    "B.Tech CSE"));
        repo.save(new Student("Priya Sharma",  "priya@klu.ac.in",   "B.Tech ECE"));
        repo.save(new Student("Arun Reddy",    "arun@klu.ac.in",    "B.Tech IT"));
        repo.save(new Student("Sneha Patel",   "sneha@klu.ac.in",   "B.Tech AI&DS"));
        repo.save(new Student("Kiran Babu",    "kiran@klu.ac.in",   "M.Tech CSE"));
        System.out.println("Sample students seeded. Open Swagger UI at: http://localhost:8080/swagger-ui.html");
    }
}
