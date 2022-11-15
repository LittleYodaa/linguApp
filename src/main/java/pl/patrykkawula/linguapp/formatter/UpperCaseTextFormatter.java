package pl.patrykkawula.linguapp.formatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UpperCaseTextFormatter implements TextFormatter {
    @Override
    public String printWord(String originalText) {
        return originalText.toUpperCase();
    }
}
