package com.example.jpasecurity;

import com.example.jpasecurity.entity.Role;
import com.example.jpasecurity.entity.User;
import com.example.jpasecurity.repository.PostRepository;
import com.example.jpasecurity.repository.RoleRepository;
import com.example.jpasecurity.repository.UserRepository;
import com.example.jpasecurity.entity.Post;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class JpaSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PostRepository posts, UserRepository users, RoleRepository roles,
                                        PasswordEncoder encoder) {
        return args -> {
            Role roleUser = roles.save(new Role("USER"));
            Role roleAdmin = roles.save(new Role("ADMIN"));
            users.save(new User("user@example.com", encoder.encode("password"), Set.of(new Role[]{roleUser})));
            users.save(new User("admin@example.com", encoder.encode("password"), Set.of(new Role[]{roleUser, roleAdmin})));
            posts.save(new Post("Hello, World!", "Welcome to my new blog!", "Dan Vega"));
        };
    }
}
