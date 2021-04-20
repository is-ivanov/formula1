package ua.com.foxminded.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import ua.com.foxminded.racer.Racer;

public class Parser implements Parserable {
    private static final String MESSAGE_FILENAME_IS_NULL = "Filename is null!!";
    private static final String MESSAGE_FILE = "File \"";
    private static final String MESSAGE_NOT_FOUND = "\" not found!!";
    private static final String DELIMITER = "_";
    private static final String PATTERN_REGEX_FOR_LOG = "([A-Z]{3})(.+)";
    private static final String PATTERN_FOR_DATE_TIME = "yyyy-MM-dd_HH:mm:ss.SSS";
    private static final DateTimeFormatter FORMATTER_DATE_TIME = DateTimeFormatter
            .ofPattern(PATTERN_FOR_DATE_TIME);

    @Override
    public List<Racer> parseDataFiles(String abbreviationFilename,
                                      String startLogFilename,
                                      String endLogFilename) throws IOException {
        if (Objects.isNull(abbreviationFilename)
                || Objects.isNull(startLogFilename)
                || Objects.isNull(endLogFilename)) {
            throw new IllegalArgumentException(MESSAGE_FILENAME_IS_NULL);
        }
        List<String> fileContents = readFile(abbreviationFilename);
        List<Racer> racers = parseAbbreviationFile(fileContents);
        fileContents = readFile(startLogFilename);
        Map<String, LocalDateTime> startTimes = parseLogFile(fileContents);
        fileContents = readFile(endLogFilename);
        Map<String, LocalDateTime> endTimes = parseLogFile(fileContents);
        racers.stream().forEach(racer -> {
            racer.setStartTime(startTimes.get(racer.getId()));
            racer.setEndTime(endTimes.get(racer.getId()));
        });
        return racers;
    }

    private List<String> readFile(String filepath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(filepath);
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);) {
            lines = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            throw new IOException(MESSAGE_FILE + filepath + MESSAGE_NOT_FOUND);
        }
        return lines;
    }

    private List<Racer> parseAbbreviationFile(
            List<String> abbreviationFileContents) {

        return abbreviationFileContents.stream()
                .map(s -> s.split(DELIMITER))
                .map(arr -> new Racer(arr[0], arr[1], arr[2]))
                .collect(Collectors.toList());
    }

    private Map<String, LocalDateTime> parseLogFile(List<String> fileContents) {
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
