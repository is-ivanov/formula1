package ua.com.foxminded.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser implements Parserable {

    @Override
    public List<String> readFile(String filepath) {
        List<String> lines = new ArrayList<>();

        try (Stream<String> stream = Files
                .lines(Paths.get(this.getClass().getClassLoader().getResource(filepath).toURI()))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            System.out.println("exception");
        }
        return lines;
    }

    @Override
    public void parseLog() {

    }
}
