package pl.patrykkawula.linguapp.dataAcces;

import java.io.IOException;
import java.util.List;

public interface EntryDataRepository {
    void saveEntry(List<Entry> entries) throws IOException;

    List<Entry> returnEntry() throws IOException;
}
