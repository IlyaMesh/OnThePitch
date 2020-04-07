package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Role;
import com.onthepitch.backend.model.User;
import com.onthepitch.shared.model.UserResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserToUserResult implements Converter<User, UserResult> {
    @Override
    public UserResult convert(User user) {
        return new UserResult(
                user.getUser_id(),
                user.getUsername(),
                user.getEmail(),
                user.getUser_pic(),
                user.getRoles().stream()
                .map(Role::name)
                .collect(Collectors.toList())
        );
    }
}
