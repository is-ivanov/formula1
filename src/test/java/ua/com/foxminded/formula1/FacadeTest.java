package ua.com.foxminded.formula1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.formatter.Formattable;
import ua.com.foxminded.parser.Parserable;
import ua.com.foxminded.processor.Processor;

@ExtendWith(MockitoExtension.class)
class FacadeTest {
    private static final String FILENAME_ABBREVIATIONS = "abbreviations.txt";
    private static final String FILENAME_START_LOG = "start.log";
    private static final String FILENAME_END_LOG = "end.log";

    private Facade facade;

    @Mock
    private Parserable parser;

    @Mock
    private Processor processor;

    @Mock
    private Formattable formatter;

    @BeforeEach
    void setUp() throws Exception {
        facade = new Facade(parser, processor, formatter);
    }

    @Test
    @DisplayName("test with normal input should first call parser, after processor, after formatter")
    void testOrderCalls() {
        
    }

}
