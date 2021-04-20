package ua.com.foxminded.formula1;


import java.io.IOException;

import ua.com.foxminded.formatter.Formattable;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.parser.Parser;
import ua.com.foxminded.parser.Parserable;
import ua.com.foxminded.processor.Processor;

public class Application {
    public static void main(String[] args) {
        Parserable parser = new Parser();
        Processor processor = new Processor();
        Formattable formatter = new Formatter();
        
        Facade facade = new Facade(parser, processor, formatter);
        
        try {
            facade.processQualificationResult();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}