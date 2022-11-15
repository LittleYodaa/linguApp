package pl.patrykkawula.linguapp.formatter;

import org.springframework.stereotype.Component;

@Component
public class LowerCaseTextFormatter implements TextFormatter{
    @Override
    public String printWord(String originalText) {
        return originalText.toLowerCase();
    }
}
