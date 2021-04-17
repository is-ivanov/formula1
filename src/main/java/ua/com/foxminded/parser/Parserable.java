package ua.com.foxminded.parser;

import java.util.List;
import java.util.Map;

import ua.com.foxminded.racer.Racer;

public interface Parserable {
    public List<String> readFile(String filepath);

    public Map<String, Racer> parseAbbreviationFile(List<String> fileContents);

    public void parseLogFile();

}
