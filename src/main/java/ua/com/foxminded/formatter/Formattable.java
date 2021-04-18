package ua.com.foxminded.formatter;

import java.util.List;

import ua.com.foxminded.racer.Racer;

public interface Formattable {

    String formatResult(List<Racer> resultCalculation);

}