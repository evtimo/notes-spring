package noteapp.dao;

import noteapp.model.Note;

public interface NoteDao {

    Note getNoteById(Long id);

    void saveNote(Note note);

    void deleteNoteById(Long id);

}
