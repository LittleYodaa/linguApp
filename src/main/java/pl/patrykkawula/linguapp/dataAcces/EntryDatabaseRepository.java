package pl.patrykkawula.linguapp.dataAcces;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Primary
public class EntryDatabaseRepository implements EntryDataRepository {

    private final EntityManager entityManager;

    public EntryDatabaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Entry> returnEntry() throws IOException {
        List entries = entityManager.createQuery("select e from Entry e").getResultList();
        return entries;
    }

    @Transactional
    public void saveEntry(List<Entry> entries) throws IOException {
        for (Entry entry : entries) {
            entityManager.persist(entry);
        }
    }

}
