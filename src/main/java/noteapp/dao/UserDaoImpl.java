package noteapp.dao;

import noteapp.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where username=:username ", User.class);
        query.setParameter("username", username);

        User user = null;

        try{
            user = query.getSingleResult();
            Hibernate.initialize(user.getNotes());
        } catch (NoResultException ignored) {}

        return user;
    }

    @Override
    public void save(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(user);
    }

    @Override
    public List<User> getUsers() {

        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("from User", User.class);

        return query.getResultList();
    }

}
