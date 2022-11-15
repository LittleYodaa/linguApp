package pl.patrykkawula.linguapp.cipher;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class StandardCipher implements Encryption{
    @Override
    public String encrypt(String originalText) {
        return originalText;
    }

    @Override
    public String decipher(String cipherText) {
        return cipherText;
    }
}
