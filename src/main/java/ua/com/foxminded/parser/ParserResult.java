package ua.com.foxminded.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ua.com.foxminded.racer.Racer;

public class ParserResult implements Parserable {

    private static final String MESSAGE_FILE_NOT_FOUND = "File not found!!";

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
    public Map<String, Racer> parseAbbreviationFile(List<String> fileContents) {

        return fileContents.stream().map((s) -> s.split("_"))
                .map((s) -> new Racer(s[0], s[1], s[2]))
                .collect(Collectors.toMap(Racer::getId, s -> s));

    }

    @Override
    public void parseLogFile() {

    }

}
