package com.onthepitch.backend.controllers;

import com.onthepitch.backend.commands.PostForm;
import com.onthepitch.backend.converter.PostToPostForm;
import com.onthepitch.backend.dao.PostRepository;
import com.onthepitch.backend.model.Post;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class PostController {
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
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
//    public String redirToList(){
//        return "redirect:/post/list";
//    }

    @GetMapping({"/post/list", "/post"})
    public String listProducts(Map<String,Object> model){
        Iterable<Post> posts = postRepository.findAll();
        model.put("posts", posts);
        return "post/list";
    }

    @RequestMapping("/post/show/{id}")
    public String getProduct(@PathVariable String id, Model model){

        model.addAttribute("post", postService.getById(Long.valueOf(id)));
        return "post/show";
    }

    @RequestMapping("post/edit/{id}")
    public String edit(@PathVariable String id, Model model){
        Post post = postService.getById(Long.valueOf(id));
        PostForm postForm = postToPostForm.convert(post);

        model.addAttribute("postForm", postForm);
        return "post/postform";
    }

    @RequestMapping("/post/new")
    public String newProduct(Model model){
        model.addAttribute("postForm", new PostForm());
        return "post/postform";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@AuthenticationPrincipal User user, @Valid PostForm postForm, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "post/postform";
        }

        Post savedProduct = postService.saveOrUpdatePostForm(postForm,user);

        return "redirect:/post/show/" + savedProduct.getPost_id();
    }

    @RequestMapping("/post/delete/{id}")
    public String delete(@PathVariable String id){
        postService.delete(Long.valueOf(id));
        return "redirect:/post/list";
    }
//    @InitBinder("post")
//    public void customizeBinding (WebDataBinder binder) {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
//        dateFormatter.setLenient(false);
//        binder.registerCustomEditor(Date.class, "created_at",
//                new CustomDateEditor(dateFormatter, true));
//    }
}
