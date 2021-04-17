package ua.com.foxminded.formula1;

import ua.com.foxminded.parser.ParserResult;
import ua.com.foxminded.parser.Parserable;

public class Application {
    public static void main(String[] args) {
        Parserable parser = new ParserResult();
                
        Facade facade = new Facade(parser);
        facade.start();
    }
}