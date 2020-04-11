package com.onthepitch.backend.service;

import com.onthepitch.shared.model.LogResult;

import java.util.List;

public interface LogService {

    List<LogResult> getLogs();

    void addLog(String text);
}
