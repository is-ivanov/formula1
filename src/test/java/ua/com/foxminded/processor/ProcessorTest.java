package ua.com.foxminded.processor;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.racer.Racer;

class ProcessorTest {
    private static final String RACER_ID = "id";
    private static final String RACER_NAME = "Name";
    private static final String RACER_TEAM = "Team";
    private static final String START_TIME_STRING = "2020-01-12 12:30:00.000";
    private static final String END_TIME_STRING = "2020-01-12 12:31:10.100";
    private static final String LAP_TIME_DURATION_STRING = "PT1M10.1S";


    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss.SSS";
    
    private Processor processor;
    
    @BeforeEach
    void setUp() throws Exception {
        processor = new Processor();
    }

    @Test
    @DisplayName("test calculateLapTime")
    void testCalculateLapTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);
        
        List<Racer> racers = new ArrayList<>();
        Racer racer = new Racer(RACER_ID, RACER_NAME, RACER_TEAM);
        racer.setStartTime(LocalDateTime.parse(START_TIME_STRING, formatter));
        racer.setEndTime(LocalDateTime.parse(END_TIME_STRING, formatter));
        racers.add(racer);
        
        List<Racer> actualList = processor.calculateLapTime(racers);
        
        Duration actualDuration = actualList.get(0).getLapTime();
        Duration expecteDuration = Duration.parse(LAP_TIME_DURATION_STRING);
        
        assertEquals(expecteDuration, actualDuration);
        
    }

}
