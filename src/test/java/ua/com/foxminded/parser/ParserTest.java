package ua.com.foxminded.parser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @Test
    @DisplayName("")
    void test() {
    }

}
