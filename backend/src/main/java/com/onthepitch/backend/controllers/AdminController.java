package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.LogService;
import com.onthepitch.backend.service.serviceImpl.UserService;
import com.onthepitch.shared.model.LogResult;
import com.onthepitch.shared.model.MessageResponse;
import com.onthepitch.shared.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private UserService userService;
    private LogService logService;

    @Autowired
    public AdminController(UserService userService,LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    @GetMapping("/admin/users")
    public Page<UserResult> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size){
        return userService.getAllUsers(page,size);
    }

    @GetMapping("/admin/users/{id}/promote")
    public ResponseEntity<?> promote(@PathVariable("id") String id){
        userService.promote(Long.parseLong(id));
        logService.addLog(new StringBuffer("Promote user with id").append(id).toString());
        return ResponseEntity.ok(new MessageResponse("User promoted successfully!"));
    }

    @GetMapping("/admin/users/{id}/demote")
    public ResponseEntity<?> demote(@PathVariable("id") String id){
        userService.demote(Long.parseLong(id));
        logService.addLog(new StringBuffer("Demote user with id").append(id).toString());
        return ResponseEntity.ok(new MessageResponse("User demoted successfully!"));
    }

    @GetMapping("/admin/users/{id}/ban")
    public ResponseEntity<?> ban(@PathVariable("id") String id){
        userService.ban(Long.parseLong(id));
        logService.addLog("Ban user");
        return ResponseEntity.ok(new MessageResponse("User banned!"));
    }

    @GetMapping("/admin/logs")
    public Page<LogResult> getLogs(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size){
        return logService.getLogs(page,size);
    }
}
