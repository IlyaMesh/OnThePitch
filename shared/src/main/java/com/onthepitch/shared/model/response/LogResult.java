package com.onthepitch.shared.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LogResult {
    private String username;
    private String text;
    private String created_at;
}
