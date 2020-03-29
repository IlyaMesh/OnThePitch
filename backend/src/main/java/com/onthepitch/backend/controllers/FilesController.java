package com.onthepitch.backend.controllers;

import com.onthepitch.backend.service.PhotoStorageService;
import com.onthepitch.shared.model.PhotoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin(origins = "*",maxAge = 3600)
@PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
public class FilesController {
    @Autowired
    PhotoStorageService photoStorageService;

    @PostMapping("/upload")
    public ResponseEntity<PhotoResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            photoStorageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new PhotoResponse(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new PhotoResponse(message));
        }
    }
}
