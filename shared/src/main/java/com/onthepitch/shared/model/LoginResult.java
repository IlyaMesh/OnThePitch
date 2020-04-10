package com.onthepitch.shared.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult {
    private String username;

    @JsonCreator
    public LoginResult(@JsonProperty("username") String username) {
        this.username = username;
    }
}
