package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.CloudinaryService;
import com.onthepitch.backend.service.PhotoStorageService;
import com.onthepitch.backend.service.UserService;
import com.onthepitch.shared.model.PhotoResponse;
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
    @Autowired
    private PhotoStorageService photoStorageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CloudinaryService cloudinaryConfig;

    @PostMapping("/upload")
    public ResponseEntity<PhotoResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();

        //photoStorageService.save(file);
        String url = cloudinaryConfig.uploadFile(file);
        userService.updatePhoto(UserName,url);
        message = "Uploaded the file successfully: " + url;
        return ResponseEntity.status(HttpStatus.OK).body(new PhotoResponse(message));

    }
}
