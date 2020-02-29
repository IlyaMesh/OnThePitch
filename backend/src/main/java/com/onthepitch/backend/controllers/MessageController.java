package com.onthepitch.backend.controllers;

import com.onthepitch.backend.dao.PostRepository;
import com.onthepitch.backend.model.Post;

import com.onthepitch.backend.model.User;
import com.onthepitch.shared.model.PostResult;
//import com.onthepitch.shared.model.UserResult;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class MessageController {



    // private ObjectProvider<PostResult> provider;

//    @PostMapping("/factorialString")
//    public final String withInformation(@RequestBody final IncomeMessage message) {
//        return String.format("%s %d", message.getMessage());
//    }



//    @RequestMapping("/user")
//    public ResponseEntity<UserResult> user(//@AuthenticationPrincipal User user
//                                           ) {
//        User user = new User();//mock
//        return new ResponseEntity<UserResult>(new UserResult(user.getUser_id(), user.getUsername(), user.getUser_password(), user.getEmail(), user.getUser_pic(), "role"), HttpStatus.OK);
//    }

}
