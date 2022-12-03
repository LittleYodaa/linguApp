package pl.patrykkawula.linguapp.dataService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@ConditionalOnProperty(value = "data.save.mode", havingValue = "database")
public class DatabaseSavingService implements SavingService {
    private final EntryDatabaseRepository entryDatabaseRepository;

    public DatabaseSavingService(EntryDatabaseRepository entryDatabaseRepository) {
        this.entryDatabaseRepository = entryDatabaseRepository;
    }

    @Override
    public void saveEntry(List<Entry> entries) throws IOException {
        entryDatabaseRepository.saveAll(entries);
    }

    @Override
    public List<Entry> returnEntry() throws IOException {
        List<Entry> entries = new ArrayList<>();
        entryDatabaseRepository.findAll().forEach(entries::add);
        return entries;
    }

    @Override
    public Optional<Entry> findByOriginal(String original) throws IOException {
        return findByOriginal(original);
    }
}
