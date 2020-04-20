/*
 * Copyright (c)
*/
package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.LogService;
import com.onthepitch.backend.service.serviceImpl.UserService;
import com.onthepitch.shared.model.response.LogResult;
import com.onthepitch.shared.model.response.MessageResponse;
import com.onthepitch.shared.model.response.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final LogService logService;

    /**
     * @param userService
     * @param logService
     */
    @Autowired
    public AdminController(UserService userService, LogService logService) {
        this.userService = userService;
        this.logService = logService;
    }

    /**
     *
     * @param page which page we want to get
     * @param size how many elements we need in this page
     * @return Page of users
     */
    @GetMapping("/admin/users")
    public Page<UserResult> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size) {
        return userService.getAllUsers(page, size);
    }

    /**
     *
     * @param id: which user with that id we want promote
     * @return Message response with info about promotion status
     */
    @GetMapping("/admin/users/{id}/promote")
    public ResponseEntity<?> promote(@PathVariable("id") String id) {
        userService.promote(Long.parseLong(id));
        logService.addLog(new StringBuffer("Promote user with id").append(id).toString());
        return ResponseEntity.ok(new MessageResponse("User promoted successfully!"));
    }

    /**
     *
     * @param id which user with that id we want demote
     * @return Message response with info about demotion status
     */
    @GetMapping("/admin/users/{id}/demote")
    public ResponseEntity<?> demote(@PathVariable("id") String id) {
        userService.demote(Long.parseLong(id));
        logService.addLog(new StringBuffer("Demote user with id").append(id).toString());
        return ResponseEntity.ok(new MessageResponse("User demoted successfully!"));
    }

    /**
     *
     * @param id which user with that id we want ban
     * @return Message response with info about ban
     */
    @GetMapping("/admin/users/{id}/ban")
    public ResponseEntity<?> ban(@PathVariable("id") String id) {
        userService.ban(Long.parseLong(id));
        logService.addLog("Ban user");
        return ResponseEntity.ok(new MessageResponse("User banned!"));
    }

    /**
     *
     * @param page which page we want to get
     * @param size how many elements we need in this page
     * @return Page of logs
     */
    @GetMapping("/admin/logs")
    public Page<LogResult> getLogs(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size) {
        return logService.getLogs(page, size);
    }
}
