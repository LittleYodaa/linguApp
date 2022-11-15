package pl.patrykkawula.linguapp;

import org.springframework.stereotype.Service;
import pl.patrykkawula.linguapp.formatter.TextFormatter;

@Service
public class ConsoleOutputWriter {
    private final TextFormatter textFormatter;

    public ConsoleOutputWriter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    public void println(String text) {
        String formattedText = textFormatter.printWord(text);
        System.out.println(formattedText);
    }
}
