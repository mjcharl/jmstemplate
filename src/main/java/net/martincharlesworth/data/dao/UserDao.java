package net.martincharlesworth.data.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import net.martincharlesworth.data.entity.User;

public class UserDao {

    private SessionFactory sf;

    public UserDao() {
        super();
    }

    public UserDao(SessionFactory sf) {
        super();
        this.sf = sf;
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public User findUserByUsername(String username) {
        Query query = sf.getCurrentSession().getNamedQuery("userByUsername");
        query.setString("username", username);

        List<User> users = query.list();

        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public User getUserWithOrders(Integer id) {
        Query query = sf.getCurrentSession().getNamedQuery("userWithOrders");
        query.setInteger("id", id);

        List<User> users = query.list();

        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public User getUserWithOrdersAndItems(Integer id) {
        Query query = sf.getCurrentSession().getNamedQuery("userWithOrdersAndItems");
        query.setInteger("id", id);

        List<User> users = query.list();

        if (!users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

    public void save(User user) {
        sf.getCurrentSession().saveOrUpdate(user);
    }
}
