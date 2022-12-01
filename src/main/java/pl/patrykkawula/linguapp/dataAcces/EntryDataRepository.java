package pl.patrykkawula.linguapp.dataAcces;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface EntryDataRepository {
    void saveEntry(List<Entry> entries) throws IOException;

    List<Entry> returnEntry() throws IOException;

    Optional<Entry> findByOriginal(String original) throws IOException;
}
