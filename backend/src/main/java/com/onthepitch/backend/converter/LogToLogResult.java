package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Log;
import com.onthepitch.backend.model.Role;
import com.onthepitch.shared.model.LogResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class LogToLogResult implements Converter<Log, LogResult> {
    @Override
    public LogResult convert(Log log) {
        return new LogResult(
                log.getUser().getUsername(),
                log.getUser().getRoles().stream()
                        .map(Role::name)
                        .collect(Collectors.toList()),
                log.getText()
        );
    }
}
