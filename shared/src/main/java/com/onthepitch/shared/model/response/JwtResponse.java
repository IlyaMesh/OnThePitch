package com.onthepitch.shared.model.response;

import java.util.List;


public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private String user_pic;
    private String reg_time;
    private List<String> roles;
    private Long club_id;

    public String getUser_pic() {
        return user_pic;
    }

    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }

    public JwtResponse(String accessToken,
                       Long id,
                       String username,
                       String email,
                       String user_pic,
                       String reg_time,
                       List<String> roles,
                       Long club_id) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.user_pic = user_pic;
        this.reg_time = reg_time;
        this.roles = roles;
        this.club_id = club_id;
    }

    public Long getClub_id() {
        return club_id;
    }

    public void setClub_id(Long club_id) {
        this.club_id = club_id;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
