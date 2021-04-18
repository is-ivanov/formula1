package ua.com.foxminded.formula1;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import ua.com.foxminded.formatter.Formattable;
import ua.com.foxminded.parser.Parserable;
import ua.com.foxminded.processor.Processor;
import ua.com.foxminded.racer.Racer;

public class Facade {
    private Parserable parser;
    private Processor processor;
    private Formattable formatter;

    public Facade(Parserable parser, Processor calculator, Formattable formatter) {
        this.parser = parser;
        this.processor = calculator;
        this.formatter = formatter;
    }

    private static final String FILENAME_ABBREVIATIONS = "abbreviations.txt";
    private static final String FILENAME_START_LOG = "start.log";
    private static final String FILENAME_END_LOG = "end.log";

    public void processQualificationResult() {

        List<String> fileContents;
        fileContents = parser.readFile(FILENAME_ABBREVIATIONS);
        List<Racer> racers = parser.parseAbbreviationFile(fileContents);
        fileContents = parser.readFile(FILENAME_START_LOG);
        Map<String, LocalDateTime> startTimes = parser
                .parseLogFile(fileContents);
        fileContents = parser.readFile(FILENAME_END_LOG);
        Map<String, LocalDateTime> endTimes = parser.parseLogFile(fileContents);
        racers = processor.processResult(racers,
                startTimes, endTimes);
        String string = formatter.formatResult(racers);
        
        
        System.out.println(string);

    }
}
