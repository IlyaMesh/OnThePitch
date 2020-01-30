package com.onthepitch.backend.controllers;

import com.onthepitch.backend.dao.UserRepo;
import com.onthepitch.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class RegistrationController {
    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

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
        user.setIs_moderator(false);
        userRepo.save(user);
        return "redirect:/login";
    }
}
