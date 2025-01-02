package com.ibn.OT_Management_System.config;

import com.ibn.OT_Management_System.entity.User;
import com.ibn.OT_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserInitializer implements CommandLineRunner {
    @Value("${default.username}")
    private String defaultUsername;

    @Value("${default.password}")
    private String defaultPassword;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername(defaultUsername).isEmpty()) {
            User defaultUser = new User();
            defaultUser.setUsername(defaultUsername);
            defaultUser.setPassword(passwordEncoder.encode(defaultPassword));

            userRepository.save(defaultUser);
            System.out.println("Default user created: " + defaultUsername);
        } else {
            System.out.println("Default user already exists.");
        }
    }

}
