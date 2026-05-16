package models;

import java.time.LocalDateTime;
import java.util.List;

public class Log<T extends Source> {

    private String message;
    private LocalDateTime timestamp;
    private T source;

    public Log(String message, T source) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.source = source;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message + this.source.getBody();
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
