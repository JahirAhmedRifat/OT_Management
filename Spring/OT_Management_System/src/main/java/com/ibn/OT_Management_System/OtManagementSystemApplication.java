package com.ibn.OT_Management_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication
public class OtManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtManagementSystemApplication.class, args);

	}

}

