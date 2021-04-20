package ua.com.foxminded.parser;

import java.io.IOException;
import java.util.List;
import ua.com.foxminded.racer.Racer;

public interface Parserable {
    List<Racer> parseDataFiles(String abbreviationFilename, String startLogFilename, String endLogFilename) throws IOException;
    
}
