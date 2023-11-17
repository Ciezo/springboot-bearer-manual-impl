package api.inkdown.controller;

import api.inkdown.errors.ErrorResponse;
import api.inkdown.keys.SecureKeys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import api.inkdown.model.Notes;
import api.inkdown.services.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotesController {

    @Autowired
    private NotesService service;

    private final static String SECURE_KEY = SecureKeys.getApiKeys().trim();

    // Get all notes resources
    @RequestMapping("/api/inkdown/notes")
    public ResponseEntity<?> fetchAllNotes(@RequestHeader("Authorization") String auth) {
        /**
         * @note Fetch all notes contents
         * @example
         *     "note_id": 2,
         *     "user_id": 1,
         *     "author": "Chariz Valerie Secuya",
         *     "title": "Note Title 2",
         *     "body": "The quick brown fox jumped over the lazy dogs",
         *     "date_posted": "November 14, 2023"
         */
        String authKey = auth.replace("Bearer", "").trim();
        if (authKey.equals(SECURE_KEY)) {
            return ResponseEntity.ok(service.getNotes());
        } else {
            ErrorResponse errorResponse = new ErrorResponse("Unauthorized access");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    // Post note
    @RequestMapping(value = "/api/inkdown/add-note", method = RequestMethod.POST)
    public Notes saveNote(@RequestHeader("Authorization") String auth,  @RequestBody Notes note) {
        /**
         * @body Notes model
         * NotesService implementation
         * return repository.save(note);
         */
        String authKey = auth.replace("Bearer", "").trim();
        if(authKey.equals(SECURE_KEY)) {
            return service.addNote(note);
        } else {
            return null;
        }
    }

    // Put note
    @RequestMapping(value = "/api/inkdown/update-note/n/{note_id}/u/{user_id}", method = RequestMethod.PUT)
    public Notes patchNote(@RequestHeader("Authorization") String auth,  @RequestBody Notes note,
                           @PathVariable long note_id, @PathVariable long user_id) {
        /**
         * @body Notes model
         *
         * NotesService implementation:
         *  return repository.save(note);
         */
        String authKey = auth.replace("Bearer", "").trim();
        if(authKey.equals(SECURE_KEY)) {
            return service.updateNote(note, note_id, user_id);
        } else {
            return null;
        }
    }

    // Get note by note_id
    @RequestMapping(value = "/api/inkdown/note/{id}")
    public Notes fetchNote(@RequestHeader("Authorization") String auth, @PathVariable long id) {
        /**
         * @param id must be present
         *
         * NotesService implementation:
         * if(optional.isPresent())
         *   return  (Notes) optional.get();
         */
        String authKey = auth.replace("Bearer", "").trim();
        if(authKey.equals(SECURE_KEY)) {
            return service.getNoteById(id);
        } else {
            return null;
        }
    }

    // Delete note by note_id
    @RequestMapping(value = "/api/inkdown/note/delete/{id}", method = RequestMethod.DELETE)
    public void removeNote(@RequestHeader("Authorization") String auth, @PathVariable long id) {
        /**
         * @param id must be present
         *
         * NotesService implementation:
         * repository.deleteById(id);
         */
        String authKey = auth.replace("Bearer", "").trim();
        if(authKey.equals(SECURE_KEY))
            service.deleteNote(id);
    }
}
