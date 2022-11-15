package pl.patrykkawula.linguapp.cipher;

public interface Encryption {
    String encrypt(String originalText);

    String decipher(String orginalText);
}
