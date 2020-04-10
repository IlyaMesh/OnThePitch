package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.UserToUserResult;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.service.LogService;
import com.onthepitch.backend.service.serviceImpl.UserService;
import com.onthepitch.shared.model.LogResult;
import com.onthepitch.shared.model.MessageResponse;
import com.onthepitch.shared.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    //TODO do smth with same code pieces and preauthorize
    @GetMapping("/admin/users")
    public List<UserResult> getAllUsers(){
        List<User> all = userRepo.findAll();
        List<UserResult> result = new ArrayList<>();
        for(User user: all){
            result.add(userToUserResult.convert(user));
        }
        return result;
    }

    @GetMapping("/admin/users/{id}/promote")
    public ResponseEntity<?> promote(@PathVariable("id") String id){
        User user = userRepo.findById(Long.parseLong(id)).orElse(null);
        if(user == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User with that id didnt exists"));
        }
        userService.promote(user);

        return ResponseEntity.ok(new MessageResponse("User promoted successfully!"));
    }

    @GetMapping("/admin/users/{id}/demote")
    public ResponseEntity<?> demote(@PathVariable("id") String id){
        User user = userRepo.findById(Long.parseLong(id)).orElse(null);
        if(user == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User with that id didnt exists"));
        }
        userService.demote(user);
        return ResponseEntity.ok(new MessageResponse("User demoted successfully!"));
    }

    @GetMapping("/admin/users/{id}/ban")
    public ResponseEntity<?> ban(@PathVariable("id") String id){
        User user = userRepo.findById(Long.parseLong(id)).orElse(null);
        if(user == null){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("User with that id didnt exists"));
        }
        userService.ban(user);
        return ResponseEntity.ok(new MessageResponse("User banned!"));
    }

    @GetMapping("/admin/logs")
    public List<LogResult> getLogs(){
        return logService.getLogs();
    }
}
