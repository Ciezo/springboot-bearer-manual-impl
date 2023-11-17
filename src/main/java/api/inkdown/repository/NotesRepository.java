package api.inkdown.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import api.inkdown.model.Notes;

@Repository
public interface NotesRepository extends CrudRepository<Notes, Long> { }
