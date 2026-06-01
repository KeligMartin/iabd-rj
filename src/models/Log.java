package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Log<T extends Source> {

    private String message;
    private LocalDateTime timestamp;
    private T source;
    private Status status;

    public Log(String message, T source, Status status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.source = source;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return String.format("[%s] %s %s", this.getStatus(), this.message, this.getSource().getBody());
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getSource() {
        return source;
    }
}
