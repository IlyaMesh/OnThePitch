package com.onthepitch.backend.controllers;

import com.onthepitch.backend.model.Rating;
import com.onthepitch.backend.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RatingController {

    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("/ratings")
    public List<Rating> showAll(){
        return ratingService.findAll();
    }

    @GetMapping("/note/{id}/like")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public void addLiketoNote(@PathVariable("id") String id){
        ratingService.createRating(Long.parseLong(id),true);
    }

    @GetMapping("/note/{id}/dislike")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public void addDisliketoNote(@PathVariable("id") String id){
        ratingService.createRating(Long.parseLong(id),false);
    }

    @GetMapping("/note/{id}/delete")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('MODERATOR') or hasAuthority('ADMIN')")
    public void delRatingtoNote(@PathVariable("id") String id){
        ratingService.removeRating(Long.parseLong(id));
    }


}
