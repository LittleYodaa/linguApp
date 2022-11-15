package pl.patrykkawula.linguapp;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Repository
public class EntryRepository {
    private FileService fileService;
    private List<Entry> entries;

    public EntryRepository(FileService fileService) {
        this.fileService = fileService;
        try {
            this.entries = fileService.readAllLines();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Set<Entry> getRandomEntries(int number) {
        Random random = new Random();
        Set<Entry> entrySet = new HashSet<>();
        while (entrySet.size() < number && entrySet.size() < entries.size()) {
            entrySet.add(entries.get(random.nextInt(entries.size())));
        }
        return entrySet;
    }

    public List<Entry> getAll() {
        return entries;
    }

    public void add(Entry entry) {
        entries.add(entry);
    }

    public boolean isEmpty() {
        return entries.isEmpty();
    }

    public int getSize() {
        return entries.size();
    }
}
