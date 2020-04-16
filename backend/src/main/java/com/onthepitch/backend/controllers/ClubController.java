package com.onthepitch.backend.controllers;

import com.onthepitch.backend.model.Club;
import com.onthepitch.backend.service.ClubService;
import com.onthepitch.shared.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/clubs")
public class ClubController {

    public ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/{id}")
    public Club getClubById(@PathVariable String id) {
        return clubService.getById(Long.parseLong(id));
    }

    @GetMapping("")
    public List<Club> getAllClubs() {
        return clubService.listAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MessageResponse> updateClub(@PathVariable String id) {
        clubService.updateClubForUser(Long.parseLong(id));
        return ResponseEntity.ok(new MessageResponse("Favourite club is updated"));
    }


}
