package ua.com.foxminded.formatter;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.racer.Racer;

class FormatterTest {
    private static final String LF = System.lineSeparator();
    private static final String FIRST_STRING_RESULT = "01. Sebastian Vettel | FERRARI                   | 01:04.415";
    private static final String SECOND_STRING_RESULT = "02. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013";
    private static final String FIRST_RACER_ID = "DRR";
    private static final String FIRST_RACER_NAME = "Daniel Ricciardo";
    private static final String FIRST_RACER_TEAM = "RED BULL RACING TAG HEUER";
    private static final String FIRST_RACER_LAP_TIME = "PT01M12.013S";
    private static final String SECOND_RACER_ID = "SVF";
    private static final String SECOND_RACER_NAME = "Sebastian Vettel";
    private static final String SECOND_RACER_TEAM = "FERRARI";
    private static final String SECOND_RACER_LAP_TIME = "PT01M04.415S";
        
    
    private Formattable formatter;
    private List<Racer> racers;
    
    @BeforeEach
    void setUp() throws Exception {
        formatter = new Formatter();
        racers = new LinkedList<>();
    }

    @Test
    @DisplayName("test format two string with normal result")
    void testFormatTwoString() {
        String expectedResult = FIRST_STRING_RESULT + LF
                              + SECOND_STRING_RESULT + LF;
        
        Racer firstRacer = new Racer(FIRST_RACER_ID, FIRST_RACER_NAME, FIRST_RACER_TEAM);
        firstRacer.setLapTime(Duration.parse(FIRST_RACER_LAP_TIME));
        Racer secondRacer = new Racer(SECOND_RACER_ID, SECOND_RACER_NAME, SECOND_RACER_TEAM);
        secondRacer.setLapTime(Duration.parse(SECOND_RACER_LAP_TIME));
        
        racers.add(firstRacer);
        racers.add(secondRacer);
        String actualResult = formatter.formatResult(racers);
        
        assertEquals(expectedResult, actualResult);
        
    }
    
    @Test
    @DisplayName("test drawing underline after 15 racers")
    void testDrawingUnderLineAfter15Racers() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            stringBuilder.append(String.format("%02d. %-4s | %-4s | %s%s", i + 1, "Name", "Team", "00:00.001", LF));
        }
        stringBuilder.append("---------------------------").append(LF)
        .append(String.format("%02d. %-4s | %-4s | %s%s", 16, "Name", "Team", "00:00.001", LF));
        String expectedResult = stringBuilder.toString();
        
        for (int i = 0; i < 16; i++) {
            Racer racer = new Racer("df", "Name", "Team");
            racer.setLapTime(Duration.parse("PT00M00.001S"));
            racers.add(racer);
        }
        String actualResult = formatter.formatResult(racers);
        
        assertEquals(expectedResult, actualResult);
    }

}
