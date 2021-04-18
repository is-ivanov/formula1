package ua.com.foxminded.formula1;


import ua.com.foxminded.calculator.Calculator;
import ua.com.foxminded.formatter.Formattable;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.parser.ParserResult;
import ua.com.foxminded.parser.Parserable;

public class Application {
    public static void main(String[] args) {
        Parserable parser = new ParserResult();
        Calculator calculator = new Calculator();
        Formattable formatter = new Formatter();
        
        Facade facade = new Facade(parser, calculator, formatter);
        
        facade.start();
    }
}