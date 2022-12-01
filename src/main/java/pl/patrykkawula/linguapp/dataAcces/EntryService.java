package pl.patrykkawula.linguapp.dataAcces;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class EntryService {
    private EntryDataRepository dataService;
    private List<Entry> entries;

    public EntryService(EntryDataRepository dataService) {
        this.dataService = dataService;
        try {
            this.entries = dataService.returnEntry();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateEntry(Entry entry) {
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
