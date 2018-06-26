package noteapp.service;

import noteapp.dao.NoteDao;
import noteapp.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteServiceImpl  implements NoteService{

    private final NoteDao noteDao;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @Override
    @Transactional
    public Note getNoteById(Long id) {
        return noteDao.getNoteById(id);
    }

    @Override
    @Transactional
    public void saveNote(Note note) {
        noteDao.saveNote(note);
    }

    @Override
    @Transactional
    public void deleteNoteById(Long id) {

        noteDao.deleteNoteById(id);

    }
}
