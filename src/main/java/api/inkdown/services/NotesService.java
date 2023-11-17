package api.inkdown.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import api.inkdown.model.Notes;
import api.inkdown.repository.NotesRepository;
import api.inkdown.interfaces.INotesService;

import java.util.List;
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

        // Find the note to update
        Notes noteToUpdate = repository.findById(note_id).get();
        long tempNoteID = noteToUpdate.getNote_id();

        // Identify the user logged-in for their note to update
        long userInSessionID = repository.findById(user_id).get().getUser_id();

        if(user_id == userInSessionID) {
            System.out.println("Found user in session!");
            if(note_id == tempNoteID) {
                System.out.println("Identified note resource...updating");
                return repository.save(note);
            }
        }
        return repository.save(note);
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
