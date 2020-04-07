package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void updatePhoto(String userName,String link) {
        userRepo.updatePhoto(userName,link);
    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
}
