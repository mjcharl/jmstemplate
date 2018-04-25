package net.martincharlesworth.data.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.martincharlesworth.data.AbstractDBTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
// @TransactionConfiguration (defaultRollback=false) //Stops the transaction
// being rolled back at the end of the test.
public class UserTest extends AbstractDBTest {

    @Autowired
    private SessionFactory sf;

    @Before
    public void setUp() throws Exception {

        FlatXmlDataSetBuilder dataSetBuilder = new FlatXmlDataSetBuilder();

        IDataSet[] dataSets = new IDataSet[1];
        dataSets[0] = dataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("dbunit/users.xml"));

        CompositeDataSet compositeDataSet = new CompositeDataSet(dataSets);
        getDatabaseTester().setDataSet(compositeDataSet);
        getDatabaseTester().onSetup();
    }

    @Test
    @Transactional
    public void create() {
        User user = new User();
        user.setForename("Martin");
        user.setSurname("Charlesworth");
        user.setUsername("mcharlesworth");
        user.setPassword("Password123");

        sf.getCurrentSession().save(user);

        assertNotNull(user.getId());

    }

    @Test
    @Transactional
    public void find() {
        User user = (User) sf.getCurrentSession().get(User.class, 9999);

        assertNotNull(user);
        assertEquals("Aldo", user.getForename());

    }

    @Test(expected = StaleObjectStateException.class)
    public void testOptimisticLocking() {

        Session session = sf.openSession();

        Transaction trans = session.beginTransaction();
        // session.save(user);
        User user = (User) session.get(User.class, 9999);
        trans.commit();

        trans = session.beginTransaction();
        Query query = session.createSQLQuery("update users set version = version + 1 where id = " + user.getId());
        query.executeUpdate();
        trans.commit();

        trans = session.beginTransaction();
        user.setForename("Jack");
        trans.commit();

        session.close();

    }

}
