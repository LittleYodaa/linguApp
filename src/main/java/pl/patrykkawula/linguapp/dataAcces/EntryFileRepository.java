package pl.patrykkawula.linguapp.dataAcces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.patrykkawula.linguapp.cipher.Encryption;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("file")
public class EntryFileRepository implements EntryDataRepository {

    private final String fileName;

    private Encryption encryption;

    public EntryFileRepository(@Value("${app.filename}") String fileName, Encryption encryption) {
        this.fileName = fileName;
        this.encryption = encryption;
    }

    public List<Entry> returnEntry() throws IOException {
        return Files.readAllLines(Paths.get(fileName))
                .stream()
                .map(encryption::decipher)
                .map(EntryFileRepository::splitLine)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Entry> findByOriginal(String original) throws IOException {
        Optional<Entry> entryOptional = Optional.empty();
        List<Entry> entries = returnEntry();
        for (Entry entry : entries) {
            if (!entry.getOriginal().equals(original)) {
                entryOptional = Optional.empty();
            } else {
                entryOptional = Optional.ofNullable(entry);
            }
        }
        return entryOptional;
    }


    public void saveEntry(List<Entry> entries) throws IOException {
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
