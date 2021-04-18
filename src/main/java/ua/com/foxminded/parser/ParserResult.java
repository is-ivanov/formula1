package ua.com.foxminded.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ua.com.foxminded.racer.Racer;

public class ParserResult implements Parserable {

    private static final String MESSAGE_FILE_NOT_FOUND = "File not found!!";
    private static final String PATTERN_REGEX_FOR_LOG = "([A-Z]{3})(.+)";
    private static final String PATTERN_FOR_DATE_TIME = "yyyy-MM-dd_HH:mm:ss.SSS";
    private static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter.ofPattern(PATTERN_FOR_DATE_TIME);    

    @Override
    public List<String> readFile(String filepath) {
        List<String> lines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(this.getClass()
                .getClassLoader().getResource(filepath).toURI()))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            System.err.println(MESSAGE_FILE_NOT_FOUND);
        }
        return lines;
    }

    @Override
    public List<Racer> parseAbbreviationFile(List<String> fileContents) {

        return fileContents.stream().map(s -> s.split("_"))
                .map(a -> new Racer(a[0], a[1], a[2]))
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, LocalDateTime> parseLogFile(List<String> fileContents) {
        Map<String, LocalDateTime> map = new HashMap<>();

        for (String string : fileContents) {
            String[] matches = getMatches(string);
            map.put(matches[0], parseDateTime(matches[1]));
        }
        return map;

    }

    private String[] getMatches(String inputString) {
        Pattern patternLogFile = Pattern.compile(PATTERN_REGEX_FOR_LOG);
        Matcher matcher = patternLogFile.matcher(inputString);
        String[] outputStrings = new String[2];
        while (matcher.find()) {
            for (int i = 0; i < 2; i++) {
                outputStrings[i] = matcher.group(i + 1);
            }
        }
        return outputStrings;
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, FORMATTER_DATE_TIME);
    }

}
