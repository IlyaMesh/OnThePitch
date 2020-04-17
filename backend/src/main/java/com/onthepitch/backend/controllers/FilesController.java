package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.serviceImpl.CloudinaryService;
import com.onthepitch.backend.service.serviceImpl.UserService;
import com.onthepitch.shared.model.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
public class FilesController {

    private UserService userService;
    private CloudinaryService cloudinaryConfig;

    @Autowired
    public FilesController(UserService userService, CloudinaryService cloudinaryService) {
        this.userService = userService;
        this.cloudinaryConfig = cloudinaryService;
    }

    @PostMapping("/upload")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        //photoStorageService.save(file);
        String url = cloudinaryConfig.uploadFile(file);
        userService.updatePhoto(UserName, url);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Uploaded the photo successfully"));

    }
}
