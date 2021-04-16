package ua.com.foxminded.formula1;

import ua.com.foxminded.parser.Parser;
import ua.com.foxminded.parser.Parserable;

public class Facade {


    public void start() {
        String filename = "start.log";
        
        Parserable parser = new Parser();
        parser.readFile(filename);

    }
}
