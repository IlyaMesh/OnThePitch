package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.converter.UserToUserResult;
import com.onthepitch.backend.exсeption.NoSuchUserException;
import com.onthepitch.backend.model.Role;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.UserRepo;
import org.springframework.data.domain.PageImpl;
import com.onthepitch.shared.model.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserToUserResult userToUserResult;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public Page<UserResult> getAllUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> all = userRepo.findAll(pageRequest);
        int totalElements = (int) all.getTotalElements();
//        List<UserResult> result = new ArrayList<>();
//        for(User user: all){
//            result.add(userToUserResult.convert(user));
//        }
        return new PageImpl<>(
                    all.stream()
                        .map(user -> userToUserResult.convert(user))
                        .collect(Collectors.toList()), pageRequest, totalElements);
    }

    public void updatePhoto(String userName, String link) {
        userRepo.updatePhoto(userName, link);
    }

    public void promote(Long user_id) {
        User user = checkUserById(user_id);
        if (user.getRoles().contains(Role.MODERATOR)) {
            user.addRole(Role.ADMIN);
        } else {
            user.addRole(Role.MODERATOR);
        }
    }

    public void demote(Long user_id) {
        User user = checkUserById(user_id);
        if (user.getRoles().contains(Role.ADMIN)) {
            user.removeRole(Role.ADMIN);
        } else {
            user.removeRole(Role.MODERATOR);
        }
    }

    public void ban(Long user_id) {
        User user = checkUserById(user_id);
        userRepo.delete(user);
    }

    private User checkUserById(Long user_id) {
        User user = userRepo.findById(user_id).orElse(null);
        if (user == null) {
            throw new NoSuchUserException();
        }
        return user;
    }
}
