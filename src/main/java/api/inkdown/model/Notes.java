package api.inkdown.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long note_id;

    private long user_id;
    private String author;
    private String title;
    private String body;
    private LocalDate date_posted;

    public Notes() {}

    public Notes(long note_id, long user_id, String author, String title, String body, LocalDate date_posted) {
        this.note_id = note_id;
        this.user_id = user_id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.date_posted = date_posted;
    }

    public long getNote_id() {
        return note_id;
    }

    public void setNote_id(long note_id) {
        this.note_id = note_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate_posted() { return LocalDate.now();}

    public void setDate_posted(LocalDate date_posted) {
        this.date_posted = date_posted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return note_id == notes.note_id &&
                user_id == notes.user_id &&
                author.equals(notes.author) &&
                title.equals(notes.title) &&
                body.equals(notes.body) &&
                date_posted.equals(notes.date_posted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(note_id, user_id, author, title, body, date_posted);
    }
}
