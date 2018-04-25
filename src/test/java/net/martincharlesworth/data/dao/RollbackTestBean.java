package net.martincharlesworth.data.dao;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.martincharlesworth.data.AbstractDBTest;
import net.martincharlesworth.data.entity.User;

//This bean is served from the bean factory and works with AOP proxy or
//the TestApp. It allows the required functionality to work without Spring JUnit
//stuff causing rollback problems.
@Component
public class RollbackTestBean extends AbstractDBTest {

    private Logger logger = LoggerFactory.getLogger(RollbackTestBean.class);

    @Autowired
    private SessionFactory sf;

    @Transactional
    public void testRollback() {
        createUserSuccessful();
        try {
            createUserRollback();
        } catch (Exception e) {
            logger.error("Expected exeption for test", e);
        }

        UserDao dao = new UserDao(sf);
        User user1 = dao.findUserByUsername("mcharlesworth");
        User user2 = dao.findUserByUsername("mcharlesworth2");
        logger.info(Boolean.toString(user1 != null));
        logger.info(Boolean.toString(user2 == null));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createUserSuccessful() {
        UserDao dao = new UserDao(sf);
        User user = new User();
        user.setForename("Martin");
        user.setSurname("Charlesworth");
        user.setUsername("mcharlesworth");
        user.setPassword("Password123");
        user.setEnabled(true);

        // Persist the user
        dao.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createUserRollback() {
        UserDao dao = new UserDao(sf);
        User user = new User();
        user.setForename("Martin");
        user.setSurname("Charlesworth");
        user.setUsername("mcharlesworth2");
        user.setPassword("Password123");
        user.setEnabled(true);

        // Persist the user
        dao.save(user);

        if (2 > 1) {
            throw new RuntimeException();
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTestData() {
        SQLQuery query = sf.getCurrentSession()
                .createSQLQuery("delete from users where username like 'mcharleswort%';");
        query.executeUpdate();
    }

}
