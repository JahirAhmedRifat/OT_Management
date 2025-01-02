package com.ibn.OT_Management_System.service;

import com.ibn.OT_Management_System.DTO.AuthResponse;
import com.ibn.OT_Management_System.entity.User;
import com.ibn.OT_Management_System.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String generateJwtToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public AuthResponse login(User user){
       User user1 = this.userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username"));

        // Check if the entered password matches the stored (hashed) password
        if (!passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        String jwtToken = this.generateJwtToken(user.getUsername());
        return new AuthResponse(jwtToken);
    }



}
