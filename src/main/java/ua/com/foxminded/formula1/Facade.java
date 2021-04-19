package ua.com.foxminded.formula1;

import java.util.List;
import ua.com.foxminded.formatter.Formattable;
import ua.com.foxminded.parser.Parserable;
import ua.com.foxminded.processor.Processor;
import ua.com.foxminded.racer.Racer;

public class Facade {
    private static final String FILENAME_ABBREVIATIONS = "abbreviations.txt";
    private static final String FILENAME_START_LOG = "start.log";
    private static final String FILENAME_END_LOG = "end.log";

    private Parserable parser;
    private Processor processor;
    private Formattable formatter;

    public Facade(Parserable parser, Processor calculator,
            Formattable formatter) {
        this.parser = parser;
        this.processor = calculator;
        this.formatter = formatter;
    }

    public void processQualificationResult() {

        List<Racer> racers = parser.parseDataFiles(FILENAME_ABBREVIATIONS,
                FILENAME_START_LOG, FILENAME_END_LOG);
        racers = processor.calculateLapTime(racers);
        String string = formatter.formatResult(racers);

        System.out.println(string);
    }
}
