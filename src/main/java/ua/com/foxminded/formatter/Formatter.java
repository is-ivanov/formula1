package ua.com.foxminded.formatter;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import ua.com.foxminded.racer.Racer;

public class Formatter implements Formattable {
    private static final int NUMBER_QUALIFIED_RACERS = 15;
    private static final int NUMBER_AUXILIARY_CHARACTERS = 15;
    private static final String LF = System.lineSeparator();
    private static final String SYMBOL_FOR_STRING_DELIMITER = "-";
    private static final String DELIMITER_FOR_STRING_JOIN = "";
    private static final String PATTERN_DURATION = "mm:ss.SSS";
    private static final String FIRST_PART_MASK = "%02d. %-";
    private static final String SECOND_PART_MASK = "s | %-";
    private static final String LAST_PART_MASK = "s | %s%s";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DURATION);
    

    @Override
    public String formatResult(List<Racer> resultCalculation) {

        resultCalculation.sort(Comparator.comparing(Racer::getLapTime));
        StringBuilder resultStrings = new StringBuilder();
        int maxLenghtName = countMaxLenghtName(resultCalculation);
        int maxLenghtTeam = countMaxLenghtTeam(resultCalculation);

        String maskFormat = createMaskFormat(maxLenghtName, maxLenghtTeam);

        int count = 1;
        for (Racer racer : resultCalculation) {
            resultStrings.append(String.format(maskFormat, 
                                        count,
                                               racer.getName(),
                                               racer.getTeam(),
                                               formatDuration(racer.getLapTime()),
                                               LF));
            if (count == NUMBER_QUALIFIED_RACERS) {
                int lenghtString = countMaxLenghtString(maxLenghtName,
                        maxLenghtTeam);

                resultStrings.append(String
                        .join(DELIMITER_FOR_STRING_JOIN, Collections.nCopies(
                                lenghtString, SYMBOL_FOR_STRING_DELIMITER))
                        + LF);
            }
            count++;
        }

        return resultStrings.toString();
    }

    private int countMaxLenghtString (int maxLenghtName, int maxLenghtTeam) {
        return NUMBER_AUXILIARY_CHARACTERS + maxLenghtName + maxLenghtTeam;
    }
    
    private String createMaskFormat(int maxLenghtName, int maxLenghtTeam) {

        return FIRST_PART_MASK + maxLenghtName + SECOND_PART_MASK
                + maxLenghtTeam + LAST_PART_MASK;
    }

    private int countMaxLenghtName(List<Racer> resultCalculation) {
        return Collections.max(resultCalculation.stream()
                .map(s -> s.getName().length()).collect(Collectors.toList()));
    }

    private int countMaxLenghtTeam(List<Racer> resultCalculation) {
        return Collections.max(resultCalculation.stream()
                .map(s -> s.getTeam().length()).collect(Collectors.toList()));
    }

    private String formatDuration(Duration duration) {
        LocalTime timeDuration = LocalTime.MIDNIGHT.plus(duration);
        return timeDuration.format(TIME_FORMATTER);
    }

}
