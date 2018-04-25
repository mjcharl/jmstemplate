package net.martincharlesworth.data;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class HibernateConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SessionFactory sf;

    @Test
    public void getSession() {
        assertNotNull(applicationContext);
        assertNotNull(sf);
    }
}
