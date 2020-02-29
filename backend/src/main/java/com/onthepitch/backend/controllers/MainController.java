package com.onthepitch.backend.controllers;

import com.onthepitch.backend.dao.MatchRepository;
import com.onthepitch.backend.dao.PostRepository;
import com.onthepitch.backend.dao.UserRepo;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.soccerApi.SoccerDataService;
import com.onthepitch.shared.model.MatchesResult;
import com.onthepitch.shared.model.PostResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class MainController {

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

    @PostMapping("/posts")
    @CrossOrigin
    public List<PostResult> listPosts() {

        Iterable<Post> posts = postRepository.findAll();
        List<PostResult> postResults = new ArrayList<>();
        for(Post post:posts){
            postResults.add( new PostResult(
                post.getPost_id(),
                post.getAuthorName(),
                post.getHeader(),
                post.getText(),
                post.getCreated_at()
        ));
        }

        return postResults;
    }

    @PostMapping("/matches")
    @CrossOrigin
    public List<MatchesResult> listMatches(){
        try{
        soccerDataService.updateAll();}
        catch (InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        Date from = Date.from(LocalDate.now().minusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date to = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Match> matches = matchRepository.findMatchesByMatchTimeBetweenOrderByMatchTime(from, to);
        List<MatchesResult> matchesResults =  new ArrayList<MatchesResult>();
        for(Match match : matches){
            matchesResults.add( new MatchesResult(
                    match.getMatch_id(),
                    match.getHomeTeam().getClub_id(),
                    match.getHomeTeam().getClub_name(),
                    match.getAwayTeam().getClub_id(),
                    match.getAwayTeam().getClub_name(),
                    match.getHomeTeamScored(),
                    match.getAwayTeamScored(),
                    match.getHomeTeamPenalties(),
                    match.getAwayTeamPenalties(),
                    match.getMatchTime(),
                    match.getLeague().getLeague_id(),
                    match.getLeague().getLeague_title()
            ));
        }
        return matchesResults;
    }

//    @PostMapping("/login")
//    @CrossOrigin
//    public LoginResult getUserResult(@RequestBody final LoginRequest request){
////TODO попытаться зарегать пользователя findByUsername
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
