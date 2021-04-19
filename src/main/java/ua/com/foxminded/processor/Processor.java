package ua.com.foxminded.processor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ua.com.foxminded.racer.Racer;

public class Processor {

    public List<Racer> calculateLapTime(List<Racer> racers) {

        racers.stream().forEach(racer -> racer.setLapTime(
                Duration.between(racer.getStartTime(), racer.getEndTime())));

        return racers;
    }

}
