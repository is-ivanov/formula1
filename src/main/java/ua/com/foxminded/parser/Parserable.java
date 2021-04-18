package ua.com.foxminded.parser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import ua.com.foxminded.racer.Racer;

public interface Parserable {
    List<String> readFile(String filepath);

    List<Racer> parseAbbreviationFile(List<String> fileContents);

    Map<String, LocalDateTime> parseLogFile(List<String> fileContents);

}
