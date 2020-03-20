package com.onthepitch.backend.controllers;

import com.onthepitch.backend.converter.MatchToResult;
import com.onthepitch.backend.repos.MatchRepository;
import com.onthepitch.backend.repos.PostRepository;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.soccerApi.SoccerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {
    @Autowired
    public MatchToResult matchToResult;
    private PostRepository postRepository;
    private UserRepo userRepo;
    private AuthenticationManager authManager;
    private SoccerDataService soccerDataService;
    private MatchRepository matchRepository;

    @Autowired
    public MainController(PostRepository postRepository, UserRepo userRepo,SoccerDataService soccerDataService,MatchRepository matchRepository) {
        this.postRepository = postRepository;
        this.userRepo = userRepo;
        this.soccerDataService = soccerDataService;
        this.matchRepository = matchRepository;
    }





//    @PostMapping("/login")
//    @CrossOrigin
//    public LoginResult getUserResult(@RequestBody final LoginRequest request){
//
//
//        UsernamePasswordAuthenticationToken uAuth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
//        Authentication auth = authManager.authenticate(uAuth);
//        SecurityContext sc = SecurityContextHolder.getContext();
//        sc.setAuthentication(auth);
////        HttpSession session = request.getSession(true);
////        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
//        LoginResult userResult = new LoginResult(sc.getAuthentication().getName());
//        return userResult;
//    }
}
