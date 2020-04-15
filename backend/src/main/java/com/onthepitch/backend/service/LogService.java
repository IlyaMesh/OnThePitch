package com.onthepitch.backend.service;

import com.onthepitch.shared.model.LogResult;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LogService {

    Page<LogResult> getLogs(int page, int size);

    void addLog(String text);
}
