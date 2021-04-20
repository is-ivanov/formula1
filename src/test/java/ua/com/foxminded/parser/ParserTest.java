package ua.com.foxminded.parser;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ParserTest {
    Parserable parser;
    
    @BeforeEach
    void setUp() throws Exception {
        parser = new Parser();
    }
    @Nested
    @DisplayName("test null input")
    class TestNullInput{
        
        @Test
        @DisplayName("test filename abbreviation file is null should throw IllegalArgumentException with message")
        void testAbbreviationFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles(null, "startLog", "endLog"));
            assertEquals("Filename is null!!", exception.getMessage());
        }
        
        @Test
        @DisplayName("test filename startLog file is null should throw IllegalArgumentException with message")
        void testStartLogFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles("abbreviationFile", null, "endLog"));
            assertEquals("Filename is null!!", exception.getMessage());
        }
        
        @Test
        @DisplayName("test filename endLog file is null should throw IllegalArgumentException with message")
        void testEndLogFilenameIsNull() {
            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> parser.parseDataFiles("abbreviationFile", "startLog", null));
            assertEquals("Filename is null!!", exception.getMessage());
        }
    }

    
}
