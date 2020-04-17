package com.onthepitch.shared.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserResult {
    private Long user_id;
    private String username;
    private String email;
    private String user_pic;
    private List<String> role;

}
