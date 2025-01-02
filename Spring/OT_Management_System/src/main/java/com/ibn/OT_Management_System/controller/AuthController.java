package com.ibn.OT_Management_System.controller;

import com.ibn.OT_Management_System.DTO.AuthResponse;
import com.ibn.OT_Management_System.DTO.PasswordChangeRequest;
import com.ibn.OT_Management_System.entity.User;
import com.ibn.OT_Management_System.repository.UserRepository;
import com.ibn.OT_Management_System.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            AuthResponse authResponse = this.authService.login(user);
            String jwtToken = authResponse.getToken();
            return ResponseEntity.ok(new AuthResponse(jwtToken,"login successful",true));
        }catch (Exception ex){
            return ResponseEntity.ok(new AuthResponse("login failed",false));
        }

    }


    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest request) {
        try {
            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);

            return ResponseEntity.ok(new AuthResponse("Password changed successfully!",true));
        }catch (Exception ex){
            return ResponseEntity.ok(new AuthResponse("Password change failed!",false));
        }

    }



}

