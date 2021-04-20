package ua.com.foxminded.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;
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
        Racer drr = new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER");
        drr.setStartTime(LocalDateTime.of(2018, 5, 24, 12, 14, 12, 054000000));
        Racer svf = new Racer("SVF", "Sebastian Vettel", "FERRARI");
        List<Racer> expectedRacers = new ArrayList<>();
        expectedRacers.add(drr);
        expectedRacers.add(svf);
        List<Racer> actualRacers = parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                FILENAME_START_LOG, FILENAME_END_LOG);
        assertEquals(expectedRacers, actualRacers);
    }

}
