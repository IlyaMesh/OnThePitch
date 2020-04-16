package com.onthepitch.shared.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LogResult {
    private String username;
    private String text;
    private String created_at;
}
