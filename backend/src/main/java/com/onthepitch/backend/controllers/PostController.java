package com.onthepitch.backend.controllers;

import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.converter.PostToPostForm;
import com.onthepitch.backend.dao.CommentRepository;
import com.onthepitch.backend.dao.MatchRepository;
import com.onthepitch.backend.dao.PostRepository;
import com.onthepitch.backend.model.Comment;
import com.onthepitch.backend.model.Match;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.service.PostService;
import com.onthepitch.backend.soccerApi.SoccerDataService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class PostController {
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    public SoccerDataService soccerDataService;

    private PostToPostForm postToPostForm;

    @Autowired
    public void setPostToPostForm(PostToPostForm postToPostForm) {
        this.postToPostForm = postToPostForm;
    }

    @Autowired
    public void setProductService(PostService postService) {
        this.postService = postService;
    }

//    @RequestMapping("/")
//    public String redirToList() {
//        return "redirect:/post/list";
//    }

//    @GetMapping({"/post/list", "/post"})
//    public String listProducts(Map<String, Object> model) throws InterruptedException {
//        soccerDataService.updateAll();
//        // List<Match> matches = matchRepository.findUpcomingMatches(new Date());
//        Date from = Date.from(LocalDate.now().minusDays(4).atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Date to = Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant());
//        List<Match> matches = matchRepository.findMatchesByMatchTimeBetweenOrderByMatchTime(from, to);
//        Iterable<Post> posts = postRepository.findAll();
//        //отображать только комменты на посты, а под ними просто список их комментов
//        List<Comment> comments = (List<Comment>) commentRepository.findAll();
//        model.put("posts", posts);
//        model.put("matches", matches);
//        model.put("comments",comments);
//        return "post/list";
//    }


    @RequestMapping("/post/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {

        model.addAttribute("post", postService.getById(Long.valueOf(id)));
        return "post/show";
    }

    @RequestMapping("post/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Post post = postService.getById(Long.valueOf(id));
        PostForm postForm = postToPostForm.convert(post);

        model.addAttribute("postForm", postForm);
        return "post/postform";
    }

    @RequestMapping("/post/new")
    public String newProduct(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "post/postform";
    }

//    @RequestMapping(value = "/post", method = RequestMethod.POST)
//    public String saveOrUpdateProduct(@AuthenticationPrincipal User user, @Valid PostForm postForm, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "post/postform";
//        }
//
//        Post savedProduct = postService.saveOrUpdatePostForm(postForm, user);
//
//        return "redirect:/post/show/" + savedProduct.getPost_id();
//    }

    @RequestMapping("/post/delete/{id}")
    public String delete(@PathVariable String id) {
        postService.delete(Long.valueOf(id));
        return "redirect:/post/list";
    }

}
