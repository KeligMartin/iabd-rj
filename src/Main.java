import models.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static final Predicate<Log<Source>> containsClick = log -> log.getMessage().contains("Click");

    void main() {

        Source premierSource = new Notification("Bienvenue", "notification mail");
        Source deuxiemeSource = new Action("Click", "action");

        Log<Source> premierLog = new Log<>("log 1", premierSource, Status.INFO, "tag1", "tag2");
        Log<Source> deuxiemeLog = new Log<>("log 2", deuxiemeSource, Status.ERROR, "tag1", "tag2");
        Log<Source> troisiemeLog = new Log<>("log 3", premierSource, Status.INFO, "tag1", "tag2");
        Log<Source> quatriemeLog = new Log<>("log 3", deuxiemeSource, Status.INFO, "tag1", "tag2");
        Log<Source> cinquiemeLog = new Log<>("log 5", premierSource, Status.ERROR, "tag1", "tag2");
        Log<Source> sixiemeLog = new Log<>("log 6", deuxiemeSource, Status.WARN, "tag1", "tag2");

        List<Log<Source>> logs = List.of(premierLog, deuxiemeLog, troisiemeLog, quatriemeLog, cinquiemeLog, sixiemeLog);

        Predicate<Log<Source>> isBeforeNow = log -> log.getTimestamp().isBefore(LocalDateTime.now()); // mémoire allouée
        Function<Log<Source>, String> logToMessageUppercase = log -> log.getMessage().toUpperCase();
        Predicate<Log<Source>> logContainsClick = log -> log.getMessage().contains("Click");
        Consumer<Log<Source>> logConsumer = log -> System.out.println(log);
        Function<Log<Source>, Status> logToStatus = log -> log.getStatus();
        Predicate<Log<Source>> isError = log -> log.getStatus().equals(Status.ERROR);

        logs                            // List<Log>
                .stream()               // Stream<Log>
                .filter(isBeforeNow.or(logContainsClick))    // lambda pour le filtre Stream<Log>
                .map(logToMessageUppercase)      ;           // Stream<String>

        Map<Status, List<Log<Source>>> logsByStatus = logs      // List<Log>
                .stream()                                       // Stream<Log>
                .filter(isBeforeNow)
                .collect(Collectors.groupingBy(log -> log.getStatus())); // Map<Status, List<Log>>

        Set<String> messages = logs
                .stream()
                .map(logToMessageUppercase)
                .collect(Collectors.toSet())
                ; // Stream<String>

        Map<Status, Long> countByStatusContainingClick = logs
                .stream()
                .filter(containsClick)
                .collect(Collectors.groupingBy(log -> log.getStatus(), Collectors.counting()));

        Set<Log<Source>> errorLogs = logs
                .stream()
                .filter(isError)
                .collect(Collectors.toSet());

        List<String> logsTags =
                logs.stream()
                        .flatMap(log -> log.getTags().stream())// transform List<Log> en List<List<String>>
                        .toList();

    }
}
