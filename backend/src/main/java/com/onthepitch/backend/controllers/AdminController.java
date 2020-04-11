package com.onthepitch.backend.controllers;

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

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    private UserService userService;
    private LogService logService;

    @Autowired
    public AdminController(UserService userService,LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    //TODO preauthorize
    @GetMapping("/admin/users")
    public List<UserResult> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/admin/users/{id}/promote")
    public ResponseEntity<?> promote(@PathVariable("id") String id){
        userService.promote(Long.parseLong(id));
        return ResponseEntity.ok(new MessageResponse("User promoted successfully!"));
    }

    @GetMapping("/admin/users/{id}/demote")
    public ResponseEntity<?> demote(@PathVariable("id") String id){
        userService.demote(Long.parseLong(id));
        return ResponseEntity.ok(new MessageResponse("User demoted successfully!"));
    }

    @GetMapping("/admin/users/{id}/ban")
    public ResponseEntity<?> ban(@PathVariable("id") String id){
        userService.ban(Long.parseLong(id));
        return ResponseEntity.ok(new MessageResponse("User banned!"));
    }

    @GetMapping("/admin/logs")
    public List<LogResult> getLogs(){
        return logService.getLogs();
    }
}
