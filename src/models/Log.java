package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Log<T extends Source> {

    private String message;
    private LocalDateTime timestamp;
    private T source;
    private Status status;
    private List<String> tags = new ArrayList<>();

    public Log(String message, T source, Status status, String... tags) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.source = source;
        this.status = status;
        this.tags.addAll(List.of(tags));
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    void swap(int a, int b) {
        BiConsumer<Integer, Integer> swap = (i, j) -> {
            int tmp = i;
            j = i;
            i = tmp;
        };

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

    public List<String> getTags() {
        return tags;
    }
}
