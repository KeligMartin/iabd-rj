import models.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    void main() {

        Source premierSource = new Notification("Bienvenue", "notification mail");
        Source deuxiemeSource = new Action("Click", "action");

        Log<Source> premierLog = new Log<>("log 1", premierSource, Status.INFO);
        Log<Source> deuxiemeLog = new Log<>("log 2", deuxiemeSource, Status.ERROR);
        Log<Source> troisiemeLog = new Log<>("log 3", deuxiemeSource, Status.INFO);
        Log<Source> quatriemeLog = new Log<>("log 3", deuxiemeSource, Status.INFO);
        Log<Source> cinquiemeLog = new Log<>("log 5", deuxiemeSource, Status.ERROR);
        Log<Source> sixiemeLog = new Log<>("log 6", deuxiemeSource, Status.WARN);

        List<Log<Source>> logs = List.of(premierLog, deuxiemeLog, troisiemeLog, quatriemeLog, cinquiemeLog, sixiemeLog);

        Predicate<Log<Source>> isBeforeNow = log -> log.getTimestamp().isBefore(LocalDateTime.now()); // mémoire allouée
        Function<Log<Source>, String> logToMessageUppercase = log -> log.getMessage().toUpperCase();
        Predicate<Log<Source>> logContainsClick = log -> log.getMessage().contains("Click");
        Consumer<Log<Source>> logConsumer = log -> System.out.println(log);

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

    }
}
