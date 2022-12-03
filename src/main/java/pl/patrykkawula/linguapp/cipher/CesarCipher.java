package pl.patrykkawula.linguapp.cipher;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class CesarCipher implements Encryption{
    private final static int SHIFT = 3;

    @Override
    public String encrypt(String originalText) {
        return originalText.chars()
                .map(c -> c + SHIFT)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public String decipher(String cipherText) {
        return cipherText.chars()
                .map(c -> c - SHIFT)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
