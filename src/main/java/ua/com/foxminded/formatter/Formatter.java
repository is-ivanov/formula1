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
    private static final String LF = System.lineSeparator();
    private static final String PATTERN_DURATION = "mm:ss.SSS";
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
            if(count == 15) {
                int lenghtString = countMaxLenghtString(maxLenghtName, maxLenghtTeam);
                
                resultStrings.append(String.join("", Collections.nCopies(lenghtString, "-")) + LF);
            }
            count++;
        }

        return resultStrings.toString();
    }

    private int countMaxLenghtString (int maxLenghtName, int maxLenghtTeam) {
        return 4 + maxLenghtName + 3 + maxLenghtTeam + 12;
    }
    
    private String createMaskFormat(int maxLenghtName, int maxLenghtTeam) {
        
        return "%02d. %-" + maxLenghtName + "s | %-" + maxLenghtTeam + "s | %s%s";
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
