package com.onthepitch.backend.controllers;

import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.model.Role;
import com.onthepitch.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public void setUserRepo(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.put("message","User exists");
            return "registration";
        }

        user.setActive(true);
        //user.setUser_password(passwordEncoder.encode(user.getUser_password()));
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
