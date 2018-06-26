package noteapp.dao;

import noteapp.model.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NoteDaoImpl implements NoteDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public NoteDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Note getNoteById(Long id) {

        Session session = sessionFactory.getCurrentSession();

        return session.get(Note.class, id);
    }

    @Override
    public void saveNote(Note note) {

        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(note);

    }

    @Override
    public void deleteNoteById(Long id) {

        Session session = sessionFactory.getCurrentSession();

        session.delete(getNoteById(id));

    }

}
