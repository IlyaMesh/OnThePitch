package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.converter.LogToLogResult;
import com.onthepitch.backend.model.Log;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.LogRepository;
import com.onthepitch.backend.service.LogService;
import com.onthepitch.shared.model.response.LogResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private LogToLogResult logToLogResult;

    @Override
    public Page<LogResult> getLogs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Log> logs = logRepository.findAll(pageRequest);
        int totalElements = (int) logs.getTotalElements();
        return new PageImpl<>(
                logs.stream()
                        .map(log -> logToLogResult.convert(log))
                        .collect(Collectors.toList()), pageRequest, totalElements);
    }

    @Override
    public void addLog(String text) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User user = userRepo.findByUsername(UserName);
        Log log = new Log();
        log.setText(text);
        log.setUser(user);
        log.setCreated_at(new Date());
        logRepository.save(log);
    }
}
