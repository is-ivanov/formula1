package ua.com.foxminded.formula1;


import ua.com.foxminded.formatter.Formattable;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.parser.ParserResult;
import ua.com.foxminded.parser.Parserable;
import ua.com.foxminded.processor.Processor;

public class Application {
    public static void main(String[] args) {
        Parserable parser = new ParserResult();
        Processor processor = new Processor();
        Formattable formatter = new Formatter();
        
        Facade facade = new Facade(parser, processor, formatter);
        
        facade.processQualificationResult();
    }
}