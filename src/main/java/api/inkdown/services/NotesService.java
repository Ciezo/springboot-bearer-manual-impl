package api.inkdown.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.inkdown.model.Notes;
import api.inkdown.repository.NotesRepository;
import api.inkdown.interfaces.INotesService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NotesService implements INotesService {

    @Autowired
    private NotesRepository repository;

    @Override
    public List<Notes> getNotes() {
        return (List<Notes>) repository.findAll();
    }

    @Override
    public Notes addNote(Notes note) {
        return repository.save(note);
    }

    @Override
    public Notes updateNote(Notes note, long note_id, long user_id) {
        /**
         * @note Must always provide note_id and user_id on the client side
         * @param note, the Notes entity model
         * @param note_id, the existing id to identify note resource
         * @param user_id, another existing id to identify the user of the note (foreign key)
         */

        // Find the note to update
        Notes noteToUpdate = repository.findById(note_id).get();

        // Ensure the note object returned is not null
        if (Objects.nonNull(noteToUpdate)) {
            System.out.println("Record found!");

            System.out.println("Setting attributes");
            noteToUpdate.setNote_id(note.getNote_id());
            noteToUpdate.setUser_id(note.getUser_id());
            noteToUpdate.setAuthor(note.getAuthor());
            noteToUpdate.setBody(note.getBody());
            noteToUpdate.setDate_posted(note.getDate_posted());

            System.out.println("Updating...");
            return repository.save(noteToUpdate);

        } else {
            // We "force" to create a new note based on the note id and user id
            // from the database
            System.out.println("No resource found....creating now!");
            Notes new_note = new Notes();
            new_note.setNote_id(note_id);
            new_note.setUser_id(user_id);
            new_note.setAuthor(note.getAuthor());
            new_note.setUser_id(note.getUser_id());
            new_note.setBody(note.getBody());
            new_note.setDate_posted(note.getDate_posted());

            System.out.println("Updating...");
            return repository.save(new_note);
        }
    }

    @Override
    public Notes getNoteById(long id) {
        Optional optional = repository.findById(id);
        if(optional.isPresent()) {
            return  (Notes) optional.get();
        } else { return null; }
    }

    @Override
    public void deleteNote(long id) {
        repository.deleteById(id);
    }
}
