package com.onthepitch.backend.converter;

import com.onthepitch.backend.model.Log;
import com.onthepitch.shared.model.response.LogResult;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

@Component
public class LogToLogResult implements Converter<Log, LogResult> {
    @Override
    public LogResult convert(Log log) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yy HH:mm", Locale.UK);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
        return new LogResult(
                log.getUser().getUsername(),
                log.getText(),
                simpleDateFormat.format(log.getCreated_at())
        );
    }
}
