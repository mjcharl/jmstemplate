package net.martincharlesworth.business;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.martincharlesworth.data.dao.UserDao;
import net.martincharlesworth.data.entity.User;

@Component
public class UserFacade {

    @Autowired
    private SessionFactory sf;

    @Transactional(propagation = Propagation.REQUIRED)
    public User findUserByUsername(String username) {
        UserDao dao = new UserDao(sf);
        User user = dao.findUserByUsername(username);
        return user;
    }

    @Transactional
    public void saveUser(User user) {
        UserDao dao = new UserDao(sf);
        dao.save(user);
    }
}
