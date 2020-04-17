package com.onthepitch.backend.service;

import com.onthepitch.shared.model.response.LogResult;
import org.springframework.data.domain.Page;

public interface LogService {

    Page<LogResult> getLogs(int page, int size);

    void addLog(String text);
}
