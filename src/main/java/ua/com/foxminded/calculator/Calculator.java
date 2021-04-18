package ua.com.foxminded.calculator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.com.foxminded.racer.Racer;

public class Calculator {

    public List<Racer> calculateResult(List<Racer> racers,
            Map<String, LocalDateTime> startTimes,
            Map<String, LocalDateTime> endTimes) {

        Map<String, Duration> lapTimes = calculateLapTime(startTimes, endTimes);

        racers.stream().forEach(r -> {
            r.setStartTime(startTimes.get(r.getId()));
            r.setEndTime(endTimes.get(r.getId()));
            r.setLapTime(lapTimes.get(r.getId()));
        });

        return racers;

    }

    private Map<String, Duration> calculateLapTime(
            Map<String, LocalDateTime> startTimes,
            Map<String, LocalDateTime> endTimes) {
        return startTimes.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                x -> Duration.between(x.getValue(), endTimes.get(x.getKey()))));
    }
}
