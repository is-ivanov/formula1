package ua.com.foxminded.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.racer.Racer;

@ExtendWith(MockitoExtension.class)
class ParserTest {
    private static final String FILENAME_ABBREVIATION_FILE = "abbreviationsTest.txt";
    private static final String FILENAME_START_LOG = "startTest.log";
    private static final String FILENAME_END_LOG = "endTest.log";
    private static final String FILENAME_MISSING_FILE = "missingFile";
    private static final String MESSAGE_FILENAME_IS_NULL = "Filename is null!!";
    private static final String MESSAGE_FILE_NOT_FOUND = "File \"missingFile\" not found!!";
    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final String FIRST_RACER_ID = "DRR";
    private static final String FIRST_RACER_NAME = "Daniel Ricciardo";
    private static final String FIRST_RACER_TEAM = "RED BULL RACING TAG HEUER";
    private static final String FIRST_RACER_START_TIME = "2018-05-24 12:14:12.054";
    private static final String FIRST_RACER_END_TIME = "2018-05-24 12:15:24.067";
    private static final String SECOND_RACER_ID = "SVF";
    private static final String SECOND_RACER_NAME = "Sebastian Vettel";
    private static final String SECOND_RACER_TEAM = "FERRARI";
    private static final String SECOND_RACER_START_TIME = "2018-05-24 12:02:58.917";
    private static final String SECOND_RACER_END_TIME = "2018-05-24 12:04:03.332";

    Parserable parser;

    @BeforeEach
    void setUp() throws Exception {
        parser = new Parser();
    }

    @Nested
    @DisplayName("test null input")
    class TestNullInput {

        @Test
        @DisplayName("test filename abbreviation file is null should throw IllegalArgumentException with message")
        void testAbbreviationFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles(null, FILENAME_START_LOG,
                            FILENAME_END_LOG));
            assertEquals(MESSAGE_FILENAME_IS_NULL, exception.getMessage());
        }

        @Test
        @DisplayName("test filename startLog file is null should throw IllegalArgumentException with message")
        void testStartLogFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                            null, FILENAME_END_LOG));
            assertEquals(MESSAGE_FILENAME_IS_NULL, exception.getMessage());
        }

        @Test
        @DisplayName("test filename endLog file is null should throw IllegalArgumentException with message")
        void testEndLogFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                            FILENAME_START_LOG, null));
            assertEquals(MESSAGE_FILENAME_IS_NULL, exception.getMessage());
        }

    }

    @Nested
    @DisplayName("test input missimg file")
    class testMissingFile {

        @Test
        @DisplayName("test input missing abbreviations file should return IOException with message")
        void testMissingAbbreviationsFile() {
            Exception exception = assertThrows(IOException.class,
                    () -> parser.parseDataFiles(FILENAME_MISSING_FILE,
                            FILENAME_START_LOG, FILENAME_END_LOG));
            assertEquals(MESSAGE_FILE_NOT_FOUND, exception.getMessage());
        }

        @Test
        @DisplayName("test input missing startLog file should return IOException with message")
        void testMissingStartLogFile() {
            Exception exception = assertThrows(IOException.class,
                    () -> parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                            FILENAME_MISSING_FILE, FILENAME_END_LOG));
            assertEquals(MESSAGE_FILE_NOT_FOUND, exception.getMessage());
        }

        @Test
        @DisplayName("test input missing endLog file should return IOException with message")
        void testMissingEndLogFile() {
            Exception exception = assertThrows(IOException.class,
                    () -> parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                            FILENAME_START_LOG, FILENAME_MISSING_FILE));
            assertEquals(MESSAGE_FILE_NOT_FOUND, exception.getMessage());
        }

    }

    @Test
    @DisplayName("test reading file with right data should return filled List of racers")
    void testReadingRightData() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);
        
        Racer firstRacer = new Racer(FIRST_RACER_ID, FIRST_RACER_NAME, FIRST_RACER_TEAM);
        firstRacer.setStartTime(LocalDateTime.parse(FIRST_RACER_START_TIME, formatter));
        firstRacer.setEndTime(LocalDateTime.parse(FIRST_RACER_END_TIME, formatter));
        
        Racer secondRacer = new Racer(SECOND_RACER_ID, SECOND_RACER_NAME, SECOND_RACER_TEAM);
        secondRacer.setStartTime(LocalDateTime.parse(SECOND_RACER_START_TIME, formatter));
        secondRacer.setEndTime(LocalDateTime.parse(SECOND_RACER_END_TIME, formatter));
        
        List<Racer> expectedRacers = new ArrayList<>();
        expectedRacers.add(firstRacer);
        expectedRacers.add(secondRacer);
        List<Racer> actualRacers = parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                FILENAME_START_LOG, FILENAME_END_LOG);
        assertEquals(expectedRacers, actualRacers);
    }

}
