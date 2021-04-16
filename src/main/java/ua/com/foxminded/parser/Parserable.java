package ua.com.foxminded.parser;

import java.util.List;

public interface Parserable {
    public List<String> readFile(String filepath);

    public void parseLog();

}
