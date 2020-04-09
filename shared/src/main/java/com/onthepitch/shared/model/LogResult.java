package com.onthepitch.shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LogResult {
    private String username;
    private List<String> roles;
    private String text;
}
