package com.onthepitch.backend.service.serviceImpl;

import com.onthepitch.backend.converter.LogToLogResult;
import com.onthepitch.backend.model.Log;
import com.onthepitch.backend.model.User;
import com.onthepitch.backend.repos.LogRepository;
import com.onthepitch.backend.repos.UserRepo;
import com.onthepitch.backend.service.LogService;
import com.onthepitch.shared.model.LogResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LogServiceImpl  implements LogService {
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private LogToLogResult logToLogResult;

    @Override
    public List<LogResult> getLogs() {
        List<Log> logs = logRepository.findAll();
        List<LogResult> result = new ArrayList<>();
        for(Log log : logs){
            result.add(logToLogResult.convert(log));
        }
        return result;
    }

    @Override
    public void addLog(String text) {

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //User user = userRepo.findByUsername(UserName);
        Log log = new Log();
        log.setText(text);
        log.setUser(user);
            logRepository.save(log);
    }
}
