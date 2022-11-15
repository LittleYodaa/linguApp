package pl.patrykkawula.linguapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.patrykkawula.linguapp.cipher.Encryption;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final String fileName;

    private Encryption encryption;

    public FileService(@Value("${app.filename}") String fileName, Encryption encryption) {
        this.fileName = fileName;
        this.encryption = encryption;
    }

    public List<Entry> readAllLines() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(encryption::decipher)
                .map(FileService::splitLine)
                .collect(Collectors.toList());
    }

    public void saveEntryInFile(List<Entry> entries) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        for (Entry entry : entries) {
            bufferedWriter.write(encryption.encrypt(entry.toString()));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }


    private static Entry splitLine(String line) {
        String[] split = line.split(";");
        return new Entry(split[0], split[1]);
    }
}
