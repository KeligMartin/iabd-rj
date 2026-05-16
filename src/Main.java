import models.Action;
import models.Log;
import models.Notification;
import models.Source;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {

    void main() { // mémoire allouée de main

        Source premierSource = new Notification("Bienvenue", "notification mail");
        Source deuxiemeSource = new Action("Click", "action");

        Log<Source> premierLog = new Log<>("log 1 ", premierSource);
        Log<Source> deuxiemeLog = new Log<>("log 2 ", deuxiemeSource);

        List<Log<Source>> logs = List.of(premierLog, deuxiemeLog);

        Predicate<Log<Source>> isBeforeNow = log -> log.getTimestamp().isBefore(LocalDateTime.now()); // mémoire allouée
        Function<Log<Source>, String> logToMessageUppercase = log -> log.getMessage().toUpperCase();
        Predicate<Log<Source>> logContainsClick = log -> log.getMessage().contains("Click");
        Consumer<Log<Source>> logConsumer = log -> System.out.println(log);

        // Filtrer
        logs // List de Log
                .stream() // Stream de Log
                .filter(isBeforeNow) // Stream de log
                .filter(logContainsClick)
                .forEach(logConsumer); // void

        logs                            // List de log
                .stream()               // Stream de log
                .filter(isBeforeNow) // Stream de log
                .map(logToMessageUppercase) // Stream de String
                .forEach(System.out::println); // void
    }
}
