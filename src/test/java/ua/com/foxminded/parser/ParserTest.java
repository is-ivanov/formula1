package ua.com.foxminded.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParserTest {
    private static final String FILENAME_ABBREVIATION_FILE = "abbreviationsTest.txt";
    private static final String FILENAME_START_LOG = "startTest.log";
    private static final String FILENAME_END_LOG = "endTest.log";
    private static final String FILENAME_IS_NULL = "Filename is null!!";

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
            assertEquals(FILENAME_IS_NULL, exception.getMessage());
        }

        @Test
        @DisplayName("test filename startLog file is null should throw IllegalArgumentException with message")
        void testStartLogFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                            null, FILENAME_END_LOG));
            assertEquals(FILENAME_IS_NULL, exception.getMessage());
        }

        @Test
        @DisplayName("test filename endLog file is null should throw IllegalArgumentException with message")
        void testEndLogFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles(FILENAME_ABBREVIATION_FILE,
                            FILENAME_START_LOG, null));
            assertEquals(FILENAME_IS_NULL, exception.getMessage());
        }

    }

    @Nested
    @DisplayName("test input missimg file")
    class testMissingFile {

        @Test
        @DisplayName("test input missing file shuold return IOException with message")
        void testMissingAbbreviationFile() {
            Exception exception = assertThrows(IOException.class,
                    () -> parser.parseDataFiles("abbrevMiss",
                            FILENAME_START_LOG, FILENAME_END_LOG));
            assertEquals("", exception.getMessage());
        }

    }

}
