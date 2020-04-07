package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.UserToUserResult;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.shared.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private UserToUserResult userToUserResult;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/admin/users")
    public List<UserResult> getAllUsers(){
        List<User> all = userRepo.findAll();
        List<UserResult> result = new ArrayList<>();
        for(User user: all){
            result.add(userToUserResult.convert(user));
        }
        return result;
    }
}
