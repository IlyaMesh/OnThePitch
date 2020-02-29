package com.onthepitch.shared.model;

public class UserResult {
    private Long user_id;
    private String username;
    private String user_password;
    private String email;
    private String user_pic;
    private String role;

    //ВЕРОЯТНО ПРОБЛЕМА В ВИДИМОСТИ USERRESULT!!!!
    public UserResult(Long user_id, String username, String user_password, String email, String user_pic, String role) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
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

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
