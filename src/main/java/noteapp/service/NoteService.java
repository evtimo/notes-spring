package noteapp.service;

import noteapp.model.Note;

public interface NoteService {

    Note getNoteById(Long id);

    void saveNote(Note note);

    void deleteNoteById(Long id);


}
