package ua.com.foxminded.formula1;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.com.foxminded.formatter.Formattable;
import ua.com.foxminded.parser.Parserable;
import ua.com.foxminded.processor.Processor;
import ua.com.foxminded.racer.Racer;

@ExtendWith(MockitoExtension.class)
class FacadeTest {

    private Facade facade;

    @Mock
    private Parserable parser;

    @Mock
    private Processor processor;

    @Mock
    private Formattable formatter;

    @Mock
    List<Racer> racers;

    @BeforeEach
    void setUp() throws Exception {
        facade = new Facade(parser, processor, formatter);
        racers = new ArrayList<>();
    }

    @Test
    @DisplayName("test with normal input should first call parser, after processor, after formatter")
    void testOrderCallsObjects() throws IOException {

        facade.processQualificationResult();

        InOrder inOrder = Mockito.inOrder(parser, processor, formatter);

        inOrder.verify(parser).parseDataFiles(anyString(), anyString(),
                anyString());
        inOrder.verify(processor).calculateLapTime(racers);
        inOrder.verify(formatter).formatResult(racers);

    }

    @Test
    @DisplayName("test with normal input should call parser, processor and formatter once")
    void testNumberCallsObjects() throws IOException {
        facade.processQualificationResult();
        verify(parser, times(1)).parseDataFiles(anyString(), anyString(), anyString());
        verify(processor, times(1)).calculateLapTime(racers);
        verify(formatter, times(1)).formatResult(racers);
        
    }
    
    @Test
    @DisplayName("test with false input, when parser throws exception should never call processor and formatter")
    void testNumberCallsProcessorAndFormatter() {
        
        try {
            when(parser.parseDataFiles(anyString(), anyString(), anyString())).thenThrow(IOException.class);
            facade.processQualificationResult();
            verify(parser, times(1)).parseDataFiles(anyString(), anyString(), anyString());
            verify(processor, never()).calculateLapTime(racers);
            verify(formatter, never()).formatResult(racers);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        
    }

}
