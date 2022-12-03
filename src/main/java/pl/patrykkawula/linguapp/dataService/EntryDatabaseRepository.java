package pl.patrykkawula.linguapp.dataService;

import org.springframework.data.repository.CrudRepository;

interface EntryDatabaseRepository extends  CrudRepository<Entry, Long> {
    Entry findByOriginal(String original);
}
