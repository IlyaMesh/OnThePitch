/*
 * Copyright (c)
 */

package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.exсeption.EmailIsAlreadyExists;
import com.onthepitch.backend.exсeption.UsernameIsAlreadyTaken;
import com.onthepitch.backend.model.Role;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.security.jwt.JwtUtils;
import com.onthepitch.backend.service.AuthService;
import com.onthepitch.shared.model.request.SignupRequest;
import com.onthepitch.shared.model.response.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final JwtUtils jwtUtils;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(JwtUtils jwtUtils,
                           UserRepo userRepo,
                           PasswordEncoder passwordEncoder) {
        this.jwtUtils = jwtUtils;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public JwtResponse authUser(Authentication authentication) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm", Locale.UK);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        return new JwtResponse(
            jwt,
            userDetails.getUser_id(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            userDetails.getUser_pic(),
            simpleDateFormat.format(userDetails.getRegistration_time()),
            roles,
            userDetails.getClub_id()
        );
    }

    @Override
    public void regisrateUser(SignupRequest signUpRequest) {
        if (userRepo.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameIsAlreadyTaken();
        }
        if (userRepo.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailIsAlreadyExists();
        }
        // Create new user's account
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setUser_password(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setClub_id(Long.parseLong(signUpRequest.getClub_id()));
        Set<Role> userRole = Collections.singleton(Role.USER);
        user.setRoles(userRole);
        user.setActive(true);
        user.setRegistration_time(new Date());
        userRepo.save(user);
    }
}
