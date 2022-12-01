package pl.patrykkawula.linguapp.dataAcces;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Profile("database")
interface EntryDbRepository extends CrudRepository<Entry, Long> {

//    public List<Entry> returnEntry() throws IOException {
//        List entries = entityManager.createQuery("select e from Entry e").getResultList();
//        return entries;
//    }
//
//    @Override
//    public Optional<Entry> findByOriginal(String original) {
//        return Optional.ofNullable(entityManager.find(Entry.class, original));
//    }
//
//    @Transactional
//    public void saveEntry(List<Entry> entries) throws IOException {
//        for (Entry entry : entries) {
//            entityManager.persist(entry);
//        }
//    }

}
