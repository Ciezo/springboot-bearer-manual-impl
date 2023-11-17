package api.inkdown.interfaces;

import api.inkdown.model.Notes;
import java.util.List;

public interface INotesService {

    List<Notes> getNotes();
    Notes addNote(Notes note);
    Notes updateNote(Notes note, long note_id, long user_id);
    Notes getNoteById(long id);
    void deleteNote(long id);

}
