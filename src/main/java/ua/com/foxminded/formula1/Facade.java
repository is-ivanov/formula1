package ua.com.foxminded.formula1;

import java.util.List;
import java.util.Map;

import ua.com.foxminded.parser.Parserable;
import ua.com.foxminded.racer.Racer;

public class Facade {
    Parserable parser;

    public Facade(Parserable parser) {
        this.parser = parser;
    }

    private static final String FILENAME_ABBREVIATIONS = "abbreviations.txt";
    private static final String FILENAME_START_LOG = "start.log";
    private static final String FILENAME_END_LOG = "end.log";

    public void start() {
        List<String> fileContents;
        fileContents = parser.readFile(FILENAME_ABBREVIATIONS);
        Map<String, Racer> racers = parser.parseAbbreviationFile(fileContents);
    }
}
