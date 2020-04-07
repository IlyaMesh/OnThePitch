package com.onthepitch.shared.model;

import java.util.List;

public class UserResult {
    private Long user_id;
    private String username;
    private String email;
    private String user_pic;
    private List<String> role;

    public UserResult(Long user_id, String username, String email, String user_pic, List<String> role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.user_pic = user_pic;
        this.role = role;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }
}
