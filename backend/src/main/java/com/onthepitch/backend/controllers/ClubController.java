package com.onthepitch.backend.controllers;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClubController {

    public ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs/{id}")
    public Club getClubById(@PathVariable String id) {
        return clubService.getById(Long.parseLong(id));
    }

    @GetMapping("/clubs")
    public List<Club> getAllClubs() {
        return clubService.listAll();
    }

}
